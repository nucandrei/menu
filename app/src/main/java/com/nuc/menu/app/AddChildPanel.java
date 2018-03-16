package com.nuc.menu.app;

import com.nuc.menu.child.Child;
import com.nuc.menu.child.ChildManager;
import com.nuc.menu.image.ImageManager;
import com.nuc.menu.ui.ErrorReporter;
import com.nuc.menu.ui.layout.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class AddChildPanel extends JPanel {
    private final JLabel errorLabel;
    private final JTextField nameTextField = new JTextField(20);
    private final JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(12, 1, 30, 1));
    private final JComboBox<String> genderBox = new JComboBox<>(new String[] {"Baiat", "Fata"});
    private final JTextField caloriesRequirementsField = new JTextField(5);

    public AddChildPanel(ChildManager childManager, Runnable onNewChildRunnable) {
        errorLabel = new JLabel();
        final ErrorReporter errorReporter = new ErrorReporter(errorLabel);

        setLayout(new BorderLayout());
        setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        final JPanel innerPanel = new JPanel();
        innerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(innerPanel, BorderLayout.CENTER);

        innerPanel.setLayout(new WrapLayout(FlowLayout.LEADING));
        addComponent(innerPanel, "Nume", nameTextField);
        addComponent(innerPanel, "Varsta", ageSpinner);
        addComponent(innerPanel, "Sex", genderBox);
        addComponent(innerPanel, "Necesar calorii", caloriesRequirementsField);

        final JButton addFoodItemBtn = new JButton(ImageManager.get(ImageManager.ADD_ROW_IMAGE));
        innerPanel.add(addFoodItemBtn);

        addFoodItemBtn.addActionListener(e -> {
            if (nameTextField.getText().isEmpty()) {
                errorReporter.setError("Nume copil", nameTextField);
                return;
            }

            if (childManager.nameAlreadyUsed(nameTextField.getText())) {
                errorReporter.setError("Nume copil prezent", nameTextField);
                return;
            }

            if (genderBox.getSelectedIndex() == -1) {
                errorReporter.setError("Sex neselectat", genderBox);
                return;
            }

            if (!isNumber(caloriesRequirementsField)) {
                errorReporter.setError("Numar invalid de calorii", caloriesRequirementsField);
                return;
            }

            childManager.addChild(new Child(nameTextField.getText(), ageSpinner.getValue().toString(), genderBox.getSelectedItem().toString(), caloriesRequirementsField.getText()));

            resetFields();
            onNewChildRunnable.run();
        });


        final JButton resetFieldsBtn = new JButton(ImageManager.get(ImageManager.RESET_ROW_IMAGE));
        resetFieldsBtn.addActionListener(e -> resetFields());
        innerPanel.add(resetFieldsBtn);
        innerPanel.add(errorLabel);
    }

    private void resetFields() {
        for (JTextField textField : new JTextField[]{nameTextField, caloriesRequirementsField}) {
            textField.setText("");
        }

        errorLabel.setText("");
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
