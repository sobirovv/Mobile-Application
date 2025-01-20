package com.example.loginsignupapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _filteredRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val filteredRecipes: StateFlow<List<Recipe>> = _filteredRecipes

    private var currentQuery = ""

    fun setRecipes(recipes: List<Recipe>) {
        _recipes.value = recipes
        _filteredRecipes.value = recipes
    }

    fun filterRecipes(query: String) {
        if (query == currentQuery) {
            return
        }

        currentQuery = query

        if (query.length < 3) {
            _filteredRecipes.value = _recipes.value
        } else {
            _filteredRecipes.value = _recipes.value.filter {
                it.name.contains(query, ignoreCase = true) || it.id.toString().contains(query)
            }
        }
    }
}
