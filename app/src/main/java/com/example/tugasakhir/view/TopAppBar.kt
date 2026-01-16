package com.example.tugasakhir.view


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(titleRes: Int) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = titleRes))
        }
    )
}
