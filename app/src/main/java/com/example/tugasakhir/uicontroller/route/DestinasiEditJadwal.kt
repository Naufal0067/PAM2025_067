package com.example.tugasakhir.uicontroller.route

import com.example.tugasakhir.R
import com.example.tugasakhir.uicontroller.route.DestinasiDetailJadwal.jadwalIdArg

object DestinasiEditJadwal {
    const val route = "edit_jadwal"
    const val jadwalIdArg = "jadwalId"

    val routeWithArg = "$route/{$jadwalIdArg}"
}
