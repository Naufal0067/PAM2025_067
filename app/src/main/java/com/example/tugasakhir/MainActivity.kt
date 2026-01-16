package com.example.tugasakhir


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasakhir.ui.theme.TugasAkhirTheme
import com.example.tugasakhir.uicontroller.PetaNavigasi
import com.example.tugasakhir.util.UserSeeder
import com.example.tugasakhir.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // seed user sekali saja
        UserSeeder.seed(this)

        setContent {
            TugasAkhirTheme {
                PetaNavigasi()
            }
        }
    }
}





