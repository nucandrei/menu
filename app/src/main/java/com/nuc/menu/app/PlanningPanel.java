package com.nuc.menu.app;

import com.nuc.menu.image.ImageManager;
import com.nuc.menu.ui.table.PermanentPrettyTableRow;
import com.nuc.menu.ui.table.PrettyTable;
import com.nuc.menu.ui.table.PrettyTableRow;

import javax.swing.*;
import java.awt.*;

public class PlanningPanel extends JPanel {
    public PlanningPanel() {

        this.setLayout(new BorderLayout());
        final PrettyTable headerTable = new PrettyTable(5, 5, true, false);
        headerTable.addRow(new PermanentPrettyTableRow(true, new JLabel("Data"), new JLabel("Numar calorii"), new JLabel("Proteine"), new JLabel("Lipide"), new JLabel("Glucide"), new JLabel()));
        addDayRow(headerTable, "1 Ianuarie 2018");
        this.add(new JScrollPane(headerTable), BorderLayout.NORTH);

        final PrettyTable table = new PrettyTable(6, 6, true, false);
        table.addRow(new PermanentPrettyTableRow(true, new JLabel("Masa"), new JLabel("Aliment"), new JLabel("Numar calorii"), new JLabel("Proteine"), new JLabel("Lipide"), new JLabel("Glucide"), new JLabel()));
        addDay(table);

        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void addDay(PrettyTable table) {
        addMealRow(table, "Mic dejun");
        addFoodItemRow(table, "Lapte");
        addFoodItemRow(table, "Cereale");
        addMealRow(table, "Gustare");
        addFoodItemRow(table, "Paine");
        addFoodItemRow(table, "Nuttela");
        addMealRow(table, "Pranz");
        addFoodItemRow(table, "Taitei");
        addFoodItemRow(table, "Cartofi");
        addFoodItemRow(table, "Carne vita");
        addMealRow(table, "Gustare");
        addFoodItemRow(table, "Mar");
        addFoodItemRow(table, "Ciocolata");
        addMealRow(table, "Cina");
        addFoodItemRow(table, "Mamaliga");
        addFoodItemRow(table, "Branza");
    }

    public void addDayRow(PrettyTable table, String date) {
        final JLabel jLabel = new JLabel(date);

        final JPanel dayPanel = new JPanel();
        dayPanel.add(new JButton(ImageManager.get(ImageManager.LEFT_SINGLE_IMAGE)));
        dayPanel.add(jLabel);
        dayPanel.add(new JButton(ImageManager.get(ImageManager.RIGHT_SINGLE_IMAGE)));

        table.addRow(new PermanentPrettyTableRow(true, dayPanel, new JLabel("250"), new JLabel("30"), new JLabel("30"), new JLabel("30"), new JLabel()));
    }

    public void addMealRow(PrettyTable table, String mealName) {
        table.addRow(new PermanentPrettyTableRow(true, new JLabel(mealName), new JButton(ImageManager.get(ImageManager.ADD_ROW_IMAGE)), new JLabel("250"), new JLabel("30"), new JLabel("30"), new JLabel("30")));
    }

    public void addFoodItemRow(PrettyTable table, String foodName) {
        table.addRow(new PrettyTableRow(ImageManager.get(ImageManager.REMOVE_ROW_IMAGE), true, true, new JLabel(""), new JLabel(foodName), new JLabel("250"), new JLabel("30"), new JLabel("30"), new JLabel("30")) {
            @Override
            public void onRemove() {

            }
        });
    }
}
