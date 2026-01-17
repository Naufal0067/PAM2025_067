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
import com.example.tugasakhir.viewmodel.SubmitTugasViewModel
import com.example.tugasakhir.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubmitTugasView(
    idTugas: Int,
    navController: NavController,
    viewModel: SubmitTugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    var jawaban by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Submit Tugas") })
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = jawaban,
                onValueChange = { jawaban = it },
                label = { Text("Jawaban") },
                minLines = 4
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.submitTugas(
                        tugasId = idTugas,
                        namaSiswa = "Siswa", // nanti bisa dari Session
                        jawaban = jawaban
                    ) {
                        navController.popBackStack()
                    }
                }
            ) {
                Text("Kirim")
            }
        }
    }
}
