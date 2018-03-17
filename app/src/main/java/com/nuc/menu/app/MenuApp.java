package com.nuc.menu.app;

import com.nuc.menu.child.ChildManager;
import com.nuc.menu.child.XMLChildStorage;
import com.nuc.menu.food.FoodManager;
import com.nuc.menu.food.XMLFoodStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;

import javax.swing.*;
import java.awt.*;

public class MenuApp extends JFrame {

    private static final Logger LOGGER = LogManager.getLogger(MenuApp.class);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new SubstanceDustLookAndFeel());
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);

                new MenuApp().setVisible(true);

            } catch (UnsupportedLookAndFeelException e) {
                LOGGER.error("Detected unsupported look and feel", e);
                LOGGER.error("Stopping application");
            }
        });
    }

    private MenuApp() {
        super("Menu");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        final FoodManager foodManager = new FoodManager(new XMLFoodStorage("food.xml"));
        final ChildManager childManager = new ChildManager(new XMLChildStorage("children.xml"));

        final JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Planificare", new JPanel());
        tabbedPane.addTab("Management alimente", new ManagementPanel<>(this, foodManager));
        tabbedPane.addTab("Copii", new ManagementPanel<>(this, childManager));

        this.setLayout(new BorderLayout());
        this.add(tabbedPane, BorderLayout.CENTER);
    }
}
