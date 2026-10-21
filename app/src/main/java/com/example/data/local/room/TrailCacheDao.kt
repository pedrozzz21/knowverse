package com.example.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Room offline cache of trails, units, and lessons.
 */
@Dao
interface TrailCacheDao {

    @Query("SELECT * FROM cached_trails ORDER BY category ASC, title ASC")
    fun getAllTrails(): Flow<List<CachedTrailEntity>>

    @Query("SELECT * FROM cached_trails WHERE category = :category ORDER BY title ASC")
    fun getTrailsByCategory(category: String): Flow<List<CachedTrailEntity>>

    @Query("SELECT * FROM cached_trails WHERE id = :trailId LIMIT 1")
    fun getTrailById(trailId: String): Flow<CachedTrailEntity?>

    @Query("SELECT * FROM cached_units WHERE trailId = :trailId ORDER BY unitNumber ASC")
    fun getUnitsForTrail(trailId: String): Flow<List<CachedUnitEntity>>

    @Query("SELECT * FROM cached_lessons WHERE unitId = :unitId ORDER BY id ASC")
    fun getLessonsForUnit(unitId: String): Flow<List<CachedLessonEntity>>

    @Query("SELECT * FROM cached_lessons WHERE trailId = :trailId ORDER BY unitNumber ASC, id ASC")
    fun getLessonsForTrail(trailId: String): Flow<List<CachedLessonEntity>>

    @Query("SELECT * FROM cached_lessons ORDER BY id ASC")
    fun getAllLessons(): Flow<List<CachedLessonEntity>>

    @Query("SELECT * FROM cached_lessons WHERE id = :lessonId LIMIT 1")
    suspend fun getLessonById(lessonId: String): CachedLessonEntity?

    @Query("SELECT * FROM cached_lessons WHERE id = :lessonId LIMIT 1")
    fun getLessonFlowById(lessonId: String): Flow<CachedLessonEntity?>

    @Query("SELECT COUNT(*) FROM cached_lessons")
    fun getCachedLessonCountFlow(): Flow<Int>

    @Query("SELECT COUNT(*) FROM cached_lessons")
    suspend fun getCachedLessonCount(): Int

    @Query("SELECT COUNT(*) FROM cached_trails")
    suspend fun getCachedTrailCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrails(trails: List<CachedTrailEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnits(units: List<CachedUnitEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<CachedLessonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: CachedLessonEntity)

    @Query("DELETE FROM cached_lessons WHERE id = :lessonId")
    suspend fun deleteLesson(lessonId: String)

    @Query("DELETE FROM cached_lessons")
    suspend fun clearLessons()

    @Query("DELETE FROM cached_units")
    suspend fun clearUnits()

    @Query("DELETE FROM cached_trails")
    suspend fun clearTrails()
}
