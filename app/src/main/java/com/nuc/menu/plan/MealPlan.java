package com.nuc.menu.plan;

import com.nuc.menu.food.FoodItem;

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

    private final List<FoodItem> foodItems = new ArrayList<>();

    public MealPlan(String mealName) {
        mealNameLabel = new JLabel(mealName);
        caloriesLabel = new JLabel();
        proteinsLabel = new JLabel();
        lipidsLabel = new JLabel();
        fatsLabel = new JLabel();

        updateLabels();
    }

    public JComponent[] getComponents(JComponent addMealComponent) {
        return new JComponent[]{mealNameLabel, addMealComponent, caloriesLabel, proteinsLabel, lipidsLabel, fatsLabel};
    }

    private void updateLabels() {
        caloriesLabel.setText(String.valueOf(calories));
        proteinsLabel.setText(String.valueOf(proteins));
        lipidsLabel.setText(String.valueOf(lipids));
        fatsLabel.setText(String.valueOf(fats));
    }

    public void add(FoodItem foodItem) {
        foodItems.add(foodItem);

        calories += Double.parseDouble(foodItem.getCalories());
        proteins += Double.parseDouble(foodItem.getProtein());
        lipids += Double.parseDouble(foodItem.getLipids());
        fats += Double.parseDouble(foodItem.getFats());
        updateLabels();
    }

    public void remove(FoodItem foodItem) {
        foodItems.remove(foodItem);

        calories -= Double.parseDouble(foodItem.getCalories());
        proteins -= Double.parseDouble(foodItem.getProtein());
        lipids -= Double.parseDouble(foodItem.getLipids());
        fats -= Double.parseDouble(foodItem.getFats());
        updateLabels();
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }
}
