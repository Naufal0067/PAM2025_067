package com.example.tugasakhir.room


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal")
data class JadwalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mataPelajaran: String,
    val hari: String,
    val jam: String
)