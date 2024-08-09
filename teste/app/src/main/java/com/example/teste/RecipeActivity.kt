package com.example.teste

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RecipeActivity : AppCompatActivity() {

    private lateinit var recipeImage: ImageView
    private lateinit var recipeTitle: TextView
    private lateinit var recipeIngredients: TextView
    private lateinit var recipeInstructions: TextView
    private lateinit var favoriteButton: ImageButton
    private var isFavorite = false
    private var recipeId: String? = null
    private lateinit var favoritesRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // Inicializando as views
        recipeImage = findViewById(R.id.recipe_image)
        recipeTitle = findViewById(R.id.recipe_title)
        recipeIngredients = findViewById(R.id.recipe_ingredients)
        recipeInstructions = findViewById(R.id.recipe_instructions)
        favoriteButton = findViewById(R.id.favorite_button)

        recipeId = intent.getStringExtra("RECIPE_ID")

        if (recipeId.isNullOrEmpty()) {
            // Trate a ausência do recipeId aqui, talvez fechando a Activity ou mostrando uma mensagem de erro
            finish()
            return
        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) {
            // Trate o caso onde o usuário não está autenticado
            finish()
            return
        }

        favoritesRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("favorites")

        loadRecipeDetails()
        checkIfFavorite()

        favoriteButton.setOnClickListener {
            toggleFavorite()
        }
    }

    private fun loadRecipeDetails() {
        val recipeRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("recipes").child(recipeId!!)
        recipeRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val title: String? = snapshot.child("title").getValue(String::class.java)
                val ingredients: String? = snapshot.child("ingredients").getValue(String::class.java)
                val instructions: String? = snapshot.child("instructions").getValue(String::class.java)
                val imageUrl: String? = snapshot.child("image").getValue(String::class.java)

                recipeTitle.text = title
                recipeIngredients.text = ingredients
                recipeInstructions.text = instructions
                Glide.with(this@RecipeActivity).load(imageUrl).into(recipeImage)
            }

            override fun onCancelled(error: DatabaseError) {
                // Tratamento de erro
            }
        })
    }

    private fun checkIfFavorite() {
        favoritesRef.child(recipeId!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isFavorite = snapshot.exists()
                updateFavoriteButton()
            }

            override fun onCancelled(error: DatabaseError) {
                // Tratamento de erro
            }
        })
    }

    private fun toggleFavorite() {
        if (isFavorite) {
            favoritesRef.child(recipeId!!).removeValue()
        } else {
            favoritesRef.child(recipeId!!).setValue(true)
        }
        isFavorite = !isFavorite
        updateFavoriteButton()
    }

    private fun updateFavoriteButton() {
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.ic_favorite)
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}
