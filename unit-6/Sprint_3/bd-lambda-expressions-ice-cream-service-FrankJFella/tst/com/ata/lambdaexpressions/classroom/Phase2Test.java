package com.ata.lambdaexpressions.classroom;

import com.ata.lambdaexpressions.classroom.dependency.DaggerIceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.dependency.IceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.model.Flavor;
import com.ata.lambdaexpressions.classroom.model.Sundae;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Phase2Test {
    private static final IceCreamParlorServiceComponent DAGGER = DaggerIceCreamParlorServiceComponent.create();

    private IceCreamParlorService service;

    @BeforeEach
    private void setup() {
        service = DAGGER.provideIceCreamParlorService();
    }

    @Test
    void getSundae_withInStockFlavors_returnsSundaeWithAllFlavors() {
        // GIVEN
        List<String> flavorNames = ImmutableList.of("Vanilla", "Strawberry", "Vanilla");
        List<Flavor> expectedFlavors = ImmutableList.of(Flavor.VANILLA, Flavor.STRAWBERRY, Flavor.VANILLA);

        // WHEN
        Sundae result = service.getSundae(flavorNames);

        // THEN
        assertEquals(expectedFlavors, result.getScoops(), "Sundae didn't contain expected flavors!");
    }

    @Test
    void getSundae_withEmptyFlavor_returnsSundaeWithoutEmptyFlavor() {
        // GIVEN
        List<String> flavorNames = ImmutableList.of("Chocolate", "Strawberry", "Chocolate", "Vanilla", "Strawberry");
        List<Flavor> expectedFlavors = ImmutableList.of(Flavor.STRAWBERRY, Flavor.VANILLA,Flavor. STRAWBERRY);

        // WHEN
        Sundae result = service.getSundae(flavorNames);

        // THEN
        assertEquals(expectedFlavors, result.getScoops(), "Sundae didn't contain expected flavors!");
    }
}
