package com.nuc.menu.app;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static boolean promptUser(Frame parentFrame, String message) {
        return JOptionPane.showOptionDialog(parentFrame, message, "Confirmare", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Da", "Nu"}, "Da") == 0;
    }

}
