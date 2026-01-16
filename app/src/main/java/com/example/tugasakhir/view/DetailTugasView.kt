package com.example.tugasakhir.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.tugasakhir.R
import com.example.tugasakhir.uicontroller.route.DestinasiEditTugas
import com.example.tugasakhir.uicontroller.route.DestinasiSubmitTugas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTugasView(
    navController: NavController,
    isGuru: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.detail_tugas)) }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            Text(stringResource(R.string.nama_tugas))
            Text(stringResource(R.string.deskripsi_tugas))

            if (isGuru) {
                Button(onClick = {
                    navController.navigate(DestinasiEditTugas.route)
                }) {
                    Text(stringResource(R.string.edit_tugas))
                }
            } else {
                Button(onClick = {
                    navController.navigate(DestinasiSubmitTugas.route)
                }) {
                    Text(stringResource(R.string.submit_tugas))
                }
            }
        }
    }
}
