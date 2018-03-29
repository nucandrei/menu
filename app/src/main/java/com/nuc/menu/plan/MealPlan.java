package com.nuc.menu.plan;

import java.util.ArrayList;
import java.util.List;

public class MealPlan extends NutritionalInfo implements NutritionalInfoListener {
    private final String mealName;
    private final List<FoodPlan> foodPlans = new ArrayList<>();

    public MealPlan(String mealName) {
        this.mealName = mealName;
    }

    public void addFoodPlan(FoodPlan foodPlan) {
        this.foodPlans.add(foodPlan);
        foodPlan.addListener(this, true);
    }

    public void removeFoodPlan(FoodPlan foodPlan) {
        this.foodPlans.remove(foodPlan);
        foodPlan.removeListener(this);
    }

    @Override
    public void notifyChange(boolean rebuildModel) {
        setCalories(foodPlans.stream().mapToInt(NutritionalInfo::getCalories).sum());
        setProteins(foodPlans.stream().mapToDouble(NutritionalInfo::getProteins).sum());
        setLipids(foodPlans.stream().mapToDouble(NutritionalInfo::getLipids).sum());
        setFats(foodPlans.stream().mapToDouble(NutritionalInfo::getFats).sum());

        notifyAllListeners(rebuildModel);
    }

    public String getMealName() {
        return mealName;
    }

    public List<FoodPlan> getFoodPlans() {
        return foodPlans;
    }


}
