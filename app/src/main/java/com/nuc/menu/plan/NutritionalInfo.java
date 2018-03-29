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
        addListener(listener, false);
    }

    public void addListener(NutritionalInfoListener listener, boolean rebuildModel) {
        this.listeners.add(listener);
        listener.notifyChange(rebuildModel);
    }

    public void removeListener(NutritionalInfoListener listener) {
        removeListener(listener, false);
    }

    private void removeListener(NutritionalInfoListener listener, boolean rebuildModel) {
        this.listeners.remove(listener);
        listener.notifyChange(rebuildModel);
    }

    public void notifyAllListeners(boolean rebuildModel) {
        this.listeners.forEach(listener -> listener.notifyChange(rebuildModel));
    }
}
