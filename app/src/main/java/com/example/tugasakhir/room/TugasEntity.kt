package com.example.tugasakhir.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tugas")
data class TugasEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judulTugas: String,
    val deskripsi: String,
    val tenggat: String
)