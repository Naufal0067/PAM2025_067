package com.example.tugasakhir.uicontroller.route

import com.example.tugasakhir.R

object DestinasiDetailTugas : DestinasiNavigasi {
    override val route = "detail_tugas/{tugasId}"
    override val titleRes = R.string.detail_tugas

    fun createRoute(tugasId: Int) = "detail_tugas/$tugasId"
}