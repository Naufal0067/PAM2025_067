package com.example.tugasakhir.repositori

import com.example.tugasakhir.room.*
import kotlinx.coroutines.flow.Flow

class UserRepositori(
    private val userDao: UserDao,
    private val jadwalDao: JadwalDao,
    private val tugasDao: TugasDao
) {

    /* ================= USER ================= */

    suspend fun login(username: String, password: String): UserEntity? {
        return userDao.login(username, password)
    }

    /* ================= JADWAL ================= */

    fun getAllJadwal(): Flow<List<JadwalEntity>> {
        return jadwalDao.getAllJadwal()
    }

    suspend fun getJadwalById(id: Int): JadwalEntity {
        return jadwalDao.getJadwalById(id)
    }

    suspend fun insertJadwal(jadwal: JadwalEntity) {
        jadwalDao.insertJadwal(jadwal)
    }

    suspend fun updateJadwal(jadwal: JadwalEntity) {
        jadwalDao.updateJadwal(jadwal)
    }

    suspend fun deleteJadwal(jadwal: JadwalEntity) {
        jadwalDao.deleteJadwal(jadwal)
    }

    /* ================= TUGAS ================= */

    fun getAllTugas(): Flow<List<TugasEntity>> {
        return tugasDao.getAllTugas()
    }

    suspend fun getTugasById(id: Int): TugasEntity {
        return tugasDao.getTugasById(id)
    }

    suspend fun insertTugas(tugas: TugasEntity) {
        tugasDao.insertTugas(tugas)
    }

    suspend fun updateTugas(tugas: TugasEntity) {
        tugasDao.updateTugas(tugas)
    }

    suspend fun deleteTugas(tugas: TugasEntity) {
        tugasDao.deleteTugas(tugas)
    }
}
