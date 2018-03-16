package com.nuc.menu.food;

import java.util.Set;

public interface FoodStorage {
    Set<FoodItem> getFoodItems();

    void saveFoodItems(Set<FoodItem> foodItems);
}
