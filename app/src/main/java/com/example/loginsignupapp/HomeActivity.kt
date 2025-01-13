package com.example.loginsignupapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = generateDummyRecipes()
        recipeAdapter = RecipeAdapter(recipes, object : RecipeAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int) {
                Toast.makeText(this@HomeActivity, "Item clicked: $recipeId", Toast.LENGTH_SHORT).show()
            }

            override fun onActionClick(recipeId: Int, action: String) {
                Toast.makeText(this@HomeActivity, "$action clicked for item: $recipeId", Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.adapter = recipeAdapter
    }

    private fun generateDummyRecipes(): List<Recipe> {
        return listOf(
            Recipe(1, "Palov", R.drawable.palov),
            Recipe(2, "Somsa", R.drawable.somsa)
        )
    }
}
