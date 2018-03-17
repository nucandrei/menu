package com.nuc.menu.app;

import javax.swing.*;

public class ValidationResult {
    public static final ValidationResult VALID = new ValidationResult(null, null);

    private final String invalidMessage;
    private final JComponent invalidComponent;

    public ValidationResult(String invalidMessage, JComponent invalidComponent) {
        this.invalidMessage = invalidMessage;
        this.invalidComponent = invalidComponent;
    }

    public boolean isValid() {
        return invalidComponent == null;
    }

    public String getInvalidMessage() {
        return invalidMessage;
    }

    public JComponent getInvalidComponent() {
        return invalidComponent;
    }
}
