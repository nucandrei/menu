package com.nuc.menu.plan;

import java.util.List;

public interface PlanStorage {

    List<DailyPlan> getPlans();

    void savePlans(List<DailyPlan> plans);
}
