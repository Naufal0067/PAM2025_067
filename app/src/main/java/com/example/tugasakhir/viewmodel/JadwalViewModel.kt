package com.example.tugasakhir.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir.repositori.UserRepositori
import com.example.tugasakhir.room.JadwalEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JadwalViewModel(
    private val repository: UserRepositori
) : ViewModel() {

    val listJadwal = repository.getAllJadwal()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertJadwal(
        mapel: String,
        hari: String,
        jam: String
    ) {
        viewModelScope.launch {
            repository.insertJadwal(
                JadwalEntity(
                    mataPelajaran = mapel,
                    hari = hari,
                    jam = jam
                )
            )
        }
    }
}
