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

    // Ambil role dari session setiap kali navigasi diinisialisasi
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

        // ===== HOME GURU =====
        // Dipisahkan agar tidak tertukar saat proses login berlangsung
        composable(DestinasiHomeGuru.route) {
            LaunchedEffect(Unit) {
                homeViewModel.setRole("guru")
            }
            HomeGuruView(navController = navController)
        }

        // ===== HOME SISWA =====
        composable(DestinasiHomeSiswa.route) {
            LaunchedEffect(Unit) {
                homeViewModel.setRole("siswa")
            }
            HomeSiswaView(navController = navController)
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
            val id = backStackEntry.arguments?.getInt("jadwalId") ?: 0

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
            // PERBAIKAN: Gunakan variabel jadwalId yang sudah didefinisikan
            val jadwalId = backStackEntry
                .arguments
                ?.getInt(DestinasiEditJadwal.jadwalIdArg)
                ?: 0

            EditJadwalView(
                navController = navController,
                idJadwal = jadwalId
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

        // ===== DETAIL TUGAS =====
        composable(
            route = DestinasiDetailTugas.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailTugas.tugasIdArg) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val idTugas = backStackEntry
                .arguments
                ?.getInt(DestinasiDetailTugas.tugasIdArg)
                ?: 0

            DetailTugasView(
                idTugas = idTugas,
                navController = navController,
                isGuru = isGuru
            )
        }

// ===== SUBMIT TUGAS =====
        composable(
            route = DestinasiSubmitTugas.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiSubmitTugas.tugasIdArg) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val idTugas = backStackEntry
                .arguments
                ?.getInt(DestinasiSubmitTugas.tugasIdArg)
                ?: 0

            SubmitTugasView(
                idTugas = idTugas,
                navController = navController
            )
        }
        composable(
            route = DestinasiEditTugas.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiEditTugas.tugasIdArg) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val idTugas = backStackEntry
                .arguments
                ?.getInt(DestinasiEditTugas.tugasIdArg)
                ?: 0

            if (isGuru) {
                EditTugasView(
                    idTugas = idTugas,
                    navController = navController
                )
            } else {
                // siswa tidak boleh edit
                navController.popBackStack()
            }
        }
        // ===== DAFTAR SUBMIT TUGAS (GURU) =====
        composable(
            route = DestinasiDaftarSubmitTugas.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDaftarSubmitTugas.tugasIdArg) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val idTugas = backStackEntry
                .arguments
                ?.getInt(DestinasiDaftarSubmitTugas.tugasIdArg)
                ?: 0

            if (isGuru) {
                DaftarSubmitTugasView(
                    tugasId = idTugas,
                    navController = navController,
                    viewModel = viewModel(factory = PenyediaViewModel.Factory)
                )
            } else {
                // siswa tidak boleh lihat daftar submit
                navController.popBackStack()
            }
        }
    }
}