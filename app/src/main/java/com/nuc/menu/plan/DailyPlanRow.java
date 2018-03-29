package com.nuc.menu.plan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DailyPlanRow implements NutritionalInfoListener {

    private final DailyPlan dailyPlan;
    private final List<MealPlanRow> mealPlanRows = new ArrayList<>();

    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    public DailyPlanRow(String day) {
        this.dailyPlan = new DailyPlan(day);

        caloriesLabel = new JLabel();
        proteinsLabel = new JLabel();
        lipidsLabel = new JLabel();
        fatsLabel = new JLabel();

        dailyPlan.addListener(this);

        addMeal(new MealPlanRow("Mic dejun"));
        addMeal(new MealPlanRow("Gustare dimineata"));
        addMeal(new MealPlanRow("Pranz"));
        addMeal(new MealPlanRow("Gustare dupamasa"));
        addMeal(new MealPlanRow("Cina"));
    }

    public void addMeal(MealPlanRow mealPlanRow) {
        mealPlanRows.add(mealPlanRow);
        dailyPlan.addMealPlan(mealPlanRow.getMealPlan());
    }

    public JComponent[] getComponents(JComponent titleComponent) {
        return new JComponent[]{titleComponent, caloriesLabel, proteinsLabel, lipidsLabel, fatsLabel};
    }

    public String getDay() {
        return dailyPlan.getDay();
    }

    public List<MealPlanRow> getMealPlanRows() {
        return mealPlanRows;
    }

    @Override
    public void notifyChange() {
        caloriesLabel.setText(String.valueOf(dailyPlan.getCalories()));
        proteinsLabel.setText(String.valueOf(dailyPlan.getProteins()));
        lipidsLabel.setText(String.valueOf(dailyPlan.getLipids()));
        fatsLabel.setText(String.valueOf(dailyPlan.getFats()));
    }
}
