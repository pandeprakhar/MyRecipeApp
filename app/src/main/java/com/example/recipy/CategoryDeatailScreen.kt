// CategoryDetailScreen.kt
package com.example.recipy

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CategoryDetailScreen(category: Category) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = category.strCategory, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = category.strCategoryDescription)
    }
}