package com.nuc.menu.app;

import com.nuc.menu.ui.table.PrettyTable;

import javax.swing.*;

public class PlanningPanel extends JPanel {
    public PlanningPanel() {
        this.add(new PrettyTable(3, 0, true, true));
    }
}
