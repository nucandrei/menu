package com.nuc.menu.plan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MealPlan {

    private final JLabel mealNameLabel;
    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    private double calories = 0;
    private double proteins = 0;
    private double lipids = 0;
    private double fats = 0;

    private final List<FoodPlan> foodPlans = new ArrayList<>();

    public MealPlan(String mealName) {
        mealNameLabel = new JLabel(mealName);
        caloriesLabel = new JLabel();
        proteinsLabel = new JLabel();
        lipidsLabel = new JLabel();
        fatsLabel = new JLabel();

        update();
    }

    public JComponent[] getComponents(JComponent addMealComponent) {
        return new JComponent[]{mealNameLabel, addMealComponent, new JLabel(), caloriesLabel, proteinsLabel, lipidsLabel, fatsLabel};
    }

    public void update() {
        calories = foodPlans.stream().mapToDouble(FoodPlan::getCalories).sum();
        proteins = foodPlans.stream().mapToDouble(FoodPlan::getProtein).sum();
        lipids = foodPlans.stream().mapToDouble(FoodPlan::getLipids).sum();
        fats = foodPlans.stream().mapToDouble(FoodPlan::getFats).sum();

        caloriesLabel.setText(String.valueOf(calories));
        proteinsLabel.setText(String.valueOf(proteins));
        lipidsLabel.setText(String.valueOf(lipids));
        fatsLabel.setText(String.valueOf(fats));
    }

    public void add(FoodPlan foodItem) {
        foodPlans.add(foodItem);
        update();
    }

    public void remove(FoodPlan foodItem) {
        foodPlans.remove(foodItem);
        update();
    }

    public List<FoodPlan> getFoodPlans() {
        return foodPlans;
    }

    public double getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getLipids() {
        return lipids;
    }

    public double getFats() {
        return fats;
    }
}
