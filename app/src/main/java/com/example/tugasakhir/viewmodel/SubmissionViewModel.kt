package com.example.tugasakhir.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir.repositori.UserRepositori
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SubmissionViewModel(
    repository: UserRepositori,
    tugasId: Int
) : ViewModel() {

    val submissions = repository
        .getSubmitTugasByTugasId(tugasId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )
}


