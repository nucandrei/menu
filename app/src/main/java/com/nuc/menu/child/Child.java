package com.nuc.menu.child;

import com.nuc.menu.app.WithProperties;

import java.util.Arrays;
import java.util.List;

public class Child implements Comparable<Child>, WithProperties {

    private final String name;
    private final String age;
    private final String gender;
    private final String caloriesRequirement;

    public Child(String name, String age, String gender, String caloriesRequirement) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.caloriesRequirement = caloriesRequirement;
    }

    @Override
    public List<String> getProperties() {
        return Arrays.asList(name, age, gender, caloriesRequirement);
    }

    @Override
    public String getDescription() {
        return "copilul";
    }

    public String getName() {
        return name;
    }



    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getCaloriesRequirement() {
        return caloriesRequirement;
    }

    @Override
    public int compareTo(Child child) {
        return name.compareTo(child.getName());
    }
}
