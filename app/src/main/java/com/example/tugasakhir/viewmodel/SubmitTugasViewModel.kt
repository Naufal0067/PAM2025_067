package com.example.tugasakhir.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir.repositori.UserRepositori
import com.example.tugasakhir.room.SubmitTugasEntity
import kotlinx.coroutines.launch

class SubmitTugasViewModel(
    private val repository: UserRepositori
) : ViewModel() {

    fun submitTugas(
        tugasId: Int,
        namaSiswa: String,
        jawaban: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            repository.insertSubmitTugas(
                SubmitTugasEntity(
                    tugasId = tugasId,
                    namaSiswa = namaSiswa,
                    jawaban = jawaban,
                    waktuSubmit = System.currentTimeMillis().toString()
                )
            )
            onSuccess()
        }
    }
}
