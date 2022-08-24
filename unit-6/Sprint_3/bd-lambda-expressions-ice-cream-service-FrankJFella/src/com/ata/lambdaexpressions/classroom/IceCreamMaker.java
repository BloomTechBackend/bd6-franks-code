package com.ata.lambdaexpressions.classroom;

import com.ata.lambdaexpressions.classroom.model.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.inject.Inject;

/**
 * The machine for making a batch/carton of ice cream.
 */
public class IceCreamMaker {
    @Inject
    public IceCreamMaker() {
    }

    /**
     * Prepares a carton of ice cream from the ingredients indicated by ingredientSupplier.
     * @param ingredientSupplier Supplier that provides the sequence of ingredients to the
     *                           maker. After last ingredient, get() must return null.
     * @return true if carton of ice cream was created successfully; false otherwise.
     */
    public boolean prepareIceCreamCarton(Supplier<Ingredient> ingredientSupplier) {
        Mixer mixer = new Mixer();

        while (true) {
            Ingredient ingredient = ingredientSupplier.get();

            if (null == ingredient) {
                break;
            }

            mixer.addIngredient(ingredient);
        }

        mixer.mix();
        mixer.freeze();

        return true;
    }

    static class Mixer {
        private List<Ingredient> ingredients = new ArrayList<>();

        void addIngredient(Ingredient ingredient) {
            ingredients.add(ingredient);
        }

        List<Ingredient> getIngredients() {
            return new ArrayList<>(ingredients);
        }

        void mix() {
            System.out.println("Mixing ingredients: " + ingredients);
        }

        void freeze() {
            System.out.println("Freezing!");
        }
    }
}
