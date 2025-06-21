package com.example.recipy

import retrofit2.http.GET

data class CategoryResponse(val categories: List<Category>)

interface RecipeApi {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse
}