// RecipeApp.kt
package com.example.recipy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState
    var isLoggedIn by remember {
        mutableStateOf(FirebaseAuth.getInstance().currentUser != null)
    }

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Screen.RecipeScreen.route else Screen.AuthScreen.route
    ) {
        composable(route = Screen.AuthScreen.route) {
            AuthScreen(onAuthSuccess = {
                isLoggedIn = true
                navController.navigate(Screen.RecipeScreen.route) {
                    popUpTo(Screen.AuthScreen.route) { inclusive = true }
                }
            })
        }

        composable(route = Screen.RecipeScreen.route) {
            RecipeScreen(viewState = viewState, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        composable(route = Screen.DetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle
                ?.get<Category>("cat") ?: Category("", "", "", "")
            CategoryDetailScreen(category = category)
        }
    }
}