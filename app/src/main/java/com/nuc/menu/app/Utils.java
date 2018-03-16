package com.nuc.menu.app;

import org.pushingpixels.lafwidget.animation.AnimationConfigurationManager;
import org.pushingpixels.lafwidget.animation.AnimationFacet;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static boolean promptUser(Frame parentFrame, String message) {
        return JOptionPane.showOptionDialog(parentFrame, message, "Confirmare", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Da", "Nu"}, "Da") == 0;
    }

}
