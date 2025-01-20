package com.example.loginsignupapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClick(recipeId: Int)
        fun onActionClick(recipeId: Int, action: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        private val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        private val likeButton: Button = itemView.findViewById(R.id.likeButton)
        private val shareButton: Button = itemView.findViewById(R.id.shareButton)

        fun bind(recipe: Recipe) {
            recipeImage.setImageResource(recipe.imageResId)
            recipeName.text = recipe.name

            itemView.setOnClickListener {
                listener.onItemClick(recipe.id)
            }

            likeButton.setOnClickListener {
                listener.onActionClick(recipe.id, "Like")
            }

            shareButton.setOnClickListener {
                listener.onActionClick(recipe.id, "Share")
            }
        }
    }

    class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}
