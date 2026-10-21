package com.example.data.local.room

import android.content.Context
import com.example.data.math.CalculusCurriculum
import com.example.data.model.DifficultyTier
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LearningUnit
import com.example.data.model.Lesson
import com.example.data.model.LessonExample
import com.example.data.model.LessonType
import com.example.data.model.MathExercise
import com.example.data.model.TestCase
import com.example.data.model.ValidationType
import com.example.data.python.PythonCurriculum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

/**
 * Repository abstracting Room local persistence and caching for Trails and Lessons.
 * Ensures users can access, read theory, and solve exercises even when offline.
 */
class TrailCacheRepository(
    private val dao: TrailCacheDao,
    private val context: Context
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val allCachedTrails: Flow<List<CachedTrailEntity>> = dao.getAllTrails()
    val allCachedLessons: Flow<List<CachedLessonEntity>> = dao.getAllLessons()
    val cachedLessonCount: Flow<Int> = dao.getCachedLessonCountFlow()

    init {
        // Automatically pre-populate cache on first launch so all tracks are immediately offline-ready
        scope.launch {
            prepopulateCacheIfNeeded()
        }
    }

    suspend fun prepopulateCacheIfNeeded() = withContext(Dispatchers.IO) {
        try {
            val count = dao.getCachedLessonCount()
            if (count == 0) {
                cacheAllCurricula()
            }
        } catch (_: Exception) {}
    }

    suspend fun forceRefreshCache(): Int = withContext(Dispatchers.IO) {
        cacheAllCurricula()
    }

    private suspend fun cacheAllCurricula(): Int = withContext(Dispatchers.IO) {
        val trailsToInsert = mutableListOf<CachedTrailEntity>()
        val unitsToInsert = mutableListOf<CachedUnitEntity>()
        val lessonsToInsert = mutableListOf<CachedLessonEntity>()

        // 1. Python Trails (Basic, Intermediate, Advanced)
        val pythonTiers = listOf(
            Triple(DifficultyTier.BASIC, "trail_python_basic", "Python: Fundamentos & Estruturas"),
            Triple(DifficultyTier.INTERMEDIATE, "trail_python_intermediate", "Python: Algoritmos & Manipulações"),
            Triple(DifficultyTier.ADVANCED, "trail_python_advanced", "Python: POO & Padrões Avançados")
        )

        for ((tier, trailId, title) in pythonTiers) {
            val units = PythonCurriculum.getUnitsForTier(tier)
            val totalLessonsInTrail = units.sumOf { it.lessons.size }

            trailsToInsert.add(
                CachedTrailEntity(
                    id = trailId,
                    title = title,
                    description = "Trilha completa com teoria, exemplos e terminal interativo offline.",
                    category = "Programação",
                    subject = "Python",
                    tierOrModule = tier.name,
                    totalLessons = totalLessonsInTrail,
                    estimatedHours = "${totalLessonsInTrail * 15 / 60}h",
                    isAvailable = true
                )
            )

            for (unit in units) {
                unitsToInsert.add(
                    CachedUnitEntity(
                        id = unit.id,
                        trailId = trailId,
                        unitNumber = unit.unitNumber,
                        title = unit.title,
                        description = unit.description,
                        tier = unit.tier.name,
                        category = unit.category,
                        subject = unit.subject,
                        lessonCount = unit.lessons.size
                    )
                )

                for (lesson in unit.lessons) {
                    lessonsToInsert.add(mapLessonToEntity(lesson, trailId, unit))
                }
            }
        }

        // 2. Basic Math Trail
        val mathUnits = CalculusCurriculum.getUnitsForModule("Matemática Básica")
        val totalMathLessons = mathUnits.sumOf { it.lessons.size }
        val mathTrailId = "trail_math_basic"

        trailsToInsert.add(
            CachedTrailEntity(
                id = mathTrailId,
                title = "Matemática Básica & Fundamentos",
                description = "Álgebra, fatoração, trigonometria e funções pré-cálculo com passo a passo.",
                category = "Matemática",
                subject = "Matemática Básica",
                tierOrModule = "Matemática Básica",
                totalLessons = totalMathLessons,
                estimatedHours = "${totalMathLessons * 20 / 60}h",
                isAvailable = true
            )
        )

        for (unit in mathUnits) {
            unitsToInsert.add(
                CachedUnitEntity(
                    id = unit.id,
                    trailId = mathTrailId,
                    unitNumber = unit.unitNumber,
                    title = unit.title,
                    description = unit.description,
                    tier = unit.tier.name,
                    category = "Matemática",
                    subject = "Matemática Básica",
                    lessonCount = unit.lessons.size
                )
            )
            for (lesson in unit.lessons) {
                lessonsToInsert.add(mapLessonToEntity(lesson, mathTrailId, unit))
            }
        }

        // 3. Calculus Trails (Limites, Derivadas, Integrais)
        val calculusModules = listOf(
            "Limites" to "trail_calc_limits",
            "Derivadas" to "trail_calc_derivatives",
            "Integrais" to "trail_calc_integrals"
        )

        for ((moduleName, trailId) in calculusModules) {
            val units = CalculusCurriculum.getUnitsForModule(moduleName)
            val totalLessonsInTrail = units.sumOf { it.lessons.size }

            trailsToInsert.add(
                CachedTrailEntity(
                    id = trailId,
                    title = "Cálculo: $moduleName",
                    description = "Teoria visual, teoremas, demonstrações e resolução de problemas estruturados.",
                    category = "Matemática",
                    subject = "Cálculo",
                    tierOrModule = moduleName,
                    totalLessons = totalLessonsInTrail,
                    estimatedHours = "${totalLessonsInTrail * 25 / 60}h",
                    isAvailable = true
                )
            )

            for (unit in units) {
                unitsToInsert.add(
                    CachedUnitEntity(
                        id = unit.id,
                        trailId = trailId,
                        unitNumber = unit.unitNumber,
                        title = unit.title,
                        description = unit.description,
                        tier = unit.tier.name,
                        category = "Matemática",
                        subject = "Cálculo",
                        lessonCount = unit.lessons.size
                    )
                )
                for (lesson in unit.lessons) {
                    lessonsToInsert.add(mapLessonToEntity(lesson, trailId, unit))
                }
            }
        }

        dao.insertTrails(trailsToInsert)
        dao.insertUnits(unitsToInsert)
        dao.insertLessons(lessonsToInsert)

        lessonsToInsert.size
    }

    suspend fun getCachedLesson(lessonId: String): Lesson? = withContext(Dispatchers.IO) {
        val entity = dao.getLessonById(lessonId) ?: return@withContext null
        mapEntityToLesson(entity)
    }

    fun observeCachedLesson(lessonId: String): Flow<Lesson?> {
        return dao.getLessonFlowById(lessonId).map { entity ->
            entity?.let { mapEntityToLesson(it) }
        }
    }

    fun getLessonsForUnit(unitId: String): Flow<List<Lesson>> {
        return dao.getLessonsForUnit(unitId).map { list ->
            list.map { mapEntityToLesson(it) }
        }
    }

    fun getLessonsForTrail(trailId: String): Flow<List<Lesson>> {
        return dao.getLessonsForTrail(trailId).map { list ->
            list.map { mapEntityToLesson(it) }
        }
    }

    // --- JSON Converters ---

    private fun mapLessonToEntity(lesson: Lesson, trailId: String, unit: LearningUnit): CachedLessonEntity {
        val hintsJson = JSONArray(lesson.hints).toString()

        val testCasesArray = JSONArray()
        for (tc in lesson.testCases) {
            val obj = JSONObject()
            obj.put("name", tc.name)
            obj.put("stdinInput", tc.stdinInput)
            obj.put("expectedOutput", tc.expectedOutput)
            obj.put("validationType", tc.validationType.name)
            obj.put("isHidden", tc.isHidden)
            testCasesArray.put(obj)
        }

        val mathExercisesArray = JSONArray()
        for (me in lesson.mathExercises) {
            val obj = JSONObject()
            obj.put("id", me.id)
            obj.put("title", me.title)
            obj.put("problemStatement", me.problemStatement)
            obj.put("options", JSONArray(me.options))
            obj.put("correctIndex", me.correctIndex)
            obj.put("hint", me.hint)
            obj.put("stepByStepSolution", me.stepByStepSolution)
            mathExercisesArray.put(obj)
        }

        val formulasArray = JSONArray()
        for (f in lesson.formulasOrSyntax) {
            val obj = JSONObject()
            obj.put("termOrFunction", f.termOrFunction)
            obj.put("syntax", f.syntax)
            obj.put("description", f.description)
            obj.put("parametersInfo", f.parametersInfo)
            formulasArray.put(obj)
        }

        val examplesArray = JSONArray()
        for (ex in lesson.examples) {
            val obj = JSONObject()
            obj.put("title", ex.title)
            obj.put("codeOrFormula", ex.codeOrFormula)
            obj.put("explanation", ex.explanation)
            examplesArray.put(obj)
        }

        return CachedLessonEntity(
            id = lesson.id,
            trailId = trailId,
            unitId = lesson.unitId,
            unitTitle = unit.title,
            unitNumber = unit.unitNumber,
            title = lesson.title,
            conceptTitle = lesson.conceptTitle,
            explanation = lesson.explanation,
            lessonType = lesson.lessonType.name,
            initialCode = lesson.initialCode,
            hintsJson = hintsJson,
            solutionExplanation = lesson.solutionExplanation,
            xpReward = lesson.xpReward,
            theorySection = lesson.theorySection,
            testCasesJson = testCasesArray.toString(),
            mathExercisesJson = mathExercisesArray.toString(),
            formulasJson = formulasArray.toString(),
            examplesJson = examplesArray.toString(),
            isSavedOffline = true
        )
    }

    private fun mapEntityToLesson(entity: CachedLessonEntity): Lesson {
        val hints = mutableListOf<String>()
        try {
            val arr = JSONArray(entity.hintsJson)
            for (i in 0 until arr.length()) {
                hints.add(arr.getString(i))
            }
        } catch (_: Exception) {}

        val testCases = mutableListOf<TestCase>()
        try {
            val arr = JSONArray(entity.testCasesJson)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                testCases.add(
                    TestCase(
                        name = obj.optString("name", "Teste"),
                        stdinInput = obj.optString("stdinInput", ""),
                        expectedOutput = obj.optString("expectedOutput", ""),
                        validationType = try {
                            ValidationType.valueOf(obj.optString("validationType", "EXACT_OUTPUT"))
                        } catch (_: Exception) {
                            ValidationType.EXACT_OUTPUT
                        },
                        isHidden = obj.optBoolean("isHidden", false)
                    )
                )
            }
        } catch (_: Exception) {}

        val mathExercises = mutableListOf<MathExercise>()
        try {
            val arr = JSONArray(entity.mathExercisesJson)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val optsArr = obj.optJSONArray("options") ?: JSONArray()
                val options = mutableListOf<String>()
                for (j in 0 until optsArr.length()) {
                    options.add(optsArr.getString(j))
                }
                mathExercises.add(
                    MathExercise(
                        id = obj.optString("id", "ex_$i"),
                        title = obj.optString("title", "Exercício"),
                        problemStatement = obj.optString("problemStatement", ""),
                        options = options,
                        correctIndex = obj.optInt("correctIndex", 0),
                        hint = obj.optString("hint", ""),
                        stepByStepSolution = obj.optString("stepByStepSolution", "")
                    )
                )
            }
        } catch (_: Exception) {}

        val formulas = mutableListOf<FormulaOrSyntaxItem>()
        try {
            val arr = JSONArray(entity.formulasJson)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                formulas.add(
                    FormulaOrSyntaxItem(
                        termOrFunction = obj.optString("termOrFunction", ""),
                        syntax = obj.optString("syntax", ""),
                        description = obj.optString("description", ""),
                        parametersInfo = obj.optString("parametersInfo", "")
                    )
                )
            }
        } catch (_: Exception) {}

        val examples = mutableListOf<LessonExample>()
        try {
            val arr = JSONArray(entity.examplesJson)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                examples.add(
                    LessonExample(
                        title = obj.optString("title", ""),
                        codeOrFormula = obj.optString("codeOrFormula", ""),
                        explanation = obj.optString("explanation", "")
                    )
                )
            }
        } catch (_: Exception) {}

        return Lesson(
            id = entity.id,
            unitId = entity.unitId,
            title = entity.title,
            conceptTitle = entity.conceptTitle,
            explanation = entity.explanation,
            lessonType = try {
                LessonType.valueOf(entity.lessonType)
            } catch (_: Exception) {
                LessonType.LEARN
            },
            initialCode = entity.initialCode,
            hints = hints,
            solutionExplanation = entity.solutionExplanation,
            xpReward = entity.xpReward,
            testCases = testCases,
            mathExercises = mathExercises,
            theorySection = entity.theorySection,
            formulasOrSyntax = formulas,
            examples = examples
        )
    }
}
