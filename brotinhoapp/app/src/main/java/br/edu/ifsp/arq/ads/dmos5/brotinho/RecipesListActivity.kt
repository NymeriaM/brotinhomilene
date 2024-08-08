package br.edu.ifsp.arq.ads.dmos5.brotinho

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Arrays




class RecipesListActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var txtTitle: TextView
    lateinit var recipesList: List<Recipe> // Supondo que você tenha uma lista de receitas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_list) // Alterado para o layout que exibirá a lista de receitas
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        txtTitle = findViewById(R.id.toolbar_title)
        txtTitle.text = getString(R.string.recipes) // Alterado para o título da lista de receitas

        // Inicialize a lista de receitas com alguns dados (ou carregue de onde quer que venha)
        recipesList = Arrays.asList(
            Recipe(
                "Bolo de Chocolate",
                mutableListOf("Farinha", "Açúcar", "Chocolate", "Ovos"),
                mutableListOf(
                    "1. Misture os ingredientes secos",
                    "2. Adicione os ovos e o chocolate derretido",
                    "3. Asse em forno preaquecido a 180°C por 30 minutos"
                ),
                8
            ),
            Recipe(
                "Pão Caseiro", mutableListOf("Farinha", "Água", "Sal", "Fermento"), mutableListOf(
                    "1. Misture os ingredientes", "2. Deixe a massa descansar por 1 hora",
                    "3. Asse em forno preaquecido a 200°C por 40 minutos"
                ), 10
            ),
            Recipe(
                "Torta de Limão",
                mutableListOf("Farinha", "Açúcar", "Manteiga", "Limão"),
                mutableListOf(
                    "1. Prepare a massa", "2. Faça o recheio de limão",
                    "3. Asse em forno preaquecido a 180°C por 35 minutos"
                ),
                6
            )
        )

        // Exiba a lista de receitas na tela
        val recipesListView = findViewById<ListView>(R.id.recipes_list_view)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, recipesList.map { it.name })
        recipesListView.adapter = adapter

        // Adiciona um listener de clique para os itens da lista
        recipesListView.setOnItemClickListener { parent, view, position, id ->
            val clickedRecipe = recipesList[position]

            // Inicia a RecipeDetailActivity e passa os detalhes da receita
            val intent = Intent(this@RecipesListActivity, RecipeData::class.java)
            intent.putExtra("recipe", clickedRecipe?.name ?: "")
            startActivity(intent)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
