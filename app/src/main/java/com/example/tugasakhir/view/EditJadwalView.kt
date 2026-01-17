package com.example.tugasakhir.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tugasakhir.R
import com.example.tugasakhir.viewmodel.EditJadwalViewModel
import com.example.tugasakhir.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditJadwalView(
    idJadwal: Int,
    navController: NavController,
    viewModel: EditJadwalViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    // Mengambil state dari ViewModel
    val state by viewModel.uiState.collectAsState()

    // Memuat data jadwal berdasarkan ID saat halaman dibuka
    LaunchedEffect(idJadwal) {
        viewModel.loadJadwal(idJadwal)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.edit_jadwal)) },
                // Menambahkan tombol kembali di TopAppBar
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp) // Memberi jarak antar field
        ) {
            // Field Mata Pelajaran
            TextField(
                value = state.mataPelajaran,
                onValueChange = { viewModel.onMapelChange(it) },
                label = { Text(stringResource(R.string.mata_pelajaran)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Field Hari
            TextField(
                value = state.hari,
                onValueChange = { viewModel.onHariChange(it) },
                label = { Text(stringResource(R.string.hari)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Field Jam
            TextField(
                value = state.jam,
                onValueChange = { viewModel.onJamChange(it) },
                label = { Text(stringResource(R.string.jam)) },
                placeholder = { Text("Contoh: 07:00 - 09:00") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f)) // Mendorong tombol ke bawah

            // Tombol Simpan
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.updateJadwal {
                        navController.popBackStack()
                    }
                }
            ) {
                Text(stringResource(R.string.btn_simpan))
            }
        }
    }
}