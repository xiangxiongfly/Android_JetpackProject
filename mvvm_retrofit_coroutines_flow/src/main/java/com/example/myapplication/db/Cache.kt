package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cache(var name: String, var value: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}