package com.example.tugasakhir.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tugasakhir.R
import com.example.tugasakhir.viewmodel.TugasViewModel
import com.example.tugasakhir.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryTugasView(
    navController: NavController,
    // Pastikan mengambil viewModel yang benar
    viewModel: TugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    // Ambil state dari ViewModel
    val entryUiState = viewModel.entryUiState
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Tugas") }
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
                value = entryUiState.tugasEvent.judulTugas,
                onValueChange = {
                    // Perbaikan: updateUiState menerima TugasEvent, bukan variabel uiState
                    viewModel.updateUiState(entryUiState.tugasEvent.copy(judulTugas = it))
                },
                label = { Text("Judul Tugas") },
                modifier = Modifier.fillMaxWidth()
            )

            // Field Deskripsi
            TextField(
                value = entryUiState.tugasEvent.deskripsi,
                onValueChange = {
                    viewModel.updateUiState(entryUiState.tugasEvent.copy(deskripsi = it))
                },
                label = { Text("Deskripsi") },
                modifier = Modifier.fillMaxWidth()
            )

            // Field Tenggat
            TextField(
                value = entryUiState.tugasEvent.tenggat,
                onValueChange = {
                    viewModel.updateUiState(entryUiState.tugasEvent.copy(tenggat = it))
                },
                label = { Text("Tenggat") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveTugas()
                        navController.popBackStack()
                    }
                }
            ) {
                Text("Simpan")
            }
        }
    }
}
