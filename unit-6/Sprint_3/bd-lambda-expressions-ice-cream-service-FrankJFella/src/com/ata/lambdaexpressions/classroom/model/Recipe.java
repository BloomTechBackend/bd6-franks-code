package com.ata.lambdaexpressions.classroom.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A recipe for making a particular flavor of ice cream.
 */
public class Recipe {
    private final String flavorName;
    private final List<Ingredient> ingredients = new ArrayList<>();

    /**
     * Creates a recipe from the given flavor name and ingredients
     * @param flavorName flavor name
     * @param ingredients varargs of ingredients in the recipe
     */
    public Recipe(String flavorName, Ingredient ... ingredients) {
        this.flavorName = flavorName;
        Collections.addAll(this.ingredients, ingredients);
    }

    public String getFlavorName() {
        return flavorName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
            "flavorName='" + flavorName + '\'' +
            ", ingredients=" + ingredients +
            '}';
    }
}
