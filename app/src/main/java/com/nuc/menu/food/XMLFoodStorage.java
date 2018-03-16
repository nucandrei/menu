package com.nuc.menu.food;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class XMLFoodStorage implements FoodStorage {
    private static final Logger LOGGER = LogManager.getLogger(XMLFoodStorage.class);
    private static final String NAME = "name";
    private static final String CALORIES = "calories";
    private static final String PROTEIN = "protein";
    private static final String LIPIDS = "lipids";
    private static final String FATS = "fats";

    private String filePath;

    public XMLFoodStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Set<FoodItem> getFoodItems() {
        if (!new File(filePath).exists()) {
            saveFoodItems(Collections.emptySet());
            return Collections.emptySet();
        }

        try {
            final SAXBuilder saxBuilder = new SAXBuilder();
            final Document document = saxBuilder.build(new File(filePath));
            final Element rootElement = document.getRootElement();

            return rootElement.getChildren().stream().map(this::getFoodItemFromElement).collect(Collectors.toCollection(TreeSet::new));

        } catch (Exception e) {
            LOGGER.error("Failed to get food items", e);
            return Collections.emptySet();
        }
    }

    @Override
    public void saveFoodItems(Set<FoodItem> foodItems) {
        final Document document = new Document(new Element("foods"));
        foodItems.stream().map(this::getElementFromFoodItem).forEach(element -> document.getRootElement().addContent(element));

        final XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());

        try {
            xmlOutputter.output(document, new FileWriter(filePath));

        } catch (IOException e) {
            LOGGER.error("Failed to write food items", e);
        }
    }

    private FoodItem getFoodItemFromElement(Element element) {
        final String name = element.getAttribute(NAME).getValue();
        final String calories = element.getAttribute(CALORIES).getValue();
        final String protein = element.getAttribute(PROTEIN).getValue();
        final String lipids = element.getAttribute(LIPIDS).getValue();
        final String fats = element.getAttribute(FATS).getValue();
        return new FoodItem(name, calories, protein, lipids, fats);
    }

    private Element getElementFromFoodItem(FoodItem foodItem) {
        final Element element = new Element("food");
        element.setAttribute(NAME, foodItem.getName());
        element.setAttribute(CALORIES, foodItem.getCalories());
        element.setAttribute(PROTEIN, foodItem.getProtein());
        element.setAttribute(LIPIDS, foodItem.getLipids());
        element.setAttribute(FATS, foodItem.getFats());
        return element;
    }
}
