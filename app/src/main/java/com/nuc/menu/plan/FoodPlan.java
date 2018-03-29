package com.nuc.menu.plan;

import com.nuc.menu.food.FoodItem;

public class FoodPlan extends NutritionalInfo {

    private final int foodCalories;
    private final double foodFats;
    private final double foodLipids;
    private final double foodProteins;

    private final FoodItem foodItem;
    private int portion;

    FoodPlan(FoodItem foodItem, int portion) {
        this.foodItem = foodItem;

        foodCalories = Integer.parseInt(foodItem.getCalories());
        foodFats = Double.parseDouble(foodItem.getFats());
        foodLipids = Double.parseDouble(foodItem.getLipids());
        foodProteins = Double.parseDouble(foodItem.getProtein());

        updatePortion(portion);
    }

    void updatePortion(int portion) {
        this.portion = portion;

        setCalories(foodCalories * portion / 100);
        setProteins(foodProteins * portion / 100);
        setLipids(foodLipids * portion / 100);
        setFats(foodFats * portion / 100);

        notifyAllListeners();
    }

    int getPortion() {
        return portion;
    }

    public String getName() {
        return foodItem.getName();
    }
}
