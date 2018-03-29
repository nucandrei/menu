package com.nuc.menu.plan;

import java.util.ArrayList;
import java.util.List;

public class NutritionalInfo {

    private final List<NutritionalInfoListener> listeners = new ArrayList<>();

    private int calories;
    private double proteins;
    private double lipids;
    private double fats;

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getLipids() {
        return lipids;
    }

    public void setLipids(double lipids) {
        this.lipids = lipids;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public void addListener(NutritionalInfoListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(NutritionalInfoListener listener) {
        this.listeners.remove(listener);
    }

    public void notifyAllListeners(boolean recomputeModel) {
        this.listeners.forEach(listener -> listener.notifyChange(recomputeModel));
    }
}
