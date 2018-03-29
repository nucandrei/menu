package com.nuc.menu.plan;

import com.nuc.menu.food.FoodItem;

import javax.swing.*;

public class FoodPlanRow {
    private FoodItem foodItem;
    private int portionSize;

    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    private double calories;
    private double proteins;
    private double lipids;
    private double fats;

    public FoodPlanRow(FoodItem foodItem) {
        this.foodItem = foodItem;

        caloriesLabel = new JLabel();
        proteinsLabel = new JLabel();
        lipidsLabel = new JLabel();
        fatsLabel = new JLabel();

        updatePortion(100);
    }

    private void computeBasedOnPortion() {
        this.calories = Double.parseDouble(foodItem.getCalories()) * portionSize / 100.0;
        this.proteins = Double.parseDouble(foodItem.getProtein()) * portionSize / 100.0;
        this.lipids = Double.parseDouble(foodItem.getLipids()) * portionSize / 100.0;
        this.fats = Double.parseDouble(foodItem.getFats()) * portionSize / 100.0;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return proteins;
    }

    public double getLipids() {
        return lipids;
    }

    public double getFats() {
        return fats;
    }

    public void updatePortion(int portionSize) {
        this.portionSize = portionSize;

        computeBasedOnPortion();
        updateLabels();
    }

    public JComponent[] getComponents(JSpinner spinner) {
        return new JComponent[]{new JLabel(""), new JLabel(foodItem.getName()), spinner, caloriesLabel, proteinsLabel, lipidsLabel, fatsLabel};
    }

    private void updateLabels() {
        caloriesLabel.setText(String.valueOf(calories));
        proteinsLabel.setText(String.valueOf(proteins));
        lipidsLabel.setText(String.valueOf(lipids));
        fatsLabel.setText(String.valueOf(fats));
    }

    public int getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(int portionSize) {
        this.portionSize = portionSize;
    }
}
