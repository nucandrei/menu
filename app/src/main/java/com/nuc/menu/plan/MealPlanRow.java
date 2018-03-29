package com.nuc.menu.plan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MealPlanRow {

    private final JLabel mealNameLabel;
    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    private int calories = 0;
    private double proteins = 0;
    private double lipids = 0;
    private double fats = 0;

    private final List<FoodPlanRow> foodPlanRows = new ArrayList<>();

    public MealPlanRow(String mealName) {
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
        calories = foodPlanRows.stream().mapToInt(FoodPlanRow::getCalories).sum();
        proteins = foodPlanRows.stream().mapToDouble(FoodPlanRow::getProtein).sum();
        lipids = foodPlanRows.stream().mapToDouble(FoodPlanRow::getLipids).sum();
        fats = foodPlanRows.stream().mapToDouble(FoodPlanRow::getFats).sum();

        caloriesLabel.setText(String.valueOf(calories));
        proteinsLabel.setText(String.valueOf(proteins));
        lipidsLabel.setText(String.valueOf(lipids));
        fatsLabel.setText(String.valueOf(fats));
    }

    public void add(FoodPlanRow foodItem) {
        foodPlanRows.add(foodItem);
        update();
    }

    public void remove(FoodPlanRow foodItem) {
        foodPlanRows.remove(foodItem);
        update();
    }

    public List<FoodPlanRow> getFoodPlanRows() {
        return foodPlanRows;
    }

    public int getCalories() {
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
