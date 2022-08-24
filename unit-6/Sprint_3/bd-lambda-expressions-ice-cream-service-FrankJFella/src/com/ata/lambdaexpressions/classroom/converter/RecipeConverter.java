package com.ata.lambdaexpressions.classroom.converter;

import com.ata.lambdaexpressions.classroom.model.Ingredient;
import com.ata.lambdaexpressions.classroom.model.Recipe;

import java.util.LinkedList;
import java.util.Queue;

public final class RecipeConverter {
    private RecipeConverter() {
    }

    public static Queue<Ingredient> fromRecipeToIngredientQueue(Recipe recipe) {
        return new LinkedList<>(recipe.getIngredients());
    }
}
