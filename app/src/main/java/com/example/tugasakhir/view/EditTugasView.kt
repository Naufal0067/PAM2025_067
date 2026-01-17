package com.example.tugasakhir.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tugasakhir.R
import com.example.tugasakhir.viewmodel.EditTugasViewModel
import com.example.tugasakhir.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTugasView(
    idTugas: Int,
    navController: NavController,
    viewModel: EditTugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    // Mengonversi StateFlow dari ViewModel menjadi State yang bisa dibaca Compose
    val uiState by viewModel.uiState.collectAsState()

    // Memuat data tugas berdasarkan ID saat pertama kali View ini muncul
    LaunchedEffect(idTugas) {
        viewModel.loadTugas(idTugas)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.edit_tugas)) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Field Judul Tugas
            TextField(
                value = uiState.judulTugas,
                onValueChange = { viewModel.onJudulChange(it) },
                label = { Text(stringResource(R.string.nama_tugas)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Field Deskripsi
            TextField(
                value = uiState.deskripsi,
                onValueChange = { viewModel.onDeskripsiChange(it) },
                label = { Text("Deskripsi Tugas") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            // Field Tenggat Waktu
            TextField(
                value = uiState.tenggat,
                onValueChange = { viewModel.onTenggatChange(it) },
                label = { Text("Tenggat Waktu") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Simpan
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.updateTugas {
                        // Kembali ke halaman sebelumnya setelah berhasil update
                        navController.popBackStack()
                    }
                }
            ) {
                Text(stringResource(R.string.btn_simpan))
            }
        }
    }
}