package com.ata.lambdaexpressions.classroom.dao;

import com.ata.lambdaexpressions.classroom.model.Carton;
import com.ata.lambdaexpressions.classroom.model.Flavor;

import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 * Provides access to cartons of ice cream in storage.
 */
public class CartonDao {
    private static final List<String> CARTONS = ImmutableList.of("Vanilla", "Strawberry");
    private static final List<String> EMPTY_CARTONS = ImmutableList.of("Chocolate");

    private final Map<String, Carton> cartons = new HashMap<>();

    @Inject
    public CartonDao() {
        for (String flavor : CARTONS) {
            cartons.put(flavor, Carton.getCarton(flavor));
        }
        for (String emptyFlavor : EMPTY_CARTONS) {
            cartons.put(emptyFlavor, Carton.getEmptyCarton(emptyFlavor));
        }
    }

    public List<Carton> getCartonsByFlavorNames(List<String> flavorNames) {
        return flavorNames.stream()
            .map(cartons::get)
            .filter(carton -> !Objects.isNull(carton))
            .collect(Collectors.toList());
    }

    /**
     * Adds or replaces a Carton. The Carton may be empty.
     * @param carton A carton, empty or full.
     * @return The Carton that this carton replaces.
     */
    public Carton addCarton(Carton carton) {
        String flavor = carton.getFlavor().getCommonName();
        return cartons.put(flavor, carton);
    }
}
