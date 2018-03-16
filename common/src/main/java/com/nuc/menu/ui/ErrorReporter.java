package com.nuc.menu.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ErrorReporter {
    public static final String ERROR_MESSAGE = "<html><font color='red'><b>%s</b></font></html>";

    private JComponent lastInvalidComponent = null;
    private Color lastWrongComponentBackground = null;
    private final JLabel errorLabel;

    public ErrorReporter(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }

    public void setError(String errorMessage, JComponent componentToBlame) {
        errorLabel.setText(String.format(ERROR_MESSAGE, errorMessage));
        this.markInvalidComponent(componentToBlame);
        this.markComponentValidOnFocus(componentToBlame);
    }

    private void markComponentValidOnFocus(JComponent component) {
        if (component instanceof JComboBox) {
            final JComboBox<?> baseComboBox = (JComboBox<?>) component;
            if (baseComboBox.isEditable()) {
                markComponentValidOnFocus(component, (JComponent) baseComboBox.getEditor()
                                                                              .getEditorComponent());
            } else {
                markComponentValidOnFocus(component, baseComboBox);
            }

        } else {
            markComponentValidOnFocus(component, component);
        }
    }

    private void markComponentValidOnFocus(JComponent component, final JComponent exactComponent) {
        exactComponent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                markComponentAsValid(component);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }

    private void markComponentAsValid(JComponent componentWithFocus) {
        if (lastInvalidComponent != componentWithFocus) {
            return;
        }
        if (lastInvalidComponent != null) {
            lastInvalidComponent.setBackground(lastWrongComponentBackground);
        }

        lastInvalidComponent = null;
        lastWrongComponentBackground = null;
    }

    private void markInvalidComponent(JComponent newComponent) {
        if (lastInvalidComponent != null) {
            lastInvalidComponent.setBackground(lastWrongComponentBackground);
        }

        lastInvalidComponent = newComponent;
        lastWrongComponentBackground = lastInvalidComponent.getBackground();
        lastInvalidComponent.setBackground(Color.RED);
    }
}
