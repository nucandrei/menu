package com.nuc.menu.plan;

import java.util.ArrayList;
import java.util.List;

public class DailyPlan extends NutritionalInfo implements NutritionalInfoListener{

    private final String day;
    private final List<MealPlan> mealPlans = new ArrayList<>();

    public DailyPlan(String day) {
        this.day = day;

        addMealPlan(new MealPlan("Mic dejun"));
        addMealPlan(new MealPlan("Gustare dimineata"));
        addMealPlan(new MealPlan("Pranz"));
        addMealPlan(new MealPlan("Gustare dupamasa"));
        addMealPlan(new MealPlan("Cina"));
    }

    @Override
    public void notifyChange(boolean rebuildModel) {
        setCalories(mealPlans.stream().mapToInt(NutritionalInfo::getCalories).sum());
        setProteins(mealPlans.stream().mapToDouble(NutritionalInfo::getProteins).sum());
        setLipids(mealPlans.stream().mapToDouble(NutritionalInfo::getLipids).sum());
        setFats(mealPlans.stream().mapToDouble(NutritionalInfo::getFats).sum());

        notifyAllListeners(rebuildModel);
    }

    String getDay() {
        return day;
    }

    List<MealPlan> getMealPlans() {
        return mealPlans;
    }

    private void addMealPlan(MealPlan mealPlan) {
        this.mealPlans.add(mealPlan);
        mealPlan.addListener(this);
    }
}
