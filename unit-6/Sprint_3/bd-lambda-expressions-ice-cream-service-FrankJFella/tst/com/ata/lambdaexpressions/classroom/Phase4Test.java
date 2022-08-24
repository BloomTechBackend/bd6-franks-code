package com.ata.lambdaexpressions.classroom;

import com.ata.lambdaexpressions.classroom.dao.RecipeDao;
import com.ata.lambdaexpressions.classroom.dependency.DaggerIceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.dependency.IceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.model.Ingredient;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class Phase4Test {
    private static final IceCreamParlorServiceComponent DAGGER = DaggerIceCreamParlorServiceComponent.create();

    private IceCreamParlorService serviceWithMocks;

    private IceCreamParlorService service;

    @Spy
    private IceCreamMaker iceCreamMakerSpy;

    @Mock
    private IceCreamMaker iceCreamMakerMock;

    @Captor
    private ArgumentCaptor<Supplier<Ingredient>> ingredientSupplierCaptor;

    private RecipeDao recipeDao;

    @BeforeEach
    private void setup() {
        initMocks(this);
        iceCreamMakerSpy = new IceCreamMaker();
        recipeDao = new RecipeDao();
        serviceWithMocks = new IceCreamParlorService(recipeDao, null, iceCreamMakerMock);
        service = DAGGER.provideIceCreamParlorService();
    }

    @Test
    void iceCreamMaker_prepareIceCreamCarton_callsFunctionalInterface() {
        // GIVEN
        Queue<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(Ingredient.CHOCOLATE);
        ingredients.add(Ingredient.EGGS);
        Supplier<Ingredient> ingredientSupplierSpy = spy(new SupplierSpy(ingredients));

        // WHEN
        iceCreamMakerSpy.prepareIceCreamCarton(ingredientSupplierSpy);

        // THEN
        verify(
            ingredientSupplierSpy,
            times(ingredients.size() + 1).description("Expected num ingredients + 1 calls to functional interface")
        ).get();
    }

    @Test
    void prepareFlavors_oneFlavor_prepareIceCreamCartonReceivesCorrectIngredients() {
        // GIVEN
        List<String> flavors = ImmutableList.of("Chocolate");
        when(iceCreamMakerMock.prepareIceCreamCarton(ingredientSupplierCaptor.capture())).thenReturn(true);

        // WHEN
        serviceWithMocks.prepareFlavors(flavors);

        // THEN
        Supplier<Ingredient> supplier = ingredientSupplierCaptor.getValue();
        assertEquals(
            Ingredient.CREAM,
            supplier.get(),
            "Expected functional interface to provide a " + Ingredient.CREAM);
        assertEquals(
            Ingredient.SUGAR,
            supplier.get(),
            "Expected functional interface to provide a " + Ingredient.SUGAR);
        assertEquals(
            Ingredient.EGGS,
            supplier.get(),
            "Expected functional interface to provide a " + Ingredient.EGGS);
        assertEquals(
            Ingredient.CHOCOLATE,
            supplier.get(),
            "Expected functional interface to provide a " + Ingredient.CHOCOLATE);
        assertNull(supplier.get(), "Expected functional interface to return a null when empty");
    }

    @Test
    void prepareFlavors_multipleFlavors_returnsCorrectNumberOfCartons() {
        // GIVEN
        List<String> flavors = ImmutableList.of("Chocolate", "Vanilla", "Strawberry");

        // WHEN
        int result = service.prepareFlavors(flavors);

        // THEN
        assertEquals(flavors.size(), result, "Expected number of cartons created to match flavors count");
    }

    static class SupplierSpy implements Supplier<Ingredient> {
        private Queue<Ingredient> ingredients;

        public SupplierSpy(Collection<Ingredient> ingredients) {
            this.ingredients = new LinkedList<>(ingredients);
        }

        public Ingredient get() {
            return ingredients.poll();
        }
    }
}
