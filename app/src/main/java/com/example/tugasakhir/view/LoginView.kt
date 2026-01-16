package com.example.tugasakhir.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tugasakhir.R
import com.example.tugasakhir.uicontroller.route.DestinasiHomeGuru
import com.example.tugasakhir.uicontroller.route.DestinasiHomeSiswa
import com.example.tugasakhir.uicontroller.route.DestinasiLogin
import com.example.tugasakhir.util.SessionManager
import com.example.tugasakhir.viewmodel.LoginViewModel
import com.example.tugasakhir.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val context = LocalContext.current
    val session = SessionManager(context)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.login_title)) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(stringResource(R.string.username)) }
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password)) }
            )

            if (error) {
                Text(
                    text = stringResource(R.string.login_failed),
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    loginViewModel.login(username, password) { success, role ->
                        if (success && role != null) {

                            // ✅ SIMPAN ROLE KE SESSION
                            session.saveLogin(role)

                            // ✅ NAVIGASI SESUAI ROLE
                            if (role == "guru") {
                                navController.navigate(DestinasiHomeGuru.route) {
                                    popUpTo(DestinasiLogin.route) { inclusive = true }
                                }
                            } else {
                                navController.navigate(DestinasiHomeSiswa.route) {
                                    popUpTo(DestinasiLogin.route) { inclusive = true }
                                }
                            }

                        } else {
                            error = true
                        }
                    }
                }
            ) {
                Text(stringResource(R.string.login_button))
            }
        }
    }
}

