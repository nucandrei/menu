package com.nuc.menu.app;

import javax.swing.*;
import java.util.Map;
import java.util.Set;

public interface Manager<T extends WithProperties> {
    int getFieldsCount();

    Set<String> getFieldNames();

    Set<T> getExistingItems();

    void remove(T item);

    Map<String, JComponent> getFieldsByName();

    ValidationResult validateInput();

    void addNewItem();

    void resetFields();
}
