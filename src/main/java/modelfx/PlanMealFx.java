package modelfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlanMealFx {
    private StringProperty plan;
    private StringProperty meal;
    private StringProperty day;
    private StringProperty time;

    public PlanMealFx() {
        plan = new SimpleStringProperty();
        meal = new SimpleStringProperty();
        day = new SimpleStringProperty();
        time = new SimpleStringProperty();
    }

    public String getPlan() {
        return plan.get();
    }

    public StringProperty planProperty() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan.set(plan);
    }

    public String getMeal() {
        return meal.get();
    }

    public StringProperty mealProperty() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal.set(meal);
    }

    public String getDay() {
        return day.get();
    }

    public StringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }
}
