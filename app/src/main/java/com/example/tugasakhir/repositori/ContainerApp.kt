package com.example.tugasakhir

import android.app.Application
import android.content.Context
import com.example.tugasakhir.repositori.UserRepositori
import com.example.tugasakhir.room.AppDatabase

// 1. Interface Container
interface AppContainer {
    val userRepositori: UserRepositori
}

// 2. Implementasi Container (Menggabungkan Database & Repo)
class DefaultAppContainer(private val context: Context) : AppContainer {

    // Inisialisasi Database
    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }

    // Inisialisasi Repositori dengan menyuntikkan semua DAO
    override val userRepositori: UserRepositori by lazy {
        UserRepositori(
            userDao = database.userDao(),
            jadwalDao = database.jadwalDao(),
            tugasDao = database.tugasDao(),
            submitTugasDao = database.submitTugasDao()
        )
    }
}

// 3. Class Application
class TugasAkhirApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // Mengirimkan 'this' (context) ke DefaultAppContainer
        this.container = DefaultAppContainer(this)
    }
}