package com.example.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Main Room Database for local caching of learning content.
 */
@Database(
    entities = [
        CachedTrailEntity::class,
        CachedUnitEntity::class,
        CachedLessonEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class KnowverseDatabase : RoomDatabase() {

    abstract fun trailCacheDao(): TrailCacheDao

    companion object {
        @Volatile
        private var INSTANCE: KnowverseDatabase? = null

        fun getInstance(context: Context): KnowverseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KnowverseDatabase::class.java,
                    "knowverse_offline_cache.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
