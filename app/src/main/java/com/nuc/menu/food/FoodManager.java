package com.nuc.menu.food;

import java.util.Set;

public class FoodManager {
    private final FoodStorage foodStorage;
    private final Set<FoodItem> foodItems;

    public FoodManager(FoodStorage foodStorage) {
        this.foodStorage = foodStorage;
        this.foodItems = foodStorage.getFoodItems();
    }

    public Set<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
        foodStorage.saveFoodItems(foodItems);
    }

    public void removeFoodItem(FoodItem foodItem) {
        foodItems.remove(foodItem);
        foodStorage.saveFoodItems(foodItems);
    }

    public boolean nameAlreadyUsed(String text) {
        return foodItems.stream().anyMatch(foodItem -> foodItem.getName().equals(text));
    }
}
