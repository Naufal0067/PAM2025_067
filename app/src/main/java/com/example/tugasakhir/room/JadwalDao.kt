package com.example.tugasakhir.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JadwalDao {
    @Query("SELECT * FROM jadwal")
    fun getAllJadwal(): Flow<List<JadwalEntity>>
    @Query("SELECT * FROM jadwal WHERE id = :id")
    suspend fun getJadwalById(id: Int): JadwalEntity
    @Insert
    suspend fun insertJadwal(jadwal: JadwalEntity)
    @Update
    suspend fun updateJadwal(jadwal: JadwalEntity)

    @Delete
    suspend fun deleteJadwal(jadwal: JadwalEntity)
}