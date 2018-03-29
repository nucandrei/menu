package com.nuc.menu.plan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MealPlanRow implements NutritionalInfoListener {

    private final JLabel mealNameLabel;
    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    private final List<FoodPlanRow> foodPlanRows = new ArrayList<>();
    private final MealPlan mealPlan;

    MealPlanRow(String mealName) {
        mealPlan = new MealPlan(mealName);

        mealNameLabel = new JLabel(mealPlan.getMealName());
        caloriesLabel = new JLabel();
        proteinsLabel = new JLabel();
        lipidsLabel = new JLabel();
        fatsLabel = new JLabel();

        mealPlan.addListener(this);
    }

    public JComponent[] getComponents(JComponent addMealComponent) {
        return new JComponent[]{mealNameLabel, addMealComponent, new JLabel(), caloriesLabel, proteinsLabel, lipidsLabel, fatsLabel};
    }

    public void add(FoodPlanRow foodItem) {
        foodPlanRows.add(foodItem);
        mealPlan.addFoodPlan(foodItem.getFoodPlan());
    }

    public void remove(FoodPlanRow foodItem) {
        foodPlanRows.remove(foodItem);
        mealPlan.removeFoodPlan(foodItem.getFoodPlan());
    }

    public List<FoodPlanRow> getFoodPlanRows() {
        return foodPlanRows;
    }

    @Override
    public void notifyChange() {
        caloriesLabel.setText(String.valueOf(mealPlan.getCalories()));
        proteinsLabel.setText(String.valueOf(mealPlan.getProteins()));
        lipidsLabel.setText(String.valueOf(mealPlan.getLipids()));
        fatsLabel.setText(String.valueOf(mealPlan.getFats()));
    }

    MealPlan getMealPlan() {
        return mealPlan;
    }
}
