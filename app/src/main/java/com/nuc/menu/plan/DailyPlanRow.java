package com.nuc.menu.plan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DailyPlanRow implements NutritionalInfoListener {

    private final DailyPlan dailyPlan;
    private final Consumer<DailyPlanRow> consumer;
    private final List<MealPlanRow> mealPlanRows = new ArrayList<>();

    private final JLabel caloriesLabel;
    private final JLabel proteinsLabel;
    private final JLabel lipidsLabel;
    private final JLabel fatsLabel;

    public DailyPlanRow(String day, Consumer<DailyPlanRow> consumer) {
        this.dailyPlan = new DailyPlan(day);
        this.consumer = consumer;

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
    public void notifyChange(boolean rebuildModel) {
        caloriesLabel.setText(String.valueOf(dailyPlan.getCalories()));
        proteinsLabel.setText(String.valueOf(dailyPlan.getProteins()));
        lipidsLabel.setText(String.valueOf(dailyPlan.getLipids()));
        fatsLabel.setText(String.valueOf(dailyPlan.getFats()));

        if (rebuildModel) {
            consumer.accept(this);
        }
    }
}
