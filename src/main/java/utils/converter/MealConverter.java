package utils.converter;

import database.model.Meal;
import modelfx.MealFx;

public class MealConverter {
    private static MealConverter INSTANCE;

    private MealConverter() {}

    public static MealConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MealConverter();
        return INSTANCE;
    }

    public Meal convertToMeal(MealFx mealFx) {
        Meal meal = new Meal();
        meal.setName(mealFx.getName());
        meal.setFat(mealFx.getFat());
        meal.setEnergy(mealFx.getEnergy());
        meal.setSaturates(mealFx.getSaturates());
        meal.setCarbohydrates(mealFx.getCarbohydrates());
        meal.setSugars(mealFx.getSugars());
        meal.setProtein(mealFx.getProtein());
        meal.setSalt(mealFx.getSalt());
        return meal;
    }

    public MealFx convertToMealFx(Meal meal) {
        MealFx mealFx = new MealFx();
        mealFx.setName(meal.getName());
        mealFx.setEnergy(meal.getEnergy());
        mealFx.setFat(meal.getFat());
        mealFx.setSaturates(meal.getSaturates());
        mealFx.setCarbohydrates(meal.getCarbohydrates());
        mealFx.setSugars(meal.getSugars());
        mealFx.setProtein(meal.getProtein());
        mealFx.setSalt(meal.getSalt());
        return mealFx;
    }
}
