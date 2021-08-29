package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CacheDao {
    @Insert
    fun saveCache(cache: Cache)

    @Query("select * from Cache where name = :dbname")
    fun getCache(dbname: String): Cache?


}