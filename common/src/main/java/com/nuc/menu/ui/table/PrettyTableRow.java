package com.nuc.menu.ui.table;

import javax.swing.*;
import java.awt.*;

public abstract class PrettyTableRow {
    private final JComponent[] components;
    private final ImageIcon removeIcon;
    private final boolean hasRemove;
    private final boolean useSeparator;

    public PrettyTableRow(ImageIcon removeIcon, boolean hasRemove, boolean useSeparator, JComponent... components) {
        this.removeIcon = removeIcon;
        this.hasRemove = hasRemove;
        this.useSeparator = useSeparator;
        this.components = components;
    }

    JComponent[] getComponents() {
        return components;
    }

    ImageIcon getRemoveIcon() {
        return removeIcon;
    }

    public abstract void onRemove();

    public boolean confirmRemove() {
        return true;
    }

    boolean hasRemove() {
        return hasRemove;
    }

    boolean useSeparator() {
        return useSeparator;
    }

    public Color getSeparatorColor() {
        return null;
    }
}
