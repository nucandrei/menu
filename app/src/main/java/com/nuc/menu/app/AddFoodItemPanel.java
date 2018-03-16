package com.nuc.menu.app;

import com.nuc.menu.food.FoodItem;
import com.nuc.menu.food.FoodManager;
import com.nuc.menu.image.ImageManager;
import com.nuc.menu.ui.ErrorReporter;
import com.nuc.menu.ui.layout.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class AddFoodItemPanel extends JPanel {

    private final JTextField nameTextField= new JTextField(10);
    private final JTextField qtyTextField = new JTextField("100", 10);
    private final JTextField caloriesTextField= new JTextField(10);
    private final JTextField proteinTextField = new JTextField(10);
    private final JTextField lipidsTextField = new JTextField(10);
    private final JTextField fatsTextField = new JTextField(10);
    private final JLabel errorLabel;

    public AddFoodItemPanel(FoodManager foodManager, Runnable onNewFoodItemRunnable) {

        errorLabel = new JLabel();
        final ErrorReporter errorReporter = new ErrorReporter(errorLabel);

        setLayout(new BorderLayout());
        setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        final JPanel innerPanel = new JPanel();
        innerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(innerPanel, BorderLayout.CENTER);

        innerPanel.setLayout(new WrapLayout(FlowLayout.LEADING));
        addComponent(innerPanel, "Nume", nameTextField);
        addComponent(innerPanel, "Portie (g)", qtyTextField);
        addComponent(innerPanel, "Calorii", caloriesTextField);
        addComponent(innerPanel, "Proteine", proteinTextField);
        addComponent(innerPanel, "Lipide", lipidsTextField);
        addComponent(innerPanel, "Glucide", fatsTextField);

        final JButton addFoodItemBtn = new JButton(ImageManager.get(ImageManager.ADD_ROW_IMAGE));
        innerPanel.add(addFoodItemBtn);

        addFoodItemBtn.addActionListener(e -> {
            if (nameTextField.getText().isEmpty()) {
                errorReporter.setError("Nume aliment lipsa", nameTextField);
                return;
            }

            if (foodManager.nameAlreadyUsed(nameTextField.getText())) {
                errorReporter.setError("Aliment deja prezent", nameTextField);
                return;
            }

            if (!isNumber(qtyTextField)) {
                errorReporter.setError("Cantitate invalida", qtyTextField);
                return;
            }

            if (!isNumber(caloriesTextField)) {
                errorReporter.setError("Numar invalid de calorii", caloriesTextField);
                return;
            }

            if (!isNumber(proteinTextField)) {
                errorReporter.setError("Numar invalid de proteine", proteinTextField);
                return;
            }

            if (!isNumber(lipidsTextField)) {
                errorReporter.setError("Numar invalid de lipide", lipidsTextField);
                return;
            }

            if (!isNumber(fatsTextField)) {
                errorReporter.setError("Numar invalid de glucide", fatsTextField);
                return;
            }

            foodManager.addFoodItem(new FoodItem(nameTextField.getText(), qtyTextField.getText(), caloriesTextField.getText(), proteinTextField.getText(), lipidsTextField.getText(), fatsTextField.getText()));

            resetFields();
            onNewFoodItemRunnable.run();
        });


        final JButton resetFieldsBtn = new JButton(ImageManager.get(ImageManager.RESET_ROW_IMAGE));
        resetFieldsBtn.addActionListener(e -> resetFields());
        innerPanel.add(resetFieldsBtn);
        innerPanel.add(errorLabel);
    }

    private void resetFields() {
        for (JTextField textField : new JTextField[]{nameTextField, qtyTextField, caloriesTextField, proteinTextField, lipidsTextField, fatsTextField}) {
            textField.setText("");
        }

        errorLabel.setText("");
        qtyTextField.setText("100");
    }

    private static boolean isNumber(JTextField textField) {
        try {
            final Double number = Double.parseDouble(textField.getText());
            return number >= 0;

        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private static JPanel addComponent(JPanel panel, String name, JComponent component) {
        final JPanel createdField = createField(name, component);
        panel.add(createdField);
        return createdField;
    }

    private static JPanel createField(String name, JComponent component) {
        final JPanel panel = new JPanel();
        panel.add(new JLabel(name));
        panel.add(component);
        return panel;
    }
}
