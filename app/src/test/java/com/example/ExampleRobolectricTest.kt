package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.UserProgressRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Knowverse", appName)
    }

    @Test
    fun `test user progress persistence with context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repository = UserProgressRepository(context)
        val initialProgress = repository.progressState.value
        assertNotNull(initialProgress)
        assertEquals(1, initialProgress.currentStreak)

        repository.completeLesson("py_b1_l1", 20)
        val updated = repository.progressState.value
        assertEquals(20, updated.totalXp)
        assertEquals(true, updated.completedLessonIds.contains("py_b1_l1"))
    }
}
