package com.example.tugasakhir.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir.repositori.UserRepositori
import com.example.tugasakhir.room.TugasEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class TugasViewModel(
    private val repository: UserRepositori
) : ViewModel() {

    // State untuk List Tugas
    val uiState: StateFlow<TugasUiState> =
        repository.getAllTugas()
            .map { TugasUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = TugasUiState()
            )

    // PERBAIKAN: Tambahkan import getValue & setValue agar 'by' tidak error
    var entryUiState by mutableStateOf(EntryTugasUiState())
        private set

    fun updateUiState(tugasEvent: TugasEvent) {
        entryUiState = EntryTugasUiState(tugasEvent = tugasEvent)
    }

    suspend fun saveTugas() {
        repository.insertTugas(entryUiState.tugasEvent.toTugasEntity())
    }

    // Fungsi Hapus Tugas
    fun deleteTugas(tugas: TugasEntity) {
        viewModelScope.launch {
            repository.deleteTugas(tugas)
        }
    }
}

// Data Classes
data class TugasUiState(
    val listTugas: List<TugasEntity> = emptyList()
)

data class EntryTugasUiState(
    val tugasEvent: TugasEvent = TugasEvent()
)

data class TugasEvent(
    val id: Int = 0,
    val judulTugas: String = "",
    val deskripsi: String = "",
    val tenggat: String = ""
)

// PERBAIKAN: Perhatikan posisi kurung kurawal fungsi ekstensi ini
fun TugasEvent.toTugasEntity(): TugasEntity = TugasEntity(
    id = id,
    judulTugas = judulTugas,
    deskripsi = deskripsi,
    tenggat = tenggat
)
