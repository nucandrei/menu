package com.nuc.menu.plan;

import com.nuc.menu.food.FoodItem;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class DailyPlan {
    private String day;

    private final List<MealPlan> mealPlans = Arrays.asList(
            new MealPlan("Mic dejun"),
            new MealPlan("Gustare dimineata"),
            new MealPlan("Pranz"),
            new MealPlan("Gustare dupamasa"),
            new MealPlan("Cina"));

    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    private double calories = 0;
    private double proteins = 0;
    private double lipids = 0;
    private double fats = 0;

    public DailyPlan(String day) {
        this.day = day;

        caloriesLabel = new JLabel();
        proteinsLabel = new JLabel();
        lipidsLabel = new JLabel();
        fatsLabel = new JLabel();

        updateLabels();
    }

    public JComponent[] getComponents(JComponent titleComponent) {
        return new JComponent[]{titleComponent, caloriesLabel, proteinsLabel, lipidsLabel, fatsLabel};
    }

    public String getDay() {
        return day;
    }

    public List<MealPlan> getMealPlans() {
        return mealPlans;
    }

    private void updateLabels() {
        caloriesLabel.setText(String.valueOf(calories));
        proteinsLabel.setText(String.valueOf(proteins));
        lipidsLabel.setText(String.valueOf(lipids));
        fatsLabel.setText(String.valueOf(fats));
    }

    public void addFoodItem(MealPlan mealPlan, FoodItem foodItem) {
        calories += Double.parseDouble(foodItem.getCalories());
        proteins += Double.parseDouble(foodItem.getProtein());
        lipids += Double.parseDouble(foodItem.getLipids());
        fats += Double.parseDouble(foodItem.getFats());
        updateLabels();

        mealPlan.add(foodItem);
    }

    public void remove(MealPlan mealPlan, FoodItem foodItem) {
        calories -= Double.parseDouble(foodItem.getCalories());
        proteins -= Double.parseDouble(foodItem.getProtein());
        lipids -= Double.parseDouble(foodItem.getLipids());
        fats -= Double.parseDouble(foodItem.getFats());
        updateLabels();

        mealPlan.remove(foodItem);
    }
}
