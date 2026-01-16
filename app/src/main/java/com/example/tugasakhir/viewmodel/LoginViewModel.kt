package com.example.tugasakhir.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir.repositori.UserRepositori
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Perubahan 1: Gunakan ViewModel (bukan AndroidViewModel)
// agar cocok dengan Factory di PenyediaViewModel
class LoginViewModel(private val repository: UserRepositori) : ViewModel() {

    fun login(
        username: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Perubahan 2: Jalankan di Background Thread (Dispatchers.IO)
                // Ini mencegah crash "Database access on main thread"
                val user = withContext(Dispatchers.IO) {
                    repository.login(username, password)
                }

                if (user != null) {
                    onResult(true, user.role)
                } else {
                    onResult(false, null)
                }
            } catch (e: Exception) {
                // Jika ada error database, aplikasi tidak crash tapi mengembalikan false
                onResult(false, null)
            }
        }
    }
}