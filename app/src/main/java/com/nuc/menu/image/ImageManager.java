package com.nuc.menu.image;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {

    private static final String ROOT_DIR = "/icons/";

    public static final String REMOVE_ROW_IMAGE = "delete-row-24.png";
    public static final String ADD_ROW_IMAGE = "add-row-24.png";
    public static final String RESET_ROW_IMAGE = "reset-row-24.png";

    private static final Map<String, ImageIcon> IMAGE_ICONS_BY_NAME = new HashMap<>();

    public static ImageIcon get(String imageName) {
        return IMAGE_ICONS_BY_NAME.computeIfAbsent(imageName, name -> new ImageIcon(ImageManager.class.getResource(ROOT_DIR + imageName)));
    }
}
