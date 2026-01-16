package com.example.tugasakhir.uicontroller

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.tugasakhir.uicontroller.route.*
import com.example.tugasakhir.util.SessionManager
import com.example.tugasakhir.view.*
import com.example.tugasakhir.viewmodel.HomeViewModel
import com.example.tugasakhir.viewmodel.provider.PenyediaViewModel

@Composable
fun PetaNavigasi() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val sessionManager = remember { SessionManager(context) }
    val homeViewModel: HomeViewModel =
        viewModel(factory = PenyediaViewModel.Factory)



    // ambil role dari session
    LaunchedEffect(Unit) {
        homeViewModel.setRole(sessionManager.getRole() ?: "siswa")
    }

    val isGuru by homeViewModel.isGuru.collectAsState()

    NavHost(
        navController = navController,
        startDestination = DestinasiLogin.route
    ) {

        // ===== LOGIN =====
        composable(DestinasiLogin.route) {
            LoginView(navController = navController)
        }

        // ===== HOME =====
        composable(DestinasiHomeGuru.route) {
            if (isGuru) {
                HomeGuruView(
                    navController = navController,

                )
            } else {
                HomeSiswaView(
                    navController = navController,

                )
            }
        }

        // ===== JADWAL =====
        composable(DestinasiJadwal.route) {
            JadwalView(
                navController = navController,
                isGuru = isGuru
            )
        }

        composable(DestinasiEntryJadwal.route) {
            if (isGuru) {
                EntryJadwalView(navController)
            } else {
                navController.popBackStack()
            }
        }

        composable(
            route = "${DestinasiDetailJadwal.route}/{jadwalId}",
            arguments = listOf(
                navArgument("jadwalId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("jadwalId")

            DetailJadwalView(
                idJadwal = id,
                navController = navController,
                isGuru = isGuru
            )
        }

        composable(
            route = DestinasiEditJadwal.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiEditJadwal.jadwalIdArg) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val jadwalId = backStackEntry
                .arguments
                ?.getInt(DestinasiEditJadwal.jadwalIdArg)
                ?: 0

            EditJadwalView(
                navController = navController,
                idJadwal = id
            )
        }



        // ===== TUGAS =====
        composable(DestinasiTugas.route) {
            TugasView(
                navController = navController,
                isGuru = isGuru
            )
        }

        composable(DestinasiEntryTugas.route) {
            if (isGuru) {
                EntryTugasView(navController)
            } else {
                navController.popBackStack()
            }
        }

        composable(DestinasiDetailTugas.route) {
            DetailTugasView(
                navController = navController,
                isGuru = isGuru
            )
        }

        composable(DestinasiSubmitTugas.route) {
            SubmitTugasView(navController)
        }
    }
}
