package com.nuc.menu.app;

import com.nuc.menu.image.ImageManager;
import com.nuc.menu.ui.ErrorReporter;
import com.nuc.menu.ui.layout.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Map;

public class AddNewItemPanel<T extends WithProperties> extends JPanel {

    public AddNewItemPanel(Manager<T> manager, Runnable onNewItemRunnable) {
        JLabel errorLabel = new JLabel();
        final ErrorReporter errorReporter = new ErrorReporter(errorLabel);

        setLayout(new BorderLayout());
        setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        final JPanel innerPanel = new JPanel();
        innerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(innerPanel, BorderLayout.CENTER);

        innerPanel.setLayout(new WrapLayout(FlowLayout.LEADING));

        for (Map.Entry<String, JComponent> componentEntry : manager.getFieldsByName().entrySet()) {
            addComponent(innerPanel, componentEntry.getKey(), componentEntry.getValue());
        }

        final JButton addItemBtn = new JButton(ImageManager.get(ImageManager.ADD_ROW_IMAGE));
        innerPanel.add(addItemBtn);

        addItemBtn.addActionListener(e -> {
            final ValidationResult validationResult = manager.validateInput();
            if (!validationResult.isValid()) {
                errorReporter.setError(validationResult.getInvalidMessage(), validationResult.getInvalidComponent());
                return;
            }

            manager.addNewItem();
            errorLabel.setText("");
            onNewItemRunnable.run();
        });

        final JButton resetFieldsBtn = new JButton(ImageManager.get(ImageManager.RESET_ROW_IMAGE));
        resetFieldsBtn.addActionListener(e -> manager.resetFields());
        innerPanel.add(resetFieldsBtn);
        innerPanel.add(errorLabel);
    }

    private static void addComponent(JPanel panel, String name, JComponent component) {
        final JPanel createdField = createField(name, component);
        panel.add(createdField);
    }

    private static JPanel createField(String name, JComponent component) {
        final JPanel panel = new JPanel();
        panel.add(new JLabel(name));
        panel.add(component);
        return panel;
    }
}
