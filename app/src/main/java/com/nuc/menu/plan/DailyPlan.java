package com.nuc.menu.plan;

import java.util.ArrayList;
import java.util.List;

public class DailyPlan extends NutritionalInfo implements NutritionalInfoListener {

    private final String day;
    private final List<MealPlan> mealPlans = new ArrayList<>();

    public static DailyPlan addNewDailyPlan(String day) {
        final DailyPlan dailyPlan = new DailyPlan(day);

        dailyPlan.addMealPlan(new MealPlan("Mic dejun"));
        dailyPlan.addMealPlan(new MealPlan("Gustare dimineata"));
        dailyPlan.addMealPlan(new MealPlan("Pranz"));
        dailyPlan.addMealPlan(new MealPlan("Gustare dupamasa"));
        dailyPlan.addMealPlan(new MealPlan("Cina"));

        return dailyPlan;
    }

    public DailyPlan(String day) {
        this.day = day;
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

    public void addMealPlan(MealPlan mealPlan) {
        this.mealPlans.add(mealPlan);
        mealPlan.addListener(this);
    }
}
