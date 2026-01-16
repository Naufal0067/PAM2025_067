package com.example.tugasakhir.uicontroller.route

object DestinasiDetail {
    const val route = "detail"

    const val JADWAL_ID = "jadwalId"
    const val TUGAS_ID = "tugasId"

    val routeDenganArgJadwal = "$route/jadwal/{$JADWAL_ID}"
    val routeDenganArgTugas = "$route/tugas/{$TUGAS_ID}"

    fun buatRouteJadwal(jadwalId: Int) = "$route/jadwal/$jadwalId"
    fun buatRouteTugas(tugasId: Int) = "$route/tugas/$tugasId"
}