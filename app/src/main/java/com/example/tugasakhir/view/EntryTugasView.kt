package com.example.tugasakhir.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tugasakhir.viewmodel.TugasViewModel
import com.example.tugasakhir.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryTugasView(
    navController: NavController,
    viewModel: TugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    // Pastikan nama state di ViewModel adalah 'tugasUiState' atau sesuaikan
    val uiState = viewModel.entryUiState
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tambah Tugas") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = uiState.tugasEvent.judulTugas,
                onValueChange = {
                    viewModel.updateUiState(uiState.tugasEvent.copy(judulTugas = it))
                },
                label = { Text("Judul Tugas") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = uiState.tugasEvent.deskripsi,
                onValueChange = {
                    viewModel.updateUiState(uiState.tugasEvent.copy(deskripsi = it))
                },
                label = { Text("Deskripsi") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = uiState.tugasEvent.tenggat,
                onValueChange = {
                    viewModel.updateUiState(uiState.tugasEvent.copy(tenggat = it))
                },
                label = { Text("Tenggat") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveTugas()
                        navController.navigateUp() // Lebih aman daripada popBackStack langsung
                    }
                }
            ) {
                Text("Simpan")
            }
        }
    }
}