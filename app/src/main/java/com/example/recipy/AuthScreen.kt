// AuthScreen.kt
package com.example.recipy

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(onAuthSuccess: () -> Unit) {
    val viewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.login(email, password, onAuthSuccess) }) {
            Text("Login")
        }
        Button(onClick = { viewModel.signup(email, password, onAuthSuccess) }) {
            Text("Signup")
        }

        if (viewModel.errorMessage.isNotEmpty()) {
            Text(text = viewModel.errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}