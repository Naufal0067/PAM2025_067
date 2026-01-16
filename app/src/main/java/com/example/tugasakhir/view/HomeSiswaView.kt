package com.example.tugasakhir.view

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tugasakhir.R
import com.example.tugasakhir.uicontroller.route.DestinasiJadwal
import com.example.tugasakhir.uicontroller.route.DestinasiLogin
import com.example.tugasakhir.uicontroller.route.DestinasiTugas
import com.example.tugasakhir.util.SessionManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSiswaView(navController: NavController) {

    val context = LocalContext.current
    val session = SessionManager(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home_siswa)) },
                actions = {
                    TextButton(
                        onClick = {
                            session.logout()
                            navController.navigate(DestinasiLogin.route) {
                                popUpTo(0)
                            }
                        }
                    ) {
                        Text(stringResource(R.string.logout))
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                onClick = {
                    navController.navigate(DestinasiJadwal.route)
                }
            ) {
                Text(stringResource(R.string.jadwal_pelajaran))
            }

            Button(
                onClick = {
                    navController.navigate(DestinasiTugas.route)
                }
            ) {
                Text(stringResource(R.string.tugas))
            }
        }
    }
}



