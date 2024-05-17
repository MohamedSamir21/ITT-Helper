package com.example.itthelper.core.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.itthelper.core.data.source.local.DataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_pref")

class DataStoreRepositoryImpl(
    context: Context
) : DataStoreRepository {

    private val datastore = context.dataStore

    private object PreferencesKey {
        val welcomeKey = booleanPreferencesKey("welcome_done")
        val loginKey = booleanPreferencesKey("login_done")
        val accessTokenKey = stringPreferencesKey("access_token")
        val refreshTokenKey = stringPreferencesKey("refresh_token")
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

    override suspend fun saveOnLoginDone(done: Boolean) {
        datastore.edit { preferences ->
            preferences[PreferencesKey.loginKey] = done
        }
    }


    override suspend fun readLoginDoneStatus(): Boolean {
        val loginStatus = datastore.data.map { preferences ->
            preferences[PreferencesKey.loginKey]
        }.first()
        return loginStatus ?: false
    }

    override suspend fun saveAccessToken(token: String) {
        datastore.edit { preferences ->
            preferences[PreferencesKey.accessTokenKey] = token
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        datastore.edit { preferences ->
            preferences[PreferencesKey.refreshTokenKey] = token
        }
    }

    override suspend fun readAccessToken(): String? {
        val accessToken = datastore.data.map { preferences ->
            preferences[PreferencesKey.accessTokenKey]
        }.first()
        return accessToken
    }

    override suspend fun readRefreshToken(): String? {
        val refreshToken = datastore.data.map { preferences ->
            preferences[PreferencesKey.refreshTokenKey]
        }.first()
        return refreshToken
    }
}