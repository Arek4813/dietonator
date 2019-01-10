package utils.converter;

import database.model.Plan;
import modelfx.PlanFx;

public class PlanConverter {
    private static PlanConverter INSTANCE;

    private PlanConverter() {}

    public static PlanConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PlanConverter();
        return INSTANCE;
    }

    public Plan convertToPlan(PlanFx planFx) {
        Plan plan = new Plan();
        plan.setName(planFx.getName());
        plan.setFat(planFx.getFat());
        plan.setEnergy(planFx.getEnergy());
        plan.setSaturates(planFx.getSaturates());
        plan.setCarbohydrates(planFx.getCarbohydrates());
        plan.setSugars(planFx.getSugars());
        plan.setProtein(planFx.getProtein());
        plan.setSalt(planFx.getSalt());
        return plan;
    }

    public PlanFx convertToPlanFx(Plan plan) {
        PlanFx planFx = new PlanFx();
        planFx.setName(plan.getName());
        planFx.setEnergy(plan.getEnergy());
        planFx.setFat(plan.getFat());
        planFx.setSaturates(plan.getSaturates());
        planFx.setCarbohydrates(plan.getCarbohydrates());
        planFx.setSugars(plan.getSugars());
        planFx.setProtein(plan.getProtein());
        planFx.setSalt(plan.getSalt());
        return planFx;
    }
}
