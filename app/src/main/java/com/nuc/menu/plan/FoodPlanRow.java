package com.nuc.menu.plan;

import com.nuc.menu.food.FoodItem;

import javax.swing.*;

public class FoodPlanRow implements NutritionalInfoListener {

    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    private final FoodPlan foodPlan;
    private final JSpinner spinner;

    public FoodPlanRow(FoodItem foodItem) {
        this.foodPlan = new FoodPlan(foodItem, 100);
        caloriesLabel = new JLabel();
        proteinsLabel = new JLabel();
        lipidsLabel = new JLabel();
        fatsLabel = new JLabel();

        final SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(foodPlan.getPortion(), 1, 1000, 1);
        spinner = new JSpinner(spinnerNumberModel);
        spinner.addChangeListener(e -> {
            final int value = (int) spinnerNumberModel.getValue();
            updatePortion(value);
        });

        this.foodPlan.addListener(this);
    }

    private void updatePortion(int portionSize) {
        this.foodPlan.updatePortion(portionSize);
    }

    public JComponent[] getComponents() {
        return new JComponent[]{new JLabel(""), new JLabel(foodPlan.getName()), spinner, caloriesLabel, proteinsLabel, lipidsLabel, fatsLabel};
    }

    FoodPlan getFoodPlan() {
        return foodPlan;
    }

    @Override
    public void notifyChange() {
        caloriesLabel.setText(String.valueOf(foodPlan.getCalories()));
        proteinsLabel.setText(String.valueOf(foodPlan.getProteins()));
        lipidsLabel.setText(String.valueOf(foodPlan.getLipids()));
        fatsLabel.setText(String.valueOf(foodPlan.getFats()));


    }
}
