package com.ata.lambdaexpressions.classroom;

import com.ata.lambdaexpressions.classroom.dependency.DaggerIceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.dependency.IceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.model.Carton;
import com.ata.lambdaexpressions.classroom.model.Flavor;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class Phase1Test {
    private static final IceCreamParlorServiceComponent DAGGER = DaggerIceCreamParlorServiceComponent.create();

    @Spy
    private IceCreamParlorService service;

    @Captor
    private ArgumentCaptor<List<Carton>> buildSundaeCaptor;

    @BeforeEach
    private void setup() {
        service = DAGGER.provideIceCreamParlorService();
        initMocks(this);
    }

    @Test
    void getSundae_withNonexistentFlavor_ignoresFlavor() {
        // GIVEN
        List<String> flavorNames = ImmutableList.of("Strawberry", "Licorice");
        List<Flavor> expectedFlavors = ImmutableList.of(Flavor.STRAWBERRY);

        // WHEN
        service.getSundae(flavorNames);

        // THEN
        verify(service).buildSundae(buildSundaeCaptor.capture());
        List<Carton> cartons = buildSundaeCaptor.getValue();
        List<Flavor> flavors = cartons.stream().map(Carton::getFlavor).collect(Collectors.toList());
        assertEquals(expectedFlavors, flavors, "buildSundae did not receive expected flavors!");
    }

    @Test
    void getSundae_withFlavorOutOfStock_removesFlavor() {
        // GIVEN
        List<String> flavorNames = ImmutableList.of("Vanilla", "Chocolate", "Vanilla", "Strawberry");
        List<Flavor> expectedFlavors = ImmutableList.of(Flavor.VANILLA, Flavor.VANILLA, Flavor.STRAWBERRY);

        // WHEN
        service.getSundae(flavorNames);

        // THEN
        verify(service).buildSundae(buildSundaeCaptor.capture());
        List<Carton> cartons = buildSundaeCaptor.getValue();
        List<Flavor> flavors = cartons.stream().map(Carton::getFlavor).collect(Collectors.toList());
        assertEquals(expectedFlavors, flavors, "buildSundae did not receive expected flavors!");
    }
}
