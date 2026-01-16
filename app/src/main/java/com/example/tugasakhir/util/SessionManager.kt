package com.example.tugasakhir.util

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    fun saveLogin(role: String) {
        prefs.edit()
            .putBoolean("is_login", true)
            .putString("role", role)
            .apply()
    }

    fun getRole(): String =
        prefs.getString("role",null ) ?: "siswa"

    fun isLogin(): Boolean =
        prefs.getBoolean("is_login", false)

    fun logout() {
        prefs.edit().clear().apply()
    }
}
