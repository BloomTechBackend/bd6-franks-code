package com.ata.lambdaexpressions.classroom.model;

/**
 * A carton of ice cream
 */
public class Carton {
    private final Flavor flavor;
    private boolean isEmpty;

    private Carton(Flavor flavor, boolean isEmpty) {
        this.flavor = flavor;
        this.isEmpty = isEmpty;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public static Carton getCarton(String name) {
        return new Carton(Flavor.byName(name), false);
    }

    public static Carton getEmptyCarton(String name) {
        return new Carton(Flavor.byName(name), true);
    }

    @Override
    public String toString() {
        return "Carton{" +
            "flavor=" + flavor +
            ", isEmpty=" + isEmpty +
            '}';
    }
}
