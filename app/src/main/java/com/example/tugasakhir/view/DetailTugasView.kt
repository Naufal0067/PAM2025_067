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
import com.example.tugasakhir.uicontroller.route.DestinasiEditTugas

import com.example.tugasakhir.viewmodel.TugasViewModel
import com.example.tugasakhir.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTugasView(
    idTugas: Int,
    navController: NavController,
    isGuru: Boolean,
    viewModel: TugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    val tugas = uiState.listTugas.find { it.id == idTugas }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.detail_tugas)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (tugas == null) {
                Text("Data tugas tidak ditemukan")
                return@Column
            }

            Text(tugas.judulTugas, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Deskripsi")
            Text(tugas.deskripsi)

            Spacer(modifier = Modifier.height(12.dp))
            Text("Tenggat")
            Text(tugas.tenggat)

            Spacer(modifier = Modifier.weight(1f))

            if (isGuru) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            navController.navigate(
                                "${DestinasiEditTugas.route}/${tugas.id}"
                            )
                        }
                    ) {
                        Text(stringResource(R.string.edit_tugas))
                    }

                    Button(
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error
                        ),
                        onClick = {
                            scope.launch {
                                viewModel.deleteTugas(tugas)
                                navController.popBackStack()
                            }
                        }
                    ) {
                        Text("Hapus")
                    }
                }
            } else {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(
                            "${DestinasiSubmitTugas.route}/${tugas.id}"
                        )
                    }
                ) {
                    Text(stringResource(R.string.submit_tugas))
                }
            }
            Button(onClick = {
                navController.navigate("submit_list/${tugas.id}")
            }) {
                Text("Lihat Pengumpulan")
            }

        }
    }
}
