package com.example.tugasakhir.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tugasakhir.R
import com.example.tugasakhir.room.AppDatabase
import com.example.tugasakhir.room.JadwalEntity
import com.example.tugasakhir.uicontroller.route.DestinasiEditJadwal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailJadwalView(
    idJadwal: Int,
    navController: NavController,
    isGuru: Boolean
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope() // ✅ taruh di sini, bukan di onClick

    var jadwal by remember { mutableStateOf<JadwalEntity?>(null) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    var showDeleteDialog by remember { mutableStateOf(false) }
    var deleting by remember { mutableStateOf(false) }
    var deleteError by remember { mutableStateOf<String?>(null) }

    // --- Load detail berdasarkan id ---
    LaunchedEffect(idJadwal) {
        loading = true
        error = null
        try {
            val db = AppDatabase.getDatabase(context)
            val dao = db.jadwalDao()

            val result = withContext(Dispatchers.IO) {
                dao.getJadwalById(idJadwal) // pastikan return JadwalEntity? (nullable)
            }

            jadwal = result
        } catch (e: Exception) {
            error = e.message ?: "Gagal memuat detail jadwal"
        } finally {
            loading = false
        }
    }

    // --- Dialog hapus ---
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                if (!deleting) {
                    showDeleteDialog = false
                    deleteError = null
                }
            },
            title = { Text("Hapus Jadwal") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Yakin ingin menghapus jadwal ini? Tindakan ini tidak bisa dibatalkan.")
                    if (deleteError != null) {
                        Text("Error: $deleteError")
                    }
                }
            },
            confirmButton = {
                TextButton(
                    enabled = !deleting,
                    onClick = {
                        deleting = true
                        deleteError = null

                        scope.launch {
                            try {
                                val db = AppDatabase.getDatabase(context)
                                val dao = db.jadwalDao()

                                val current = jadwal
                                if (current == null) {
                                    deleteError = "Data jadwal tidak ditemukan."
                                } else {
                                    withContext(Dispatchers.IO) {
                                        dao.deleteJadwal(current) // pastikan ada di DAO
                                    }
                                    showDeleteDialog = false
                                    navController.popBackStack()
                                }
                            } catch (e: Exception) {
                                deleteError = e.message ?: "Gagal menghapus jadwal"
                            } finally {
                                deleting = false
                            }
                        }
                    }
                ) {
                    Text(if (deleting) "Menghapus..." else "Hapus")
                }
            },
            dismissButton = {
                TextButton(
                    enabled = !deleting,
                    onClick = {
                        showDeleteDialog = false
                        deleteError = null
                    }
                ) {
                    Text("Batal")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.detail_jadwal)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "ID Jadwal: $idJadwal", fontWeight = FontWeight.Bold)
            HorizontalDivider()

            when {
                loading -> {
                    CircularProgressIndicator()
                    Text("Memuat data...")
                }

                error != null -> {
                    Text("Error: $error")
                }

                jadwal == null -> {
                    Text("Data jadwal tidak ditemukan.")
                }

                else -> {
                    val j = jadwal!!

                    Text(text = stringResource(R.string.mata_pelajaran) + ": ${j.mataPelajaran}")
                    Text(text = stringResource(R.string.hari) + ": ${j.hari}")

                    // Kalau jam kamu satu field (misal "08:00-10:00")
                    Text(text = stringResource(R.string.jam_mulai) + ": ${j.jam}")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (isGuru && !loading && jadwal != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            navController.navigate("${DestinasiEditJadwal.route}/$idJadwal")
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.edit_jadwal))
                    }

                    OutlinedButton(
                        onClick = { showDeleteDialog = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Hapus")
                    }
                }
            }
        }
    }
}
