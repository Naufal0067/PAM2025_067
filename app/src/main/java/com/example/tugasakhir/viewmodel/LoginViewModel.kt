package com.example.tugasakhir.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir.repositori.UserRepositori
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Perubahan 1: Gunakan ViewModel (bukan AndroidViewModel)
// agar cocok dengan Factory di PenyediaViewModel
class LoginViewModel(
    private val repository: UserRepositori
) : ViewModel() {

    fun login(
        username: String,
        password: String,
        onResult: (success: Boolean, role: String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Ambil user dari database di IO thread
                val user = withContext(Dispatchers.IO) {
                    repository.login(
                        username.trim(),
                        password.trim()
                    )
                }

                if (user != null) {
                    // 🔥 PENTING: pastikan role tidak null & lowercase
                    onResult(true, user.role.lowercase())
                } else {
                    onResult(false, null)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false, null)
            }
        }
    }
}
