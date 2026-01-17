package com.example.tugasakhir.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir.repositori.UserRepositori
import com.example.tugasakhir.room.TugasEntity // Sesuaikan nama Entity tugasmu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditTugasViewModel(
    private val repository: UserRepositori
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditTugasUiState())
    val uiState: StateFlow<EditTugasUiState> = _uiState

    /** ================= LOAD DATA ================= */
    fun loadTugas(id: Int) {
        viewModelScope.launch {
            // Pastikan fungsi getTugasById(id) ada di UserRepositori
            val tugas = repository.getTugasById(id)

            _uiState.value = EditTugasUiState(
                idTugas = tugas.id,
                judulTugas = tugas.judulTugas,
                deskripsi = tugas.deskripsi,
                tenggat = tugas.tenggat
            )
        }
    }

    /** ================= ON CHANGE ================= */
    fun onJudulChange(value: String) {
        _uiState.value = _uiState.value.copy(judulTugas = value)
    }

    fun onDeskripsiChange(value: String) {
        _uiState.value = _uiState.value.copy(deskripsi = value)
    }

    fun onTenggatChange(value: String) {
        _uiState.value = _uiState.value.copy(tenggat = value)
    }

    /** ================= UPDATE ================= */
    fun updateTugas(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val state = _uiState.value

            repository.updateTugas(
                TugasEntity(
                    id = state.idTugas,
                    judulTugas = state.judulTugas,
                    deskripsi = state.deskripsi,
                    tenggat = state.tenggat
                )
            )
            onSuccess()
        }
    }
}

/* ================= UI STATE ================= */
data class EditTugasUiState(
    val idTugas: Int = 0,
    val judulTugas: String = "",
    val deskripsi: String = "",
    val tenggat: String = ""
)