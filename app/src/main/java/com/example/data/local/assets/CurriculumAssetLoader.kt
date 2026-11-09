package com.example.data.local.assets

import android.content.Context
import com.example.data.biology.EnemBiologyCurriculum
import com.example.data.chemistry.EnemChemistryCurriculum
import com.example.data.geography.EnemGeographyCurriculum
import com.example.data.history.EnemHistoryCurriculum
import com.example.data.languages.EnemLanguagesCurriculum
import com.example.data.math.CalculusCurriculum
import com.example.data.math.EnemMathCurriculum
import com.example.data.model.DifficultyTier
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LearningUnit
import com.example.data.model.Lesson
import com.example.data.model.LessonExample
import com.example.data.model.LessonType
import com.example.data.model.MathExercise
import com.example.data.model.TestCase
import com.example.data.model.ValidationType
import com.example.data.physics.EnemPhysicsCurriculum
import com.example.data.python.PythonCurriculum
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import okio.Buffer
import java.io.File

/**
 * Página genérica de currículo carregada sob demanda (paginação de módulos/unidades ou aulas).
 */
data class CurriculumPage<T>(
    val items: List<T>,
    val page: Int,
    val pageSize: Int,
    val totalItems: Int,
    val totalPages: Int,
    val hasNextPage: Boolean
)

/**
 * Metadados leves de cada trilha no manifesto de assets (`assets/curricula/manifest.json`).
 */
data class SubjectAssetManifestEntry(
    val subjectId: String,
    val title: String,
    val category: String,
    val assetPath: String,
    val moduleCount: Int,
    val lessonCount: Int
)

/**
 * Carregador modular e paginado de currículos via JSON Assets (`assets/curricula/<subjectId>.json`)
 * utilizando streaming (`JsonReader`/`JsonWriter` via Okio/Moshi) e cache LRU limitado em memória.
 */
object CurriculumAssetLoader {

    private const val MANIFEST_ASSET_PATH = "curricula/manifest.json"
    private const val MAX_CACHED_SUBJECTS = 3

    // Mantém no máximo 3 disciplinas simultaneamente em memória (LRU) para reduzir pressão de heap
    private val subjectUnitsCache = object : LinkedHashMap<String, List<LearningUnit>>(MAX_CACHED_SUBJECTS + 1, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, List<LearningUnit>>?): Boolean {
            return size > MAX_CACHED_SUBJECTS
        }
    }

    val supportedSubjectIds = listOf(
        "python",
        "basic_math",
        "calculus",
        "enem_math",
        "physics",
        "chemistry",
        "biology",
        "history",
        "geography",
        "languages"
    )

    /**
     * Carrega o manifesto leve de disciplinas a partir de `assets/curricula/manifest.json`,
     * ou reconstrói dinamicamente caso o Context não esteja disponível.
     */
    fun loadManifest(context: Context? = null): List<SubjectAssetManifestEntry> {
        if (context != null) {
            try {
                val raw = context.assets.open(MANIFEST_ASSET_PATH).bufferedReader(Charsets.UTF_8).use { it.readText() }
                val parsed = parseManifestJson(raw)
                if (parsed.isNotEmpty()) return parsed
            } catch (_: Exception) {
                // Fallback seguro caso o asset ainda não esteja empacotado
            }
        }
        return supportedSubjectIds.map { subjectId ->
            val units = loadAllUnitsForSubject(context, subjectId)
            SubjectAssetManifestEntry(
                subjectId = subjectId,
                title = displayTitleForSubject(subjectId),
                category = categoryForSubject(subjectId),
                assetPath = "curricula/$subjectId.json",
                moduleCount = units.size,
                lessonCount = units.sumOf { it.lessons.size }
            )
        }
    }

    /**
     * Carrega uma página de módulos (`LearningUnit`) sob demanda para a disciplina solicitada.
     */
    fun loadUnitsPage(
        context: Context?,
        subjectId: String,
        page: Int,
        pageSize: Int = 2
    ): CurriculumPage<LearningUnit> {
        val safePage = page.coerceAtLeast(0)
        val safePageSize = pageSize.coerceAtLeast(1)
        val allUnits = loadAllUnitsForSubject(context, subjectId)
        return paginateList(allUnits, safePage, safePageSize)
    }

    /**
     * Carrega uma página de aulas (`Lesson`) sob demanda para a disciplina solicitada.
     */
    fun loadLessonsPage(
        context: Context?,
        subjectId: String,
        page: Int,
        pageSize: Int = 5
    ): CurriculumPage<Lesson> {
        val safePage = page.coerceAtLeast(0)
        val safePageSize = pageSize.coerceAtLeast(1)
        val allLessons = loadAllUnitsForSubject(context, subjectId).flatMap { it.lessons }
        return paginateList(allLessons, safePage, safePageSize)
    }

    /**
     * Carrega todas as unidades de uma disciplina priorizando o arquivo JSON em `assets/curricula/<subjectId>.json`,
     * utilizando cache LRU e fallback para os objetos curriculares Kotlin.
     */
    fun loadAllUnitsForSubject(context: Context?, subjectId: String): List<LearningUnit> {
        val normalizedId = subjectId.trim().lowercase()
        synchronized(subjectUnitsCache) {
            subjectUnitsCache[normalizedId]?.let { return it }
        }

        val loadedFromAssets: List<LearningUnit>? = if (context != null) {
            try {
                val assetPath = "curricula/$normalizedId.json"
                val jsonText = context.assets.open(assetPath).bufferedReader(Charsets.UTF_8).use { it.readText() }
                deserializeUnitsFromJson(jsonText).takeIf { it.isNotEmpty() }
            } catch (_: Exception) {
                null
            }
        } else {
            null
        }

        val units = loadedFromAssets ?: loadFallbackUnitsFromKotlin(normalizedId)
        if (units.isNotEmpty()) {
            synchronized(subjectUnitsCache) {
                subjectUnitsCache[normalizedId] = units
            }
        }
        return units
    }

    fun clearMemoryCache() {
        synchronized(subjectUnitsCache) {
            subjectUnitsCache.clear()
        }
    }

    /**
     * Serializa a lista de `LearningUnit` de uma disciplina para o formato JSON padronizado de asset via streaming.
     */
    fun serializeUnitsToJson(subjectId: String, units: List<LearningUnit>): String {
        val buffer = Buffer()
        JsonWriter.of(buffer).use { writer ->
            writer.indent = "  "
            writer.beginObject()
            writer.name("subjectId").value(subjectId)
            writer.name("title").value(displayTitleForSubject(subjectId))
            writer.name("category").value(categoryForSubject(subjectId))
            writer.name("unitCount").value(units.size)
            writer.name("lessonCount").value(units.sumOf { it.lessons.size })
            writer.name("units")
            writer.beginArray()
            for (unit in units) {
                writer.beginObject()
                writer.name("id").value(unit.id)
                writer.name("unitNumber").value(unit.unitNumber)
                writer.name("tier").value(unit.tier.name)
                writer.name("title").value(unit.title)
                writer.name("description").value(unit.description)
                writer.name("category").value(unit.category)
                writer.name("subject").value(unit.subject)

                writer.name("lessons")
                writer.beginArray()
                for (lesson in unit.lessons) {
                    writer.beginObject()
                    writer.name("id").value(lesson.id)
                    writer.name("unitId").value(lesson.unitId)
                    writer.name("title").value(lesson.title)
                    writer.name("conceptTitle").value(lesson.conceptTitle)
                    writer.name("explanation").value(lesson.explanation)
                    writer.name("lessonType").value(lesson.lessonType.name)
                    writer.name("initialCode").value(lesson.initialCode)

                    writer.name("hints")
                    writer.beginArray()
                    for (h in lesson.hints) writer.value(h)
                    writer.endArray()

                    writer.name("solutionExplanation").value(lesson.solutionExplanation)
                    writer.name("xpReward").value(lesson.xpReward)
                    writer.name("theorySection").value(lesson.theorySection)

                    writer.name("testCases")
                    writer.beginArray()
                    for (tc in lesson.testCases) {
                        writer.beginObject()
                        writer.name("name").value(tc.name)
                        writer.name("stdinInput").value(tc.stdinInput)
                        writer.name("expectedOutput").value(tc.expectedOutput)
                        writer.name("validationType").value(tc.validationType.name)
                        writer.name("isHidden").value(tc.isHidden)
                        writer.endObject()
                    }
                    writer.endArray()

                    writer.name("mathExercises")
                    writer.beginArray()
                    for (me in lesson.mathExercises) {
                        writer.beginObject()
                        writer.name("id").value(me.id)
                        writer.name("title").value(me.title)
                        writer.name("problemStatement").value(me.problemStatement)
                        writer.name("options")
                        writer.beginArray()
                        for (opt in me.options) writer.value(opt)
                        writer.endArray()
                        writer.name("correctIndex").value(me.correctIndex)
                        writer.name("hint").value(me.hint)
                        writer.name("stepByStepSolution").value(me.stepByStepSolution)
                        writer.endObject()
                    }
                    writer.endArray()

                    writer.name("formulasOrSyntax")
                    writer.beginArray()
                    for (f in lesson.formulasOrSyntax) {
                        writer.beginObject()
                        writer.name("termOrFunction").value(f.termOrFunction)
                        writer.name("syntax").value(f.syntax)
                        writer.name("description").value(f.description)
                        writer.name("parametersInfo").value(f.parametersInfo)
                        writer.endObject()
                    }
                    writer.endArray()

                    writer.name("examples")
                    writer.beginArray()
                    for (ex in lesson.examples) {
                        writer.beginObject()
                        writer.name("title").value(ex.title)
                        writer.name("codeOrFormula").value(ex.codeOrFormula)
                        writer.name("explanation").value(ex.explanation)
                        writer.endObject()
                    }
                    writer.endArray()

                    writer.endObject()
                }
                writer.endArray()
                writer.endObject()
            }
            writer.endArray()
            writer.endObject()
        }
        return buffer.readUtf8()
    }

    /**
     * Desserializa um JSON de disciplina em uma lista estruturada de `LearningUnit` via `JsonReader`.
     */
    fun deserializeUnitsFromJson(jsonString: String): List<LearningUnit> {
        val units = mutableListOf<LearningUnit>()
        val buffer = Buffer().writeUtf8(jsonString)
        JsonReader.of(buffer).use { reader ->
            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "units" -> {
                        reader.beginArray()
                        while (reader.hasNext()) {
                            units.add(readUnit(reader))
                        }
                        reader.endArray()
                    }
                    else -> reader.skipValue()
                }
            }
            reader.endObject()
        }
        return units
    }

    private fun readUnit(reader: JsonReader): LearningUnit {
        var id = ""
        var unitNumber = 1
        var tier = DifficultyTier.BASIC
        var title = ""
        var description = ""
        var category = ""
        var subject = ""
        val lessons = mutableListOf<Lesson>()

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "id" -> id = reader.nextString()
                "unitNumber" -> unitNumber = reader.nextInt()
                "tier" -> {
                    val raw = reader.nextString()
                    tier = runCatching { DifficultyTier.valueOf(raw) }.getOrDefault(DifficultyTier.BASIC)
                }
                "title" -> title = reader.nextString()
                "description" -> description = reader.nextString()
                "category" -> category = reader.nextString()
                "subject" -> subject = reader.nextString()
                "lessons" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        lessons.add(readLesson(reader, id))
                    }
                    reader.endArray()
                }
                else -> reader.skipValue()
            }
        }
        reader.endObject()

        return LearningUnit(
            id = id,
            unitNumber = unitNumber,
            tier = tier,
            title = title,
            description = description,
            lessons = lessons,
            category = category,
            subject = subject
        )
    }

    private fun readLesson(reader: JsonReader, defaultUnitId: String): Lesson {
        var id = ""
        var unitId = defaultUnitId
        var title = ""
        var conceptTitle = ""
        var explanation = ""
        var lessonType = LessonType.LEARN
        var initialCode = ""
        val hints = mutableListOf<String>()
        var solutionExplanation = ""
        var xpReward = 25
        var theorySection = ""
        val testCases = mutableListOf<TestCase>()
        val mathExercises = mutableListOf<MathExercise>()
        val formulas = mutableListOf<FormulaOrSyntaxItem>()
        val examples = mutableListOf<LessonExample>()

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "id" -> id = reader.nextString()
                "unitId" -> unitId = reader.nextString()
                "title" -> title = reader.nextString()
                "conceptTitle" -> conceptTitle = reader.nextString()
                "explanation" -> explanation = reader.nextString()
                "lessonType" -> {
                    val raw = reader.nextString()
                    lessonType = runCatching { LessonType.valueOf(raw) }.getOrDefault(LessonType.LEARN)
                }
                "initialCode" -> initialCode = reader.nextString()
                "hints" -> {
                    reader.beginArray()
                    while (reader.hasNext()) hints.add(reader.nextString())
                    reader.endArray()
                }
                "solutionExplanation" -> solutionExplanation = reader.nextString()
                "xpReward" -> xpReward = reader.nextInt()
                "theorySection" -> theorySection = reader.nextString()
                "testCases" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        var tcName = ""
                        var stdinInput = ""
                        var expectedOutput = ""
                        var validationType = ValidationType.EXACT_OUTPUT
                        var isHidden = false
                        reader.beginObject()
                        while (reader.hasNext()) {
                            when (reader.nextName()) {
                                "name" -> tcName = reader.nextString()
                                "stdinInput" -> stdinInput = reader.nextString()
                                "expectedOutput" -> expectedOutput = reader.nextString()
                                "validationType" -> {
                                    val v = reader.nextString()
                                    validationType = runCatching { ValidationType.valueOf(v) }.getOrDefault(ValidationType.EXACT_OUTPUT)
                                }
                                "isHidden" -> isHidden = reader.nextBoolean()
                                else -> reader.skipValue()
                            }
                        }
                        reader.endObject()
                        testCases.add(TestCase(tcName, stdinInput, expectedOutput, validationType, isHidden))
                    }
                    reader.endArray()
                }
                "mathExercises" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        var exId = ""
                        var exTitle = ""
                        var problemStatement = ""
                        val options = mutableListOf<String>()
                        var correctIndex = 0
                        var hint = ""
                        var stepByStepSolution = ""
                        reader.beginObject()
                        while (reader.hasNext()) {
                            when (reader.nextName()) {
                                "id" -> exId = reader.nextString()
                                "title" -> exTitle = reader.nextString()
                                "problemStatement" -> problemStatement = reader.nextString()
                                "options" -> {
                                    reader.beginArray()
                                    while (reader.hasNext()) options.add(reader.nextString())
                                    reader.endArray()
                                }
                                "correctIndex" -> correctIndex = reader.nextInt()
                                "hint" -> hint = reader.nextString()
                                "stepByStepSolution" -> stepByStepSolution = reader.nextString()
                                else -> reader.skipValue()
                            }
                        }
                        reader.endObject()
                        mathExercises.add(
                            MathExercise(exId, exTitle, problemStatement, options, correctIndex, hint, stepByStepSolution)
                        )
                    }
                    reader.endArray()
                }
                "formulasOrSyntax" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        var term = ""
                        var syntax = ""
                        var desc = ""
                        var params = ""
                        reader.beginObject()
                        while (reader.hasNext()) {
                            when (reader.nextName()) {
                                "termOrFunction" -> term = reader.nextString()
                                "syntax" -> syntax = reader.nextString()
                                "description" -> desc = reader.nextString()
                                "parametersInfo" -> params = reader.nextString()
                                else -> reader.skipValue()
                            }
                        }
                        reader.endObject()
                        formulas.add(FormulaOrSyntaxItem(term, syntax, desc, params))
                    }
                    reader.endArray()
                }
                "examples" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        var exTitle = ""
                        var codeOrFormula = ""
                        var exExplanation = ""
                        reader.beginObject()
                        while (reader.hasNext()) {
                            when (reader.nextName()) {
                                "title" -> exTitle = reader.nextString()
                                "codeOrFormula" -> codeOrFormula = reader.nextString()
                                "explanation" -> exExplanation = reader.nextString()
                                else -> reader.skipValue()
                            }
                        }
                        reader.endObject()
                        examples.add(LessonExample(exTitle, codeOrFormula, exExplanation))
                    }
                    reader.endArray()
                }
                else -> reader.skipValue()
            }
        }
        reader.endObject()

        return Lesson(
            id = id,
            unitId = unitId,
            title = title,
            conceptTitle = conceptTitle,
            explanation = explanation,
            lessonType = lessonType,
            initialCode = initialCode,
            hints = hints,
            solutionExplanation = solutionExplanation,
            xpReward = xpReward,
            testCases = testCases,
            mathExercises = mathExercises,
            theorySection = theorySection,
            formulasOrSyntax = formulas,
            examples = examples
        )
    }

    /**
     * Exporta todos os currículos estáticos para arquivos JSON paginados em `outputDir`.
     */
    fun exportAllCurriculaToDirectory(outputDir: File): List<SubjectAssetManifestEntry> {
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
        val manifestEntries = mutableListOf<SubjectAssetManifestEntry>()

        for (subjectId in supportedSubjectIds) {
            val units = loadFallbackUnitsFromKotlin(subjectId)
            val jsonContent = serializeUnitsToJson(subjectId, units)
            val subjectFile = File(outputDir, "$subjectId.json")
            subjectFile.writeText(jsonContent, Charsets.UTF_8)

            manifestEntries.add(
                SubjectAssetManifestEntry(
                    subjectId = subjectId,
                    title = displayTitleForSubject(subjectId),
                    category = categoryForSubject(subjectId),
                    assetPath = "curricula/$subjectId.json",
                    moduleCount = units.size,
                    lessonCount = units.sumOf { it.lessons.size }
                )
            )
        }

        val manifestBuffer = Buffer()
        JsonWriter.of(manifestBuffer).use { writer ->
            writer.indent = "  "
            writer.beginObject()
            writer.name("version").value(1)
            writer.name("subjects")
            writer.beginArray()
            for (entry in manifestEntries) {
                writer.beginObject()
                writer.name("subjectId").value(entry.subjectId)
                writer.name("title").value(entry.title)
                writer.name("category").value(entry.category)
                writer.name("assetPath").value(entry.assetPath)
                writer.name("moduleCount").value(entry.moduleCount)
                writer.name("lessonCount").value(entry.lessonCount)
                writer.endObject()
            }
            writer.endArray()
            writer.endObject()
        }
        File(outputDir, "manifest.json").writeText(manifestBuffer.readUtf8(), Charsets.UTF_8)

        return manifestEntries
    }

    internal fun parseManifestJson(rawJson: String): List<SubjectAssetManifestEntry> {
        val entries = mutableListOf<SubjectAssetManifestEntry>()
        val buffer = Buffer().writeUtf8(rawJson)
        JsonReader.of(buffer).use { reader ->
            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "subjects" -> {
                        reader.beginArray()
                        while (reader.hasNext()) {
                            var subjectId = ""
                            var title = ""
                            var category = ""
                            var assetPath = ""
                            var moduleCount = 0
                            var lessonCount = 0
                            reader.beginObject()
                            while (reader.hasNext()) {
                                when (reader.nextName()) {
                                    "subjectId" -> subjectId = reader.nextString()
                                    "title" -> title = reader.nextString()
                                    "category" -> category = reader.nextString()
                                    "assetPath" -> assetPath = reader.nextString()
                                    "moduleCount" -> moduleCount = reader.nextInt()
                                    "lessonCount" -> lessonCount = reader.nextInt()
                                    else -> reader.skipValue()
                                }
                            }
                            reader.endObject()
                            entries.add(
                                SubjectAssetManifestEntry(subjectId, title, category, assetPath, moduleCount, lessonCount)
                            )
                        }
                        reader.endArray()
                    }
                    else -> reader.skipValue()
                }
            }
            reader.endObject()
        }
        return entries
    }

    private fun <T> paginateList(allItems: List<T>, page: Int, pageSize: Int): CurriculumPage<T> {
        val totalItems = allItems.size
        val totalPages = if (totalItems == 0) 0 else (totalItems + pageSize - 1) / pageSize
        val startIndex = (page * pageSize).coerceAtMost(totalItems)
        val endIndex = (startIndex + pageSize).coerceAtMost(totalItems)
        val slice = if (startIndex < endIndex) allItems.subList(startIndex, endIndex) else emptyList()
        return CurriculumPage(
            items = slice,
            page = page,
            pageSize = pageSize,
            totalItems = totalItems,
            totalPages = totalPages,
            hasNextPage = endIndex < totalItems
        )
    }

    private fun loadFallbackUnitsFromKotlin(subjectId: String): List<LearningUnit> {
        return when (subjectId) {
            "python" -> (
                PythonCurriculum.getUnitsForTier(DifficultyTier.BASIC) +
                    PythonCurriculum.getUnitsForTier(DifficultyTier.INTERMEDIATE) +
                    PythonCurriculum.getUnitsForTier(DifficultyTier.ADVANCED)
                )
            "basic_math" -> CalculusCurriculum.getUnitsForModule("Matemática Básica")
            "calculus" -> (
                CalculusCurriculum.getUnitsForModule("Limites") +
                    CalculusCurriculum.getUnitsForModule("Derivadas") +
                    CalculusCurriculum.getUnitsForModule("Integrais")
                )
            "enem_math" -> EnemMathCurriculum.getAllModules().map { module ->
                LearningUnit(
                    id = "unit_${module.id}",
                    unitNumber = module.moduleNumber,
                    tier = DifficultyTier.BASIC,
                    title = module.title,
                    description = module.summary,
                    lessons = module.lessons.map { it.toAppLesson() },
                    category = "Matemática",
                    subject = "Matemática ENEM"
                )
            }
            "physics" -> EnemPhysicsCurriculum.getAllModules().map { module ->
                LearningUnit(
                    id = "unit_${module.id}",
                    unitNumber = module.moduleNumber,
                    tier = DifficultyTier.BASIC,
                    title = module.title,
                    description = module.summary,
                    lessons = module.lessons.map { it.toAppLesson() },
                    category = "Física",
                    subject = "Ciências da Natureza"
                )
            }
            "chemistry" -> EnemChemistryCurriculum.getAllModules().map { module ->
                LearningUnit(
                    id = "unit_${module.id}",
                    unitNumber = module.moduleNumber,
                    tier = DifficultyTier.BASIC,
                    title = module.title,
                    description = module.summary,
                    lessons = module.lessons.map { it.toAppLesson() },
                    category = "Química",
                    subject = "Ciências da Natureza"
                )
            }
            "biology" -> EnemBiologyCurriculum.getAllModules().map { module ->
                LearningUnit(
                    id = "unit_${module.id}",
                    unitNumber = module.moduleNumber,
                    tier = DifficultyTier.BASIC,
                    title = module.title,
                    description = module.summary,
                    lessons = module.lessons.map { it.toAppLesson() },
                    category = "Biologia",
                    subject = "Ciências da Natureza"
                )
            }
            "history" -> EnemHistoryCurriculum.getAllModules().map { module ->
                LearningUnit(
                    id = module.id,
                    unitNumber = module.moduleNumber,
                    tier = DifficultyTier.BASIC,
                    title = module.title,
                    description = module.summary,
                    lessons = module.lessons.map { it.toAppLesson() },
                    category = "História",
                    subject = "Ciências Humanas"
                )
            }
            "geography" -> EnemGeographyCurriculum.getAllModules().map { module ->
                LearningUnit(
                    id = module.id,
                    unitNumber = module.moduleNumber,
                    tier = DifficultyTier.BASIC,
                    title = module.title,
                    description = module.summary,
                    lessons = module.lessons.map { it.toAppLesson() },
                    category = "Geografia",
                    subject = "Ciências Humanas"
                )
            }
            "languages" -> EnemLanguagesCurriculum.getAllModules().map { module ->
                LearningUnit(
                    id = module.id,
                    unitNumber = module.moduleNumber,
                    tier = DifficultyTier.BASIC,
                    title = module.title,
                    description = module.summary,
                    lessons = module.lessons.map { it.toAppLesson() },
                    category = "Linguagens",
                    subject = "Linguagens e Códigos"
                )
            }
            "transpetro" -> com.example.data.transpetro.TranspetroCurriculum.allDisciplines.flatMap { disc ->
                disc.modules.map { module ->
                    LearningUnit(
                        id = module.id,
                        unitNumber = module.moduleNumber,
                        tier = DifficultyTier.INTERMEDIATE,
                        title = "${disc.name} • ${module.title}",
                        description = module.summary,
                        lessons = module.lessons.map { it.toAppLesson() },
                        category = "Concurso Transpetro",
                        subject = disc.name
                    )
                }
            }
            else -> emptyList()
        }
    }

    private fun displayTitleForSubject(subjectId: String): String = when (subjectId) {
        "python" -> "Programação Python"
        "basic_math" -> "Matemática Básica & Fundamentos"
        "calculus" -> "Cálculo I (Limites, Derivadas e Integrais)"
        "enem_math" -> "Matemática e suas Tecnologias ENEM"
        "physics" -> "Física • Ciências da Natureza ENEM"
        "chemistry" -> "Química • Ciências da Natureza ENEM"
        "biology" -> "Biologia • Ciências da Natureza ENEM"
        "history" -> "História • Ciências Humanas ENEM"
        "geography" -> "Geografia • Ciências Humanas ENEM"
        "languages" -> "Linguagens, Códigos e suas Tecnologias ENEM"
        "transpetro" -> "Concurso Transpetro - ADM e Controle"
        else -> subjectId
    }

    private fun categoryForSubject(subjectId: String): String = when (subjectId) {
        "python" -> "Programação"
        "basic_math", "calculus", "enem_math" -> "Matemática"
        "physics" -> "Física"
        "chemistry" -> "Química"
        "biology" -> "Biologia"
        "history" -> "História"
        "geography" -> "Geografia"
        "languages" -> "Linguagens"
        "transpetro" -> "Concursos"
        else -> "Geral"
    }
}
