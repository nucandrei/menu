package com.nuc.menu.child;

public class Child implements Comparable<Child> {
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
