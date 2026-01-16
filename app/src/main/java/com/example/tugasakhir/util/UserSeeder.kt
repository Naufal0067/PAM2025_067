package com.example.tugasakhir.util

import android.content.Context
import com.example.tugasakhir.room.AppDatabase
import com.example.tugasakhir.room.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object UserSeeder {

    fun seed(context: Context) {
        val dao = AppDatabase.getDatabase(context).userDao()

        CoroutineScope(Dispatchers.IO).launch {
            if (dao.countUser() == 0) {

                // Guru
                dao.insert(
                    UserEntity(
                        username = "guru",
                        password = "123",
                        role = "guru"
                    )
                )

                // Siswa
                dao.insert(
                    UserEntity(
                        username = "siswa",
                        password = "123",
                        role = "siswa"
                    )
                )
            }
        }
    }
}
