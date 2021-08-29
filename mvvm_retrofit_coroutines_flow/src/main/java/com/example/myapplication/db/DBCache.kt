package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Cache::class])
abstract class DBCache : RoomDatabase() {
    companion object {
        private var instance: DBCache? = null
        private const val DB_NAME = "cache"

        @Synchronized
        fun getDatabase(context: Context): DBCache {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                DBCache::class.java,
                DB_NAME
            ).build().apply {
                instance = this
            }
        }
    }

    abstract fun cacheDao(): CacheDao
}