package com.example.tugasakhir.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tugasakhir.TugasAkhirApplication
import com.example.tugasakhir.viewmodel.EditJadwalViewModel
import com.example.tugasakhir.viewmodel.EditTugasViewModel
import com.example.tugasakhir.viewmodel.HomeViewModel
import com.example.tugasakhir.viewmodel.LoginViewModel
import com.example.tugasakhir.viewmodel.JadwalViewModel
import com.example.tugasakhir.viewmodel.SubmissionViewModel
import com.example.tugasakhir.viewmodel.SubmitTugasViewModel
import com.example.tugasakhir.viewmodel.TugasViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {

        // Initializer untuk LoginViewModel
        initializer {
            LoginViewModel(
                repository = tugasApp().container.userRepositori
            )
        }

        // Initializer untuk HomeViewModel
        initializer {
            HomeViewModel()
        }

        // Tambahkan JadwalViewModel di sini
        initializer {
            JadwalViewModel(
                repository = tugasApp().container.userRepositori
            )
        }

        // Tambahkan TugasViewModel di sini
        initializer {
            TugasViewModel(
                repository = tugasApp().container.userRepositori
            )
        }
        initializer {
            EditJadwalViewModel(
                repository = tugasApp().container.userRepositori
            )
        }
        // Tambahkan di dalam object PenyediaViewModel
        initializer {
            EditTugasViewModel(
                repository = tugasApp().container.userRepositori
            )
        }
        initializer {
            SubmitTugasViewModel(
                tugasApp().container.userRepositori // Pastikan repository-nya benar
            )
        }
        initializer {
            SubmissionViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                repository = tugasApp().container.userRepositori
            )
        }
    }
}

/**
 * Extension function ini wajib ada agar Factory bisa mengambil
 * data repository dari ContainerApp melalui context Application.
 */
fun CreationExtras.tugasApp(): TugasAkhirApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TugasAkhirApplication)