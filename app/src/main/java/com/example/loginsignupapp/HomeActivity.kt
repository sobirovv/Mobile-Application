package com.example.loginsignupapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recipeAdapter = RecipeAdapter(object : RecipeAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int) {
                Toast.makeText(this@HomeActivity, "Item clicked: $recipeId", Toast.LENGTH_SHORT).show()
            }

            override fun onActionClick(recipeId: Int, action: String) {
                Toast.makeText(this@HomeActivity, "$action clicked for item: $recipeId", Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.adapter = recipeAdapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeViewModel.filterRecipes(newText.orEmpty())
                return true
            }
        })

        lifecycleScope.launch {
            recipeViewModel.filteredRecipes.collect { recipes ->
                recipeAdapter.submitList(recipes)
            }
        }

        val dummyRecipes = generateDummyRecipes()
        recipeViewModel.setRecipes(dummyRecipes)
    }

    private fun generateDummyRecipes(): List<Recipe> {
        return listOf(
            Recipe(1, "Palov", R.drawable.palov),
            Recipe(2, "Somsa", R.drawable.somsa),
            Recipe(3, "Manti", R.drawable.manti),
            Recipe(4, "Shashlik", R.drawable.shashlik),
            Recipe(5, "Shörva", R.drawable.shorva),
            Recipe(6, "Norin", R.drawable.norin),
            Recipe(7, "Menemen", R.drawable.menemen)
        )
    }
}
