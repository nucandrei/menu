package com.nuc.menu.app;

import com.nuc.menu.food.FoodItem;
import com.nuc.menu.image.ImageManager;
import com.nuc.menu.plan.DailyPlanRow;
import com.nuc.menu.plan.FoodPlanRow;
import com.nuc.menu.plan.MealPlanRow;
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

    public void switchTo(DailyPlanRow dailyPlanRow) {
        selectDailyPlan(dailyPlanRow);
        expandDailyPlan(dailyPlanRow);
    }

    private void selectDailyPlan(DailyPlanRow dailyPlanRow) {
        headerTable.removeAll();
        headerTable.addRow(new PermanentPrettyTableRow(true, new JLabel("Data"), new JLabel("Numar calorii"), new JLabel("Proteine"), new JLabel("Lipide"), new JLabel("Glucide"), new JLabel()));

        final JPanel dayPanel = new JPanel();
        dayPanel.add(new JButton(ImageManager.get(ImageManager.LEFT_SINGLE_IMAGE)));
        dayPanel.add(new JLabel(dailyPlanRow.getDay()));
        dayPanel.add(new JButton(ImageManager.get(ImageManager.RIGHT_SINGLE_IMAGE)));

        headerTable.addRow(new PermanentPrettyTableRow(false, dailyPlanRow.getComponents(dayPanel)));
    }

    private void expandDailyPlan(DailyPlanRow dailyPlanRow) {
        table.removeAll();

        table.addRow(new PermanentPrettyTableRow(true, new JLabel("Masa"), new JLabel("Aliment"), new JLabel("Portie (g)"), new JLabel("Numar calorii"), new JLabel("Proteine"), new JLabel("Lipide"), new JLabel("Glucide"), new JLabel()));
        for (MealPlanRow mealPlanRow : dailyPlanRow.getMealPlanRows()) {
            addMealRows(table, dailyPlanRow, mealPlanRow);
        }
    }

    private void addMealRows(PrettyTable table, DailyPlanRow dailyPlanRow, MealPlanRow mealPlanRow) {
        final JButton addMealComponent = new JButton(ImageManager.get(ImageManager.ADD_ROW_IMAGE));
        addMealComponent.addActionListener(e -> {
            final FoodItem foodItem = new FoodItem("Carne", "100", "30", "40", "40");
            mealPlanRow.add(new FoodPlanRow(foodItem));
            switchTo(dailyPlanRow);
        });
        table.addRow(new PermanentPrettyTableRow(true, mealPlanRow.getComponents(addMealComponent)));

        for (FoodPlanRow foodPlanRow : mealPlanRow.getFoodPlanRows()) {
            addFoodItemRow(table, mealPlanRow, foodPlanRow);
        }
    }

    private void addFoodItemRow(PrettyTable table, MealPlanRow mealPlanRow, FoodPlanRow foodPlanRow) {
        table.addRow(new PrettyTableRow(ImageManager.get(ImageManager.REMOVE_ROW_IMAGE), true, true, foodPlanRow.getComponents()) {
            @Override
            public void onRemove() {
                mealPlanRow.remove(foodPlanRow);
            }
        });
    }
}
