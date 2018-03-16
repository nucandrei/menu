package com.nuc.menu.ui.table;

import javax.swing.*;

public class PermanentPrettyTableRow extends PrettyTableRow {

    public PermanentPrettyTableRow(boolean useSeparator, JComponent... components) {
        super(null, false, useSeparator, components);
    }

    @Override
    public void onRemove() {
        // do nothing
    }
}
