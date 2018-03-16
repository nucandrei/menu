package com.nuc.menu.app;

import com.nuc.menu.food.FoodItem;
import com.nuc.menu.food.FoodManager;
import com.nuc.menu.image.ImageManager;
import com.nuc.menu.ui.table.PermanentPrettyTableRow;
import com.nuc.menu.ui.table.PrettyTable;
import com.nuc.menu.ui.table.PrettyTableRow;

import javax.swing.*;
import java.awt.*;

public class FoodManagementPanel extends JPanel {
    private JFrame parentFrame;

    public FoodManagementPanel(JFrame parentFrame, FoodManager foodManager) {
        this.parentFrame = parentFrame;

        this.setLayout(new BorderLayout());

        final PrettyTable prettyTable = new PrettyTable(5, 5, true, true);
        populateWithFood(prettyTable, foodManager);

        this.add(new JScrollPane(prettyTable), BorderLayout.CENTER);
        this.add(new AddFoodItemPanel(foodManager, () -> populateWithFood(prettyTable, foodManager)), BorderLayout.SOUTH);
    }

    private void populateWithFood(PrettyTable prettyTable, FoodManager foodManager) {
        prettyTable.removeAll();
        prettyTable.addRow(new PermanentPrettyTableRow(true, new JLabel("Nume aliment"), new JLabel("Calorii"), new JLabel("Proteine"), new JLabel("Lipide"), new JLabel("Glucide")));


        foodManager.getFoodItems().forEach(foodItem -> prettyTable.addRow(generateRow(foodManager, foodItem)));
    }

    private PrettyTableRow generateRow(FoodManager foodManager, FoodItem foodItem) {
        return new PrettyTableRow(ImageManager.get(ImageManager.REMOVE_ROW_IMAGE),
                true,
                true,
                new JLabel(foodItem.getName()),
                new JLabel(foodItem.getCalories()),
                new JLabel(foodItem.getProtein()),
                new JLabel(foodItem.getLipids()),
                new JLabel(foodItem.getFats())) {
            @Override
            public void onRemove() {
                foodManager.removeFoodItem(foodItem);
            }

            @Override
            public boolean confirmRemove() {
                return Utils.promptUser(parentFrame, String.format("Sunteti sigur ca vreti sa stergeti produsul \"%s\"?", foodItem.getName()));
            }
        };
    }


}
