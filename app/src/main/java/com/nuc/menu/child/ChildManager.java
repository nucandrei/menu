package com.nuc.menu.child;

import com.nuc.menu.Utils;
import com.nuc.menu.app.Manager;
import com.nuc.menu.app.ValidationResult;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ChildManager implements Manager<Child> {
    private final Set<Child> children;
    private ChildStorage childStorage;

    private static final JTextField nameTextField = new JTextField(20);
    private static final JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(12, 1, 30, 1));
    private static final JComboBox<String> genderBox = new JComboBox<>(new String[]{"Baiat", "Fata"});
    private static final JTextField caloriesRequirementsField = new JTextField(5);

    private static final Map<String, JComponent> COMPONENTS_BY_NAME = new LinkedHashMap<>();

    static {
        COMPONENTS_BY_NAME.put("Nume", nameTextField);
        COMPONENTS_BY_NAME.put("Varsta", ageSpinner);
        COMPONENTS_BY_NAME.put("Sex", genderBox);
        COMPONENTS_BY_NAME.put("Necesar calorii", caloriesRequirementsField);
    }

    public ChildManager(ChildStorage childStorage) {
        this.childStorage = childStorage;
        this.children = childStorage.getChildren();
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void addChild(Child child) {
        children.add(child);
        childStorage.saveChildren(children);
    }

    public void removeChild(Child child) {
        children.remove(child);
        childStorage.saveChildren(children);
    }

    public boolean nameAlreadyUsed(String text) {
        return children.stream().anyMatch(child -> child.getName().equals(text));
    }

    @Override
    public int getFieldsCount() {
        return COMPONENTS_BY_NAME.size();
    }

    @Override
    public Set<String> getFieldNames() {
        return COMPONENTS_BY_NAME.keySet();
    }

    @Override
    public Set<Child> getExistingItems() {
        return children;
    }

    @Override
    public void remove(Child item) {
        children.remove(item);
        childStorage.saveChildren(children);
    }

    @Override
    public Map<String, JComponent> getFieldsByName() {
        return COMPONENTS_BY_NAME;
    }

    @Override
    public ValidationResult validateInput() {
        if (nameTextField.getText().isEmpty()) {
            return new ValidationResult("Nume copil lipsa", nameTextField);
        }

        if (nameAlreadyUsed(nameTextField.getText())) {
            return new ValidationResult("Nume copil prezent", nameTextField);
        }

        if (!Utils.isNumber(caloriesRequirementsField)) {
            return new ValidationResult("Numar invalid de calorii", caloriesRequirementsField);
        }

        return ValidationResult.VALID;
    }

    @Override
    public void addNewItem() {
        addChild(new Child(nameTextField.getText(), ageSpinner.getValue().toString(), genderBox.getSelectedItem().toString(), caloriesRequirementsField.getText()));
    }

    @Override
    public void resetFields() {
        nameTextField.setText("");
        ageSpinner.setValue(1);
        genderBox.setSelectedIndex(0);
        caloriesRequirementsField.setText("");
    }
}
