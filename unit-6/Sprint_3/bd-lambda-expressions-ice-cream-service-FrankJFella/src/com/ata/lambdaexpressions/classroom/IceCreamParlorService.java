package com.ata.lambdaexpressions.classroom;

import com.ata.lambdaexpressions.classroom.converter.RecipeConverter;
import com.ata.lambdaexpressions.classroom.dao.CartonDao;
import com.ata.lambdaexpressions.classroom.dao.RecipeDao;
import com.ata.lambdaexpressions.classroom.exception.CartonCreationFailedException;
import com.ata.lambdaexpressions.classroom.exception.RecipeNotFoundException;
import com.ata.lambdaexpressions.classroom.model.Carton;
import com.ata.lambdaexpressions.classroom.model.Ingredient;
import com.ata.lambdaexpressions.classroom.model.Recipe;
import com.ata.lambdaexpressions.classroom.model.Sundae;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 * Provides Ice Cream Parlor functionality.
 */
public class IceCreamParlorService {
    private final RecipeDao recipeDao;
    private final CartonDao cartonDao;
    private final IceCreamMaker iceCreamMaker;

    /**
     * Constructs service with the provided DAOs.
     * @param recipeDao the RecipeDao to use for accessing recipes
     * @param cartonDao the CartonDao to use for accessing ice cream cartons
     */
    @Inject
    public IceCreamParlorService(RecipeDao recipeDao, CartonDao cartonDao, IceCreamMaker iceCreamMaker) {
        this.recipeDao = recipeDao;
        this.cartonDao = cartonDao;
        this.iceCreamMaker = iceCreamMaker;
    }

    /**
     * Creates and returns the sundae defined by the given ice cream flavors.
     * If a flavor is not found or we have none of that flavor left, the sundae
     * is returned, but without that flavor. (We'll only charge the customer for
     * the scoops they are returned)
     * @param flavorNames List of flavors to include in the sundae
     * @return The newly created Sundae
     */
    public Sundae getSundae(List<String> flavorNames) {
        // This does the filtering out of any unknown flavors, so only
        // Cartons of known flavors will be returned.
        List<Carton> cartons = cartonDao.getCartonsByFlavorNames(flavorNames);

        // PHASE 1: Use removeIf() to remove any empty cartons from cartons
        cartons.removeIf((aCarton) -> aCarton.isEmpty());
      //cartons.removeIf( aCarton  -> aCarton.isEmpty());  // no () if only parm for Lambda expression
      //cartons.removeIf(Carton::isEmpty);  // method reference in place of Lambda expression

        return buildSundae(cartons);
    }

    @VisibleForTesting
    Sundae buildSundae(List<Carton> cartons) {
        Sundae sundae = new Sundae();

        // PHASE 2: Use forEach() to add one scoop of each flavor
        // remaining in cartons
        //
        // call the addScoop() method for each carton we are passed
        //
        // go through the list of cartons and get on scoop from each
        // since cartons os a List we can use the List forEach to process it
        //
        // in a Java for each loop:
        //   for(Carton aCarton : cartons) {
        //      sundae.addscoop(aCarton.getFlavor);
        //   }

      //cartons.forEach((aCarton) -> sundae.addScoop(aCarton.getFlavor()));
        cartons.forEach( aCarton  -> sundae.addScoop(aCarton.getFlavor()));

        return sundae;
    }

    /**
     * Prepares the specified flavors, creating 1 carton of each provided
     * flavor.
     *
     * A flavor name that doesn't correspond
     * to a known recipe will result in CartonCreationFailedException, and
     * no Cartons will be created.
     *
     * @param flavorNames List of names of flavors to create new batches of
     * @return the number of cartons produced by the ice cream maker
     */
    public int prepareFlavors(List<String> flavorNames) {
        // This in NOT the Stream interface map()method
        // It is a helper method provided in this class
        //    which we will look at later
        List<Recipe> recipes = map(
            flavorNames,
            (flavorName) -> {
                // trap the checked exception, RecipeNotFoundException, and
                // wrap in a runtime exception because our lambda can't throw
                // checked exceptions
                try {
                    return recipeDao.getRecipe(flavorName);
                } catch (RecipeNotFoundException e) {
                    throw new CartonCreationFailedException("Could not find recipe for " + flavorName, e);
                }
            }
        );

        // PHASE 3: Replace right hand side: use Stream map() to convert List<Recipe> to List<Queue<Ingredient>>
        //          using the RecipeConverter class fromRecipeToIngredientQueue() method
      //List<Queue<Ingredient>> ingredientQueues = new ArrayList<>();  // replaced by stream().map() implementation
      //List<Queue<Ingredient>> ingredientQueues =
      //           recipes.stream().map(RecipeConverter::fromRecipeToIngredientQueue).collect(Collectors.toList());

        List<Queue<Ingredient>> ingredientQueues =
                recipes.stream().map((aRecipe) -> RecipeConverter.fromRecipeToIngredientQueue(aRecipe))
                                                 .collect(Collectors.toList());



        return makeIceCreamCartons(ingredientQueues);
    }

    @VisibleForTesting
    int makeIceCreamCartons(List<Queue<Ingredient>> ingredientQueues) {
        // don't change any of the lines that touch cartonsCreated.
        int cartonsCreated = 0;
        for (Queue<Ingredient> ingredients : ingredientQueues) {

            // PHASE 4: provide Supplier to prepareIceCream()
            //  Use a Lambda expression to give the ingredients to the iceCreamMaker one at a time
            //        if a carton is successfully prepared, count it

          //if (iceCreamMaker.prepareIceCreamCarton(null)) { // Replaced by Lambda expression
            if (iceCreamMaker.prepareIceCreamCarton(() -> ingredients.poll())) {
                cartonsCreated++;
            }
        }

        return cartonsCreated;
    }

    /**
     * Converts input list of type T to a List of type R, where each entry in the return
     * value is the output of converter applied to each entry in input.
     *
     * (We will get to Java streams in a later lesson, at which point we won't need a helper method
     * like this.)
     */
    private <T, R> List<R> map(List<T> input, Function<T, R> converter) {
        return input.stream()
            .map(converter)
            .collect(Collectors.toList());
    }
}
