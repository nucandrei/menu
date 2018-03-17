package com.nuc.menu;

import javax.swing.*;

public class Utils {
    public static boolean isNumber(JTextField textField) {
        try {
            final Double number = Double.parseDouble(textField.getText());
            return number >= 0;

        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
