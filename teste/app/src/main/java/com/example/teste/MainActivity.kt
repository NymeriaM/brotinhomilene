// java/com/yourpackage/MainActivity.kt
package com.example.teste

import android.R.drawable
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewGlutenFree: ImageView = findViewById(R.id.imageViewGlutenFree)
        val imageViewLactoseFree: ImageView = findViewById(R.id.imageViewLactoseFree)

        imageViewGlutenFree.setOnClickListener {
            val intent = Intent(this, RecipeListActivity::class.java)
            intent.putExtra("TOPIC", "gluten_free")
            startActivity(intent)
        }

        imageViewLactoseFree.setOnClickListener {
            val intent = Intent(this, RecipeListActivity::class.java)
            intent.putExtra("TOPIC", "lactose_free")
            startActivity(intent)
        }
    }
}
