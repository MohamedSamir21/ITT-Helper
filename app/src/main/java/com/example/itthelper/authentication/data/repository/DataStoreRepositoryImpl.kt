package com.example.itthelper.authentication.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.itthelper.authentication.data.local.DataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_pref")

class DataStoreRepositoryImpl(
    context: Context
): DataStoreRepository {

    private val datastore = context.dataStore

    private object PreferencesKey {
        val welcomeKey = booleanPreferencesKey("welcome_done")
        val loginKey = stringPreferencesKey("login_token")
    }

    override suspend fun saveOnWelcomeDone(done: Boolean) {
        datastore.edit { preferences ->
            preferences[PreferencesKey.welcomeKey] = done
        }
    }

    override suspend fun readWelcomeDoneStatus(): Boolean {
        val welcomeStatus = datastore.data.map { preferences ->
            preferences[PreferencesKey.welcomeKey]
        }.first()
        return welcomeStatus ?: false
    }

    override suspend fun saveTokenOnLoginDone(token: String) {
        datastore.edit { preferences ->
            preferences[PreferencesKey.loginKey] = token
        }
    }

    override suspend fun readLoginToken(): String? {
        val loginStatus = datastore.data.map { preferences ->
            preferences[PreferencesKey.loginKey]
        }.first()
        return loginStatus
    }
}