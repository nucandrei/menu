package com.nuc.menu.plan;

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
import java.util.List;
import java.util.stream.Collectors;

public class XMLPlanStorage implements PlanStorage {

    private static final Logger LOGGER = LogManager.getLogger(XMLFoodStorage.class);
    private static final String DAILY_PLAN_DAY = "day";
    private static final String MEAL_NAME = "name";
    private static final String PORTION = "portionPer100";
    private static final String CALORIES = "caloriesPer100";
    private static final String PROTEINS = "proteinsPer100";
    private static final String LIPIDS = "lipidsPer100";
    private static final String FATS = "fatsPer100";
    private static final String FOOD_ITEM_NAME = "name";

    private final String filePath;

    public XMLPlanStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<DailyPlan> getPlans() {
        if (!new File(filePath).exists()) {
            savePlans(Collections.emptyList());
            return Collections.emptyList();
        }

        try {
            final SAXBuilder saxBuilder = new SAXBuilder();
            final Document document = saxBuilder.build(new File(filePath));
            final Element rootElement = document.getRootElement();

            return rootElement.getChildren().stream().map(this::getPlanFromElement).collect(Collectors.toList());

        } catch (Exception e) {
            LOGGER.error("Failed to get plans", e);
            return Collections.emptyList();
        }
    }

    @Override
    public void savePlans(List<DailyPlan> plans) {
        final Document document = new Document(new Element("plans"));
        plans.stream().map(this::getElementFromDailyPlan).forEach(element -> document.getRootElement().addContent(element));

        final XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());

        try {
            xmlOutputter.output(document, new FileWriter(filePath));

        } catch (IOException e) {
            LOGGER.error("Failed to write food items", e);
        }
    }

    private Element getElementFromDailyPlan(DailyPlan dailyPlan) {
        final Element element = new Element("dailyPlan");
        element.setAttribute(DAILY_PLAN_DAY, dailyPlan.getDay());
        dailyPlan.getMealPlans().forEach(mealPlan -> element.addContent(getElementFromMeal(mealPlan)));

        return element;
    }

    private Element getElementFromMeal(MealPlan mealPlan) {
        final Element element = new Element("meal");
        element.setAttribute(MEAL_NAME, mealPlan.getMealName());
        mealPlan.getFoodPlans().forEach(foodPlan -> element.addContent(getElementFromFood(foodPlan)));

        return element;
    }

    private Element getElementFromFood(FoodPlan foodPlan) {
        final Element element = new Element("foodPlan");

        element.setAttribute(FOOD_ITEM_NAME, foodPlan.getName());
        element.setAttribute(PORTION, String.valueOf(foodPlan.getPortion()));
        element.setAttribute(CALORIES, String.valueOf(foodPlan.getCalories()));
        element.setAttribute(PROTEINS, String.valueOf(foodPlan.getProteins()));
        element.setAttribute(LIPIDS, String.valueOf(foodPlan.getLipids()));
        element.setAttribute(FATS, String.valueOf(foodPlan.getFats()));

        return element;
    }

    private DailyPlan getPlanFromElement(Element element) {
        final DailyPlan dailyPlan = new DailyPlan(element.getAttributeValue(DAILY_PLAN_DAY));

        for (Element mealElement : element.getChildren()) {
            dailyPlan.addMealPlan(getMealPlanFromElement(mealElement));
        }

        return dailyPlan;
    }

    private MealPlan getMealPlanFromElement(Element mealElement) {
        final MealPlan mealPlan = new MealPlan(mealElement.getAttributeValue(MEAL_NAME));

        for (Element foodElement : mealElement.getChildren()) {
            mealPlan.addFoodPlan(getFoodPlanFromElement(foodElement));
        }
        return mealPlan;
    }

    private FoodPlan getFoodPlanFromElement(Element foodElement) {
        return new FoodPlan(new FoodItem(foodElement.getAttributeValue(FOOD_ITEM_NAME), foodElement.getAttributeValue(CALORIES), foodElement.getAttributeValue(PROTEINS), foodElement.getAttributeValue(LIPIDS), foodElement.getAttributeValue(FATS)), Integer.parseInt(foodElement.getAttributeValue(PORTION)));
    }
}
