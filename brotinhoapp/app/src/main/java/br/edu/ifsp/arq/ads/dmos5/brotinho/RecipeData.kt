package br.edu.ifsp.arq.ads.dmos5.brotinho

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_data)

        // Receber os detalhes da receita da Intent
        val intent: Intent = getIntent()
        val recipe: Recipe? = intent.getParcelableExtra("recipe")

        // Verificar se a receita não é nula
        recipe?.let {
            // Atualizar as visualizações com os detalhes da receita
            findViewById<TextView>(R.id.recipe_name_text_view).text = it.getName()
            // Substitua R.drawable.placeholder_image pela imagem real da receita
            findViewById<ImageView>(R.id.recipe_image).setImageResource(R.drawable.pao)
            // Atualize a lista de ingredientes
            findViewById<TextView>(R.id.ingredients_list_text_view).text = it.getIngredients().toString()
            // Atualize o modo de preparo
            findViewById<TextView>(R.id.instructions_text).text = it.getInstructions().toString()
            // Atualize as porções
            findViewById<TextView>(R.id.servings_text).text = it.getServings().toString()
        }
    }
}
