package com.example.tugasakhir.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tugasakhir.viewmodel.SubmissionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarSubmitTugasView(
    tugasId: Int,
    navController: NavController,
    viewModel: SubmissionViewModel
) {
    val list = viewModel.submissions.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pengumpulan Tugas") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            if (list.isEmpty()) {
                Text("Belum ada siswa yang submit")
            } else {
                list.forEach { submit ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Nama: ${submit.namaSiswa}")
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Jawaban:")
                            Text(submit.jawaban)
                        }
                    }
                }
            }
        }
    }
}

