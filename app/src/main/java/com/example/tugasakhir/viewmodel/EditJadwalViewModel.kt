package com.example.tugasakhir.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir.repositori.UserRepositori
import com.example.tugasakhir.room.JadwalEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditJadwalViewModel(
    private val repository: UserRepositori
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditJadwalUiState())
    val uiState: StateFlow<EditJadwalUiState> = _uiState

    /** ================= LOAD DATA ================= */
    fun loadJadwal(id: Int) {
        viewModelScope.launch {
            val jadwal = repository.getJadwalById(id)

            _uiState.value = EditJadwalUiState(
                id = jadwal.id,
                hari = jadwal.hari,
                mataPelajaran = jadwal.mataPelajaran,
                jam = jadwal.jam
            )
        }
    }

    /** ================= ON CHANGE ================= */
    fun onHariChange(value: String) {
        _uiState.value = _uiState.value.copy(hari = value)
    }

    fun onMapelChange(value: String) {
        _uiState.value = _uiState.value.copy(mataPelajaran = value)
    }

    fun onJamChange(value: String) {
        _uiState.value = _uiState.value.copy(jam = value)
    }

    /** ================= UPDATE ================= */
    fun updateJadwal(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val state = _uiState.value

            repository.updateJadwal(
                JadwalEntity(
                    id = state.id,
                    hari = state.hari,
                    mataPelajaran = state.mataPelajaran,
                    jam = state.jam
                )
            )

            onSuccess()
        }
    }
}

/* ================= UI STATE ================= */

data class EditJadwalUiState(
    val id: Int = 0,
    val hari: String = "",
    val mataPelajaran: String = "",
    val jam: String = ""
)
