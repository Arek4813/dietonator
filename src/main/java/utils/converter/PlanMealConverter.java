package utils.converter;

import database.model.PlanMeal;
import modelfx.PlanMealFx;

public class PlanMealConverter {
    private static PlanMealConverter INSTANCE;

    private PlanMealConverter() {}

    public static PlanMealConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlanMealConverter();
        }
        return INSTANCE;
    }

    public PlanMeal convertToPlanMeal(PlanMealFx planMealFx) {
        PlanMeal planMeal = new PlanMeal();
        planMeal.setPlanName(planMealFx.getPlan());
        planMeal.setMealName(planMealFx.getMeal());
        planMeal.setDay(planMealFx.getDay());
        planMeal.setTime(planMealFx.getTime());
        return planMeal;
    }

    public PlanMealFx convertToPlanMealFx(PlanMeal planMeal) {
        PlanMealFx planMealFx = new PlanMealFx();
        planMealFx.setPlan(planMeal.getPlanName());
        planMealFx.setMeal(planMeal.getMealName());
        planMealFx.setDay(planMeal.getDay());
        planMealFx.setTime(planMeal.getTime());
        return planMealFx;
    }
}
