package com.nuc.menu.plan;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class DailyPlanRow {
    private String day;

    private final List<MealPlanRow> mealPlanRows = Arrays.asList(
            new MealPlanRow("Mic dejun"),
            new MealPlanRow("Gustare dimineata"),
            new MealPlanRow("Pranz"),
            new MealPlanRow("Gustare dupamasa"),
            new MealPlanRow("Cina"));

    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    private int calories = 0;
    private double proteins = 0;
    private double lipids = 0;
    private double fats = 0;

    public DailyPlanRow(String day) {
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

    public void update() {
        calories = mealPlanRows.stream().mapToInt(MealPlanRow::getCalories).sum();
        proteins = mealPlanRows.stream().mapToDouble(MealPlanRow::getProteins).sum();
        lipids = mealPlanRows.stream().mapToDouble(MealPlanRow::getLipids).sum();
        fats = mealPlanRows.stream().mapToDouble(MealPlanRow::getFats).sum();

        updateLabels();
    }

    public List<MealPlanRow> getMealPlanRows() {
        return mealPlanRows;
    }

    private void updateLabels() {
        caloriesLabel.setText(String.valueOf(calories));
        proteinsLabel.setText(String.valueOf(proteins));
        lipidsLabel.setText(String.valueOf(lipids));
        fatsLabel.setText(String.valueOf(fats));
    }
}
