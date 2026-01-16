package com.example.tugasakhir.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tugasakhir.R
import com.example.tugasakhir.viewmodel.EditJadwalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditJadwalView(
    idJadwal: Int,
    navController: NavController,
    viewModel: EditJadwalViewModel = viewModel() // NOTE: kalau butuh factory, lihat bagian bawah
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(idJadwal) {
        viewModel.loadJadwal(idJadwal)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.edit_jadwal)) })
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {

            TextField(
                value = state.mataPelajaran,
                onValueChange = { viewModel.onMapelChange(it) },
                label = { Text(stringResource(R.string.mata_pelajaran)) }
            )

            TextField(
                value = state.hari,
                onValueChange = { viewModel.onHariChange(it) },
                label = { Text("Hari") } // atau stringResource kalau ada
            )

            TextField(
                value = state.jam,
                onValueChange = { viewModel.onJamChange(it) },
                label = { Text("Jam") }
            )

            Button(
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
