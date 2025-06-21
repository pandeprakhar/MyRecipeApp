// AuthViewModel.kt
package com.example.recipy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    var errorMessage by mutableStateOf("")
        private set

    private val auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        errorMessage = ""
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Please fill in both fields."
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) onSuccess()
                else errorMessage = task.exception?.localizedMessage ?: "Login failed."
            }
            .addOnFailureListener {
                errorMessage = it.localizedMessage ?: "Something went wrong"
            }
    }

    fun signup(email: String, password: String, onSuccess: () -> Unit) {
        errorMessage = ""
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Please fill in both fields."
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) onSuccess()
                else errorMessage = task.exception?.localizedMessage ?: "Signup failed."
            }
            .addOnFailureListener {
                errorMessage = it.localizedMessage ?: "Something went wrong"
            }
    }
}
