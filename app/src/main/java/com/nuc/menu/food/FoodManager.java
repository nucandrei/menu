package com.nuc.menu.food;

import com.nuc.menu.Utils;
import com.nuc.menu.app.Manager;
import com.nuc.menu.app.ValidationResult;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FoodManager implements Manager<FoodItem> {
    private static final String NAME = "Nume";
    private static final String CALORIES = "Calorii";
    private static final String PROTEINS = "Proteine";
    private static final String LIPIDS = "Lipide";
    private static final String FATS = "Glucide";

    private static final HashSet<String> FIELDS = new LinkedHashSet<>(Arrays.asList(NAME, CALORIES, PROTEINS, LIPIDS, FATS));

    private static final Collector<String, ?, Map<String, JComponent>> COLLECTOR = Collectors.toMap(field -> field, field -> new JTextField(10), (jComponent, jComponent2) -> jComponent, LinkedHashMap::new);
    private static final Map<String, JComponent> COMPONENTS_MAP = FIELDS.stream().collect(COLLECTOR);

    private final FoodStorage foodStorage;
    private final Set<FoodItem> foodItems;

    public FoodManager(FoodStorage foodStorage) {
        this.foodStorage = foodStorage;
        this.foodItems = foodStorage.getFoodItems();
    }

    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
        foodStorage.saveFoodItems(foodItems);
    }

    @Override
    public void remove(FoodItem foodItem) {
        foodItems.remove(foodItem);
        foodStorage.saveFoodItems(foodItems);
    }

    public boolean nameAlreadyUsed(String text) {
        return foodItems.stream().anyMatch(foodItem -> foodItem.getName().equals(text));
    }

    @Override
    public int getFieldsCount() {
        return FIELDS.size();
    }

    @Override
    public Set<String> getFieldNames() {
        return FIELDS;
    }

    @Override
    public Set<FoodItem> getExistingItems() {
        return foodItems;
    }

    @Override
    public Map<String, JComponent> getFieldsByName() {
        return COMPONENTS_MAP;
    }

    @Override
    public ValidationResult validateInput() {
        final JTextField nameTextField = (JTextField) COMPONENTS_MAP.get(NAME);
        if (nameTextField.getText().isEmpty()) {
            return new ValidationResult("Nume aliment lipsa", nameTextField);
        }

        if (nameAlreadyUsed(nameTextField.getText())) {
            return new ValidationResult("Aliment deja prezent", nameTextField);
        }

        final JTextField caloriesTextField = (JTextField) COMPONENTS_MAP.get(CALORIES);
        if (!Utils.isNumber(caloriesTextField)) {
            return new ValidationResult("Numar invalid de calorii", caloriesTextField);
        }

        final JTextField proteinTextField = (JTextField) COMPONENTS_MAP.get(PROTEINS);
        if (!Utils.isNumber(proteinTextField)) {
            return new ValidationResult("Numar invalid de proteine", proteinTextField);
        }

        final JTextField lipidsTextField = (JTextField) COMPONENTS_MAP.get(LIPIDS);
        if (!Utils.isNumber(lipidsTextField)) {
            return new ValidationResult("Numar invalid de lipide", lipidsTextField);
        }

        final JTextField fatsTextField = (JTextField) COMPONENTS_MAP.get(FATS);
        if (!Utils.isNumber(fatsTextField)) {
            return new ValidationResult("Numar invalid de grasimi", fatsTextField);
        }

        return ValidationResult.VALID;
    }

    @Override
    public void addNewItem() {
        final JTextField nameTextField = (JTextField) COMPONENTS_MAP.get(NAME);
        final JTextField caloriesTextField = (JTextField) COMPONENTS_MAP.get(CALORIES);
        final JTextField proteinTextField = (JTextField) COMPONENTS_MAP.get(PROTEINS);
        final JTextField lipidsTextField = (JTextField) COMPONENTS_MAP.get(LIPIDS);
        final JTextField fatsTextField = (JTextField) COMPONENTS_MAP.get(FATS);

        addFoodItem(new FoodItem(nameTextField.getText(), caloriesTextField.getText(), proteinTextField.getText(), lipidsTextField.getText(), fatsTextField.getText()));
    }

    @Override
    public void resetFields() {
        for (JComponent textField : COMPONENTS_MAP.values()) {
            ((JTextField) textField).setText("");
        }
    }
}
