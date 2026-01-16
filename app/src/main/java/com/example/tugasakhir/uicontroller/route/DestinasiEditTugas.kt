package com.example.tugasakhir.uicontroller.route

import com.example.tugasakhir.R

object DestinasiEditTugas : DestinasiNavigasi {
    override val route = "edit_tugas/{tugasId}"
    override val titleRes = R.string.edit_tugas

    fun createRoute(tugasId: Int) = "edit_tugas/$tugasId"
}