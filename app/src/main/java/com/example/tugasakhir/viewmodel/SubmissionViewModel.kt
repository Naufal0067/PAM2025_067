package com.example.tugasakhir.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir.repositori.UserRepositori
import com.example.tugasakhir.uicontroller.route.DestinasiDaftarSubmitTugas
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

// Di dalam SubmissionViewModel
class SubmissionViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: UserRepositori
) : ViewModel() {

    // Ambil kunci langsung dari objek Destinasi agar PASTI SAMA
    private val tugasId: Int = checkNotNull(savedStateHandle[DestinasiDaftarSubmitTugas.tugasIdArg])

    val submissions = repository
        .getSubmitTugasByTugasId(tugasId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}