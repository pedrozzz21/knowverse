package com.example.data.local.assets

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File

class CurriculumAssetLoaderTest {

    @Before
    fun setUp() {
        CurriculumAssetLoader.clearMemoryCache()
    }

    @Test
    fun `loadManifest returns all 10 supported curricula with non-zero modules and lessons`() {
        val manifest = CurriculumAssetLoader.loadManifest(context = null)
        assertEquals(10, manifest.size)
        for (entry in manifest) {
            assertTrue("Subject ${entry.subjectId} should have modules", entry.moduleCount > 0)
            assertTrue("Subject ${entry.subjectId} should have lessons", entry.lessonCount > 0)
        }
    }

    @Test
    fun `loadLessonsPage paginates lessons on demand accurately`() {
        val page0 = CurriculumAssetLoader.loadLessonsPage(
            context = null,
            subjectId = "enem_math",
            page = 0,
            pageSize = 5
        )
        assertEquals(5, page0.items.size)
        assertEquals(0, page0.page)
        assertTrue(page0.totalItems >= 20)
        assertTrue(page0.hasNextPage)

        val page1 = CurriculumAssetLoader.loadLessonsPage(
            context = null,
            subjectId = "enem_math",
            page = 1,
            pageSize = 5
        )
        assertEquals(5, page1.items.size)
        assertEquals(1, page1.page)
        assertFalse(
            "Page 0 and Page 1 must contain distinct lessons",
            page0.items.map { it.id }.intersect(page1.items.map { it.id }.toSet()).isNotEmpty()
        )
    }

    @Test
    fun `serialize and deserialize units round-trip preserves lessons and exercises`() {
        val originalUnits = CurriculumAssetLoader.loadAllUnitsForSubject(null, "physics")
        assertTrue(originalUnits.isNotEmpty())

        val json = CurriculumAssetLoader.serializeUnitsToJson("physics", originalUnits)
        val restoredUnits = CurriculumAssetLoader.deserializeUnitsFromJson(json)

        assertEquals(originalUnits.size, restoredUnits.size)
        assertEquals(
            originalUnits.sumOf { it.lessons.size },
            restoredUnits.sumOf { it.lessons.size }
        )
        val firstOrigLesson = originalUnits.first().lessons.first()
        val firstRestoredLesson = restoredUnits.first().lessons.first()
        assertEquals(firstOrigLesson.id, firstRestoredLesson.id)
        assertEquals(firstOrigLesson.title, firstRestoredLesson.title)
        assertEquals(firstOrigLesson.mathExercises.size, firstRestoredLesson.mathExercises.size)
    }

    @Test
    fun `exportAllCurriculaToDirectory generates manifest and per-subject json assets`() {
        val projectAssetsDir = File("src/main/assets/curricula").let { rel ->
            if (rel.parentFile?.exists() == true) rel else File("app/src/main/assets/curricula")
        }
        val exported = CurriculumAssetLoader.exportAllCurriculaToDirectory(projectAssetsDir)
        assertEquals(10, exported.size)

        val manifestFile = File(projectAssetsDir, "manifest.json")
        assertTrue("manifest.json must exist", manifestFile.exists())

        val parsedManifest = CurriculumAssetLoader.parseManifestJson(manifestFile.readText(Charsets.UTF_8))
        assertEquals(10, parsedManifest.size)

        for (subjectId in CurriculumAssetLoader.supportedSubjectIds) {
            val subjectFile = File(projectAssetsDir, "$subjectId.json")
            assertTrue("$subjectId.json must exist", subjectFile.exists())
            assertTrue("$subjectId.json must not be empty", subjectFile.length() > 100)
        }
    }
}

