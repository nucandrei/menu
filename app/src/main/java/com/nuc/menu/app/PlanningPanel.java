package com.nuc.menu.app;

import com.nuc.menu.food.FoodItem;
import com.nuc.menu.image.ImageManager;
import com.nuc.menu.plan.DailyPlan;
import com.nuc.menu.plan.FoodPlan;
import com.nuc.menu.plan.MealPlan;
import com.nuc.menu.ui.table.PermanentPrettyTableRow;
import com.nuc.menu.ui.table.PrettyTable;
import com.nuc.menu.ui.table.PrettyTableRow;

import javax.swing.*;
import java.awt.*;

public class PlanningPanel extends JPanel {

    private final PrettyTable headerTable;
    private final PrettyTable table;

    public PlanningPanel() {
        this.setLayout(new BorderLayout());

        headerTable = new PrettyTable(5, 5, true, false);
        table = new PrettyTable(7, 7, true, false);

        this.add(new JScrollPane(headerTable), BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void switchTo(DailyPlan dailyPlan) {
        selectDailyPlan(dailyPlan);
        expandDailyPlan(dailyPlan);
    }

    private void selectDailyPlan(DailyPlan dailyPlan) {
        headerTable.removeAll();
        headerTable.addRow(new PermanentPrettyTableRow(true, new JLabel("Data"), new JLabel("Numar calorii"), new JLabel("Proteine"), new JLabel("Lipide"), new JLabel("Glucide"), new JLabel()));

        final JPanel dayPanel = new JPanel();
        dayPanel.add(new JButton(ImageManager.get(ImageManager.LEFT_SINGLE_IMAGE)));
        dayPanel.add(new JLabel(dailyPlan.getDay()));
        dayPanel.add(new JButton(ImageManager.get(ImageManager.RIGHT_SINGLE_IMAGE)));

        headerTable.addRow(new PermanentPrettyTableRow(false, dailyPlan.getComponents(dayPanel)));
    }

    private void expandDailyPlan(DailyPlan dailyPlan) {
        table.removeAll();

        table.addRow(new PermanentPrettyTableRow(true, new JLabel("Masa"), new JLabel("Aliment"), new JLabel("Portie (g)"), new JLabel("Numar calorii"), new JLabel("Proteine"), new JLabel("Lipide"), new JLabel("Glucide"), new JLabel()));
        for (MealPlan mealPlan : dailyPlan.getMealPlans()) {
            addMealRows(table, dailyPlan, mealPlan);
        }
    }

    private void addMealRows(PrettyTable table, DailyPlan dailyPlan, MealPlan mealPlan) {
        final JButton addMealComponent = new JButton(ImageManager.get(ImageManager.ADD_ROW_IMAGE));
        addMealComponent.addActionListener(e -> {
            final FoodItem foodItem = new FoodItem("Carne", "100", "30", "40", "40");
            mealPlan.add(new FoodPlan(foodItem));
            dailyPlan.update();
            switchTo(dailyPlan);
        });
        table.addRow(new PermanentPrettyTableRow(true, mealPlan.getComponents(addMealComponent)));

        for (FoodPlan foodPlan : mealPlan.getFoodPlans()) {
            addFoodItemRow(table, dailyPlan, mealPlan, foodPlan);
        }
    }

    private void addFoodItemRow(PrettyTable table, DailyPlan dailyPlan, MealPlan mealPlan, FoodPlan foodPlan) {
        final SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(foodPlan.getPortionSize(), 1, 1000, 1);
        final JSpinner spinner = new JSpinner(spinnerNumberModel);
        spinner.addChangeListener(e -> {
            final int value = (int) spinnerNumberModel.getValue();

            foodPlan.updatePortion(value);
            dailyPlan.update();
            mealPlan.update();
        });

        table.addRow(new PrettyTableRow(ImageManager.get(ImageManager.REMOVE_ROW_IMAGE), true, true, foodPlan.getComponents(spinner)) {
            @Override
            public void onRemove() {
                mealPlan.remove(foodPlan);
                dailyPlan.update();
            }
        });
    }
}
