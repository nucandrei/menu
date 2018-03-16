package com.nuc.menu.app;

import com.nuc.menu.child.Child;
import com.nuc.menu.child.ChildManager;
import com.nuc.menu.image.ImageManager;
import com.nuc.menu.ui.table.PermanentPrettyTableRow;
import com.nuc.menu.ui.table.PrettyTable;
import com.nuc.menu.ui.table.PrettyTableRow;

import javax.swing.*;
import java.awt.*;

public class ChildManagementPanel extends JPanel {

    private final JFrame parentFrame;

    public ChildManagementPanel(JFrame parentFrame, ChildManager childManager) {
        this.parentFrame = parentFrame;

        this.setLayout(new BorderLayout());

        final PrettyTable prettyTable = new PrettyTable(5, 5, true, true);
        populateWithChildren(prettyTable, childManager);

        this.add(new JScrollPane(prettyTable), BorderLayout.CENTER);
        this.add(new AddChildPanel(childManager, () -> populateWithChildren(prettyTable, childManager)), BorderLayout.SOUTH);
    }

    private void populateWithChildren(PrettyTable prettyTable, ChildManager childManager) {
        prettyTable.removeAll();
        prettyTable.addRow(new PermanentPrettyTableRow(true, new JLabel("Nume"), new JLabel("Varsta"), new JLabel("Sex"), new JLabel("Necesar de calorii"), new JLabel("")));

        childManager.getChildren().forEach(child -> prettyTable.addRow(generateRow(childManager, child)));
    }

    private PrettyTableRow generateRow(ChildManager childManager, Child child) {
        final JButton editBtn = new JButton(ImageManager.get(ImageManager.RESET_ROW_IMAGE));

        return new PrettyTableRow(ImageManager.get(ImageManager.REMOVE_ROW_IMAGE),
                true,
                true,
                new JLabel(child.getName()),
                new JLabel(child.getAge()),
                new JLabel(child.getGender()),
                new JLabel(child.getCaloriesRequirement()),
                editBtn) {
            @Override
            public void onRemove() {
                childManager.removeChild(child);
            }

            @Override
            public boolean confirmRemove() {
                return Utils.promptUser(parentFrame, String.format("Sunteti sigur ca vreti sa stergeti copilul \"%s\"?", child.getName()));
            }
        };
    }
}
