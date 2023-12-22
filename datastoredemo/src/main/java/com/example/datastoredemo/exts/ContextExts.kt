package com.example.datastoredemo.exts

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastoredemo.PersonPreferences
import com.example.datastoredemo.data.PersonSerializer

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_info")

val Context.personDataStore: DataStore<PersonPreferences> by dataStore(
    fileName = "person.pb", serializer = PersonSerializer
)