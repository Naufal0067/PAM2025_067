package com.example.tugasakhir.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SubmitTugasDao {

    @Insert
    suspend fun insert(submit: SubmitTugasEntity)

    @Query("SELECT * FROM submit_tugas WHERE tugasId = :tugasId")
    fun getByTugasId(tugasId: Int): Flow<List<SubmitTugasEntity>>
}
