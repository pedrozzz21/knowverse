package com.example.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Database entity representing a cached Learning Trail (e.g., Python Básico, Cálculo Limites).
 * Enables offline discovery and browsing when network is disconnected.
 */
@Entity(tableName = "cached_trails")
data class CachedTrailEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val category: String, // "Programação", "Matemática"
    val subject: String,  // "Python", "Cálculo", "Matemática Básica"
    val tierOrModule: String, // "BASIC", "INTERMEDIATE", "ADVANCED", "Limites", etc.
    val totalLessons: Int,
    val estimatedHours: String,
    val isAvailable: Boolean = true,
    val lastCachedTimestamp: Long = System.currentTimeMillis()
)

/**
 * Room Database entity representing a cached Learning Unit within a Trail.
 */
@Entity(tableName = "cached_units")
data class CachedUnitEntity(
    @PrimaryKey val id: String,
    val trailId: String,
    val unitNumber: Int,
    val title: String,
    val description: String,
    val tier: String,
    val category: String,
    val subject: String,
    val lessonCount: Int
)

/**
 * Room Database entity representing a cached Lesson with full theory, code, and exercises.
 * Enables complete offline studying and exercise solving without internet connection.
 */
@Entity(tableName = "cached_lessons")
data class CachedLessonEntity(
    @PrimaryKey val id: String,
    val trailId: String,
    val unitId: String,
    val unitTitle: String,
    val unitNumber: Int,
    val title: String,
    val conceptTitle: String,
    val explanation: String,
    val lessonType: String, // "LEARN", "PRACTICE", "CHALLENGE"
    val initialCode: String,
    val hintsJson: String,
    val solutionExplanation: String,
    val xpReward: Int = 25,
    val theorySection: String = "",
    val testCasesJson: String = "[]",
    val mathExercisesJson: String = "[]",
    val formulasJson: String = "[]",
    val examplesJson: String = "[]",
    val isSavedOffline: Boolean = true,
    val lastCachedTimestamp: Long = System.currentTimeMillis()
)
