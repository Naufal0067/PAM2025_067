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
import com.example.tugasakhir.viewmodel.JadwalViewModel
import com.example.tugasakhir.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryJadwalView(
    navController: NavController,
    viewModel: JadwalViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    var mapel by remember { mutableStateOf("") }
    var hari by remember { mutableStateOf("") }
    var jam by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.tambah_jadwal)) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            TextField(
                value = mapel,
                onValueChange = { mapel = it },
                label = { Text(stringResource(R.string.mata_pelajaran)) },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = hari,
                onValueChange = { hari = it },
                label = { Text(stringResource(R.string.hari)) },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = jam,
                onValueChange = { jam = it },
                label = { Text(stringResource(R.string.jam)) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.insertJadwal(
                        mapel = mapel,
                        hari = hari,
                        jam = jam
                    )
                    navController.popBackStack()
                }
            ) {
                Text(stringResource(R.string.btn_simpan))
            }
        }
    }
}
