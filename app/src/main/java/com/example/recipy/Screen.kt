package com.example.recipy

sealed class Screen(val route: String ) {
    object RecipeScreen: Screen("recipescreen")
    object  DetailScreen: Screen("detailscreen")
    object AuthScreen: Screen("auth")
}