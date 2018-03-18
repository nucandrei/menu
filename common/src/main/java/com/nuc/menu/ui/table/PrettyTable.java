package com.nuc.menu.ui.table;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PrettyTable extends JPanel {
    private JSeparator lastSeparator = null;
    private static final long serialVersionUID = 4533456577945979137L;
    private static final GridBagConstraints FILL_PANEL_CONSTRAINTS = new GridBagConstraints();

    static {
        FILL_PANEL_CONSTRAINTS.insets = new Insets(0, 0, 5, 5);
        FILL_PANEL_CONSTRAINTS.gridx = 0;
        FILL_PANEL_CONSTRAINTS.fill = GridBagConstraints.BOTH;
        FILL_PANEL_CONSTRAINTS.weighty = 1.0;
        FILL_PANEL_CONSTRAINTS.weightx = 1.0;
    }

    private static final GridBagConstraints SEPARATOR_CONSTRAINTS = new GridBagConstraints();

    static {
        SEPARATOR_CONSTRAINTS.weightx = 1.0;
        SEPARATOR_CONSTRAINTS.gridx = 0;
        SEPARATOR_CONSTRAINTS.fill = GridBagConstraints.HORIZONTAL;
        SEPARATOR_CONSTRAINTS.gridwidth = GridBagConstraints.REMAINDER;

    }

    private final int columns;
    private JPanel fillPanel;
    private final boolean useSeparator;
    private final boolean markFocus;

    public PrettyTable(int columns, int weightedColumns, boolean useSeparator, boolean markFocus) {
        this.columns = columns;
        this.useSeparator = useSeparator;
        this.markFocus = markFocus;
        final GridBagLayout layout = new GridBagLayout();
        final double[] weights = new double[columns + 1];
        for (int i = 0; i < weightedColumns; i++) {
            weights[i] = 1;
        }

        for (int i = weightedColumns; i <= columns; i++) {
            weights[i] = 0;
        }

        layout.columnWeights = weights;
        this.setLayout(layout);
    }

    public void addRow(PrettyTableRow row) {
        final JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setBackground(row.getSeparatorColor());

        for (final JComponent component : row.getComponents()) {
            if (markFocus) {
                final JComponent exactComponent;
                if (component instanceof JSpinner) {
                    final JSpinner spinner = (JSpinner) component;
                    final DefaultEditor editor = (DefaultEditor) spinner.getEditor();
                    exactComponent = editor.getTextField();

                } else {
                    exactComponent = component;
                }

                exactComponent.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (lastSeparator != null) {
                            lastSeparator.setBackground(null);
                        }

                        lastSeparator = separator;
                        separator.setBackground(Color.RED);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {

                    }
                });
            }
        }

        for (int i = 0; i < columns; i++) {
            final GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(5, 0, 5, 5);
            constraints.gridx = i;
            constraints.weighty = 0;
            add(row.getComponents()[i], constraints);
        }

        final GridBagConstraints removeButtonConstraints = new GridBagConstraints();
        removeButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
        removeButtonConstraints.insets = new Insets(5, 0, 5, 5);
        removeButtonConstraints.gridx = columns;
        removeButtonConstraints.weighty = 0;
        final JComponent removeSlot;
        if (row.hasRemove()) {
            removeSlot = new JButton("", row.getRemoveIcon());
            ((JButton) removeSlot).addActionListener(e -> {
                if (!row.confirmRemove()) {
                    return;
                }

                for (final JComponent component : row.getComponents()) {
                    this.remove(component);
                }

                if (useSeparator && row.useSeparator()) {
                    this.remove(separator);
                }
                this.remove(removeSlot);

                this.revalidate();
                this.repaint();
                row.onRemove();
            });
        } else {
            removeSlot = new JPanel();
        }

        add(removeSlot, removeButtonConstraints);

        if (useSeparator && row.useSeparator()) {
            add(separator, SEPARATOR_CONSTRAINTS);
        }

        addFillPanel();
        this.revalidate();
    }

    private void addFillPanel() {
        if (fillPanel != null) {
            this.remove(fillPanel);

        } else {
            fillPanel = new JPanel();
        }
        this.add(fillPanel, FILL_PANEL_CONSTRAINTS);
    }

    @Override
    public void removeAll() {
        super.removeAll();
        addFillPanel();
    }
}