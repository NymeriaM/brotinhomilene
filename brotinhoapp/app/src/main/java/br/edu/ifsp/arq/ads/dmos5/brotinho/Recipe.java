package br.edu.ifsp.arq.ads.dmos5.brotinho;

import java.util.List;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private List<String> instructions;
    private int servings;

    public Recipe(String name, List<String> ingredients, List<String> instructions, int servings) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.servings = servings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
