package com.nuc.menu.plan;

import com.nuc.menu.food.FoodItem;

import javax.swing.*;

public class FoodPlanRow {

    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    private final FoodPlan foodPlan;

    public FoodPlanRow(FoodItem foodItem) {
        this.foodPlan = new FoodPlan(foodItem, 100);

        caloriesLabel = new JLabel();
        proteinsLabel = new JLabel();
        lipidsLabel = new JLabel();
        fatsLabel = new JLabel();

        updatePortion(100);
    }

    public int getCalories() {
        return foodPlan.getCalories();
    }

    public double getProtein() {
        return foodPlan.getProteins();
    }

    public double getLipids() {
        return foodPlan.getLipids();
    }

    public double getFats() {
        return foodPlan.getFats();
    }

    public void updatePortion(int portionSize) {
        this.foodPlan.updatePortion(portionSize);
        updateLabels();
    }

    public JComponent[] getComponents(JSpinner spinner) {
        return new JComponent[]{new JLabel(""), new JLabel(foodPlan.getName()), spinner, caloriesLabel, proteinsLabel, lipidsLabel, fatsLabel};
    }

    private void updateLabels() {
        caloriesLabel.setText(String.valueOf(foodPlan.getCalories()));
        proteinsLabel.setText(String.valueOf(foodPlan.getProteins()));
        lipidsLabel.setText(String.valueOf(foodPlan.getLipids()));
        fatsLabel.setText(String.valueOf(foodPlan.getFats()));
    }

    public int getPortionSize() {
        return foodPlan.getPortion();
    }
}
