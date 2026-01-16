package com.example.tugasakhir.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 1. Daftarkan semua Entity di sini
// 2. Naikkan versi (version) menjadi 2 jika sebelumnya Anda hanya memiliki UserEntity
@Database(
    entities = [
        UserEntity::class,
        JadwalEntity::class,
        TugasEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // Daftarkan semua fungsi DAO agar bisa diakses oleh Repository
    abstract fun userDao(): UserDao
    abstract fun jadwalDao(): JadwalDao
    abstract fun tugasDao(): TugasDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tugasakhir_db"
                )
                    // Strategi migrasi: Menghapus database lama dan membuat baru jika versi naik
                    // Gunakan ini selama masa pengembangan agar tidak error saat ganti struktur tabel
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}