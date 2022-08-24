package com.ata.lambdaexpressions.classroom;

import com.ata.lambdaexpressions.classroom.dependency.DaggerIceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.dependency.IceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.model.Ingredient;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class Phase3Test {
    private static final IceCreamParlorServiceComponent DAGGER = DaggerIceCreamParlorServiceComponent.create();

    @Spy
    private IceCreamParlorService serviceSpy;

    @Captor
    private ArgumentCaptor<List<Queue<Ingredient>>> makeIceCreamCartonsCaptor;

    @BeforeEach
    private void setup() {
        serviceSpy = DAGGER.provideIceCreamParlorService();
        initMocks(this);
    }

    @Test
    void prepareFlavors_withOneFlavor_makeIceCreamCartonsReceivesCorrectIngredients() {
        // GIVEN
        List<String> flavors = ImmutableList.of("Vanilla");
        doReturn(flavors.size()).when(serviceSpy).makeIceCreamCartons(any());

        // WHEN
        serviceSpy.prepareFlavors(flavors);

        // THEN
        // we receive the right number of ingredients queues
        verify(serviceSpy, description("Missing call to makeIceCreamCartons"))
            .makeIceCreamCartons(makeIceCreamCartonsCaptor.capture());
        List<Queue<Ingredient>> ingredientsQueues = makeIceCreamCartonsCaptor.getValue();
        assertEquals(
            flavors.size(),
            ingredientsQueues.size(),
            String.format("Expected ingredient queue size to be %d but got: %s", flavors.size(), ingredientsQueues)
        );

        // The ingredients are the ones we expect
        Queue<Ingredient> ingredients = makeIceCreamCartonsCaptor.getValue().get(0);
        assertEquals(
            Ingredient.CREAM,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.CREAM);
        assertEquals(
            Ingredient.SUGAR,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.SUGAR);
        assertEquals(
            Ingredient.EGGS,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.EGGS);
        assertEquals(
            Ingredient.VANILLA,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.VANILLA);
        assertTrue(ingredients.isEmpty(), "Expected ingredients queue to be empty at this point");
    }

    @Test
    void prepareFlavors_withFlavors_makeIceCreamCartonsReceivesCorrectIngredients() {
        // GIVEN
        List<String> flavors = ImmutableList.of("Vanilla", "Strawberry");
        doReturn(flavors.size()).when(serviceSpy).makeIceCreamCartons(any());

        // WHEN
        serviceSpy.prepareFlavors(flavors);

        // THEN
        // we receive the right number of ingredients queues
        verify(serviceSpy).makeIceCreamCartons(makeIceCreamCartonsCaptor.capture());
        List<Queue<Ingredient>> ingredientsQueues = makeIceCreamCartonsCaptor.getValue();
        assertEquals(
            flavors.size(),
            ingredientsQueues.size(),
            String.format("Expected ingredient queue size to be %d but got: %s", flavors.size(), ingredientsQueues)
        );

        // The strawberry ingredients are the ones we expect
        Queue<Ingredient> ingredients = makeIceCreamCartonsCaptor.getValue().get(1);
        assertEquals(
            Ingredient.CREAM,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.CREAM);
        assertEquals(
            Ingredient.SUGAR,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.SUGAR);
        assertEquals(
            Ingredient.EGGS,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.EGGS);
        assertEquals(
            Ingredient.STRAWBERRIES,
            ingredients.remove(),
            "Expected makeIceCreamCartons to receive a " + Ingredient.STRAWBERRIES);
        assertTrue(ingredients.isEmpty(), "Expected ingredients queue to be empty at this point");
    }
}
