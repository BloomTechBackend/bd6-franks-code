package com.ata.lambdaexpressions.classroom.dao;

import com.ata.lambdaexpressions.classroom.exception.RecipeNotFoundException;
import com.ata.lambdaexpressions.classroom.model.Ingredient;
import com.ata.lambdaexpressions.classroom.model.Recipe;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import javax.inject.Inject;

/**
 * Provides access to Recipes in the ice cream parlor's recipe store.
 */
public class RecipeDao {
    private static final Recipe VANILLA = new Recipe(
        "Vanilla", Ingredient.CREAM, Ingredient.SUGAR, Ingredient.EGGS, Ingredient.VANILLA);
    private static final Recipe CHOCOLATE = new Recipe(
        "Chocolate", Ingredient.CREAM, Ingredient.SUGAR, Ingredient.EGGS, Ingredient.CHOCOLATE);
    private static final Recipe STRAWBERRY = new Recipe(
        "Strawberry", Ingredient.CREAM, Ingredient.SUGAR, Ingredient.EGGS, Ingredient.STRAWBERRIES);

    private static final Map<String, Recipe> RECIPES = ImmutableMap.of(
        VANILLA.getFlavorName(), VANILLA,
        CHOCOLATE.getFlavorName(), CHOCOLATE,
        STRAWBERRY.getFlavorName(), STRAWBERRY
    );

    @Inject
    public RecipeDao() {
    }

    /**
     * Returns the recipe for the given flavor, if found.
     * @param flavorName The flavor to look up the recipe for
     * @return The Recipe for the given flavor, if found
     * @throws RecipeNotFoundException if flavor is unrecognized
     */
    public Recipe getRecipe(String flavorName) throws RecipeNotFoundException {
        if (!RECIPES.containsKey(flavorName)) {
            throw new RecipeNotFoundException("Unrecognized flavor: " + flavorName);
        }
        return RECIPES.get(flavorName);
    }
}
