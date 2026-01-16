package com.example.tugasakhir.view

import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.tugasakhir.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubmitTugasView(navController: NavController) {
    var jawaban by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.submit_tugas)) }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            TextField(
                value = jawaban,
                onValueChange = { jawaban = it },
                label = { Text(stringResource(R.string.deskripsi_tugas)) }
            )

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(stringResource(R.string.btn_submit))
            }
        }
    }
}
