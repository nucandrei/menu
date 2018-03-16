package com.nuc.menu.food;

public class FoodItem implements Comparable<FoodItem>{

    private final String name;
    private final String calories;
    private final String protein;
    private final String lipids;
    private final String fats;

    public FoodItem(String name, String qty, String calories, String protein, String lipids, String fats) {
        final Double ratio = Double.parseDouble(qty);

        this.name = name;
        this.calories = Double.toString(Double.parseDouble(calories) * (100 / ratio));
        this.protein = Double.toString(Double.parseDouble(protein) * (100 / ratio));
        this.lipids = Double.toString(Double.parseDouble(lipids) * (100 / ratio));
        this.fats = Double.toString(Double.parseDouble(fats) * (100 / ratio));
    }

    public FoodItem(String name, String calories, String protein, String lipids, String fats) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.lipids = lipids;
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public String getCalories() {
        return calories;
    }

    public String getProtein() {
        return protein;
    }

    public String getLipids() {
        return lipids;
    }

    public String getFats() {
        return fats;
    }

    @Override
    public int compareTo(FoodItem foodItem) {
        return name.compareTo(foodItem.getName());
    }
}
