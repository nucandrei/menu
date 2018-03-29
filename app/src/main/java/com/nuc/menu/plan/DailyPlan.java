package com.nuc.menu.plan;

import java.util.ArrayList;
import java.util.List;

public class DailyPlan extends NutritionalInfo implements NutritionalInfoListener{

    private final String day;
    private final List<MealPlan> mealPlans = new ArrayList<>();

    DailyPlan(String day) {
        this.day = day;
    }

    void addMealPlan(MealPlan mealPlan) {
        this.mealPlans.add(mealPlan);
        mealPlan.addListener(this);
    }

    @Override
    public void notifyChange() {
        setCalories(mealPlans.stream().mapToInt(NutritionalInfo::getCalories).sum());
        setProteins(mealPlans.stream().mapToDouble(NutritionalInfo::getProteins).sum());
        setLipids(mealPlans.stream().mapToDouble(NutritionalInfo::getLipids).sum());
        setFats(mealPlans.stream().mapToDouble(NutritionalInfo::getFats).sum());

        notifyAllListeners();
    }

    String getDay() {
        return day;
    }
}
