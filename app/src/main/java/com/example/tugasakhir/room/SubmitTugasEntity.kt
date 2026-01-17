package com.example.tugasakhir.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "submit_tugas")
data class SubmitTugasEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tugasId: Int,
    val namaSiswa: String,
    val jawaban: String,
    val waktuSubmit: String
)