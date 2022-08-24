package com.ata.lambdaexpressions.classroom.extension;

import com.ata.lambdaexpressions.classroom.IceCreamMaker;
import com.ata.lambdaexpressions.classroom.IceCreamParlorService;
import com.ata.lambdaexpressions.classroom.dao.RecipeDao;
import com.ata.lambdaexpressions.classroom.dependency.DaggerIceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.dependency.IceCreamParlorServiceComponent;
import com.ata.lambdaexpressions.classroom.model.Flavor;
import com.ata.lambdaexpressions.classroom.model.Ingredient;
import com.ata.lambdaexpressions.classroom.model.Sundae;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ParlorExtensionTest {
    private static final IceCreamParlorServiceComponent DAGGER = DaggerIceCreamParlorServiceComponent.create();

    @Spy
    private IceCreamParlorService service;

    @Captor
    private ArgumentCaptor<Supplier<Ingredient>> ingredientSupplierCaptor;

    @BeforeEach
    private void setup() {
        service = DAGGER.provideIceCreamParlorService();
        initMocks(this);
    }

    @Test
    void getSundae_withEmptyCarton_buildsCarton() {
        // GIVEN
        List<String> scoops = Arrays.asList("Chocolate", "Vanilla", "Strawberry");

        // WHEN
        Sundae sundae = service.getSundae(scoops);

        // THEN
        List<String> actualFlavors = sundae.getScoops().stream()
            .map(Flavor::getCommonName)
            .collect(Collectors.toList());
        assertEquals(scoops, actualFlavors, "Expected the shop to make any missing flavors!");
    }

}
