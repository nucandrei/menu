package com.nuc.menu.child;

import com.nuc.menu.food.FoodItem;
import com.nuc.menu.food.XMLFoodStorage;
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
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class XMLChildStorage implements ChildStorage {
    private static final Logger LOGGER = LogManager.getLogger(XMLFoodStorage.class);
    private static final String NAME = "name";
    private static final String CALORIES = "calories";
    public static final String AGE = "age";
    public static final String GENDER = "gender";

    private String filePath;

    public XMLChildStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Set<Child> getChildren() {
        if (!new File(filePath).exists()) {
            saveChildren(Collections.emptySet());
            return Collections.emptySet();
        }

        try {
            final SAXBuilder saxBuilder = new SAXBuilder();
            final Document document = saxBuilder.build(new File(filePath));
            final Element rootElement = document.getRootElement();

            return rootElement.getChildren().stream().map(this::getChildFromElement).collect(Collectors.toCollection(TreeSet::new));

        } catch (Exception e) {
            LOGGER.error("Failed to get children", e);
            return Collections.emptySet();
        }
    }

    @Override
    public void saveChildren(Set<Child> children) {
        final Document document = new Document(new Element("children"));
        children.stream().map(this::getElementFromChild).forEach(element -> document.getRootElement().addContent(element));

        final XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());

        try {
            xmlOutputter.output(document, new FileWriter(filePath));

        } catch (IOException e) {
            LOGGER.error("Failed to write children", e);
        }
    }

    private Child getChildFromElement(Element element) {
        final String name = element.getAttribute(NAME).getValue();
        final String age = element.getAttribute(AGE).getValue();
        final String gender = element.getAttribute(GENDER).getValue();
        final String caloriesRequirement = element.getAttribute(CALORIES).getValue();
        return new Child(name, age, gender, caloriesRequirement);
    }

    private Element getElementFromChild(Child foodItem) {
        final Element element = new Element("child");
        element.setAttribute(NAME, foodItem.getName());
        element.setAttribute(AGE, foodItem.getAge());
        element.setAttribute(GENDER, foodItem.getGender());
        element.setAttribute(CALORIES, foodItem.getCaloriesRequirement());
        return element;
    }
}
