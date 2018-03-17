package com.nuc.menu.app;

import com.nuc.menu.image.ImageManager;
import com.nuc.menu.ui.table.PermanentPrettyTableRow;
import com.nuc.menu.ui.table.PrettyTable;
import com.nuc.menu.ui.table.PrettyTableRow;

import javax.swing.*;
import java.awt.*;

public class ManagementPanel <T extends WithProperties> extends JPanel {
    private final JFrame parentFrame;

    public ManagementPanel(JFrame parentFrame, Manager<T> manager) {
        this.parentFrame = parentFrame;

        this.setLayout(new BorderLayout());

        final int fieldsCount = manager.getFieldsCount();
        final PrettyTable prettyTable = new PrettyTable(fieldsCount, fieldsCount, true, true);
        populateWithChildren(prettyTable, manager);

        this.add(new JScrollPane(prettyTable), BorderLayout.CENTER);
        this.add(new AddNewItemPanel<>(manager, ()-> populateWithChildren(prettyTable, manager)), BorderLayout.SOUTH);
    }

    private void populateWithChildren(PrettyTable prettyTable, Manager<T> manager) {
        prettyTable.removeAll();
        prettyTable.addRow(new PermanentPrettyTableRow(true, manager.getFieldNames().stream().map(JLabel::new).toArray(JComponent[]::new)));

        manager.getExistingItems().forEach(item -> prettyTable.addRow(generateRow(manager, item)));
    }

    private PrettyTableRow generateRow(Manager<T> manager, T item) {
        return new PrettyTableRow(ImageManager.get(ImageManager.REMOVE_ROW_IMAGE), true, true, item.getProperties().stream().map(JLabel::new).toArray(JComponent[]::new)) {
            @Override
            public void onRemove() {
                manager.remove(item);
            }

            @Override
            public boolean confirmRemove() {
                return Utils.promptUser(parentFrame, String.format("Sunteti sigur ca vreti sa stergeti %s \"%s\"?", item.getDescription(), item.getName()));
            }
        };
    }
}
