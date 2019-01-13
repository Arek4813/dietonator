package modelfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DietUserFx {
    private StringProperty user;
    private StringProperty plan;
    private StringProperty dietician;

    public DietUserFx() {
        user = new SimpleStringProperty();
        plan = new SimpleStringProperty();
        dietician = new SimpleStringProperty();
    }

    public String getUser() {
        return user.get();
    }

    public StringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
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

    public String getDietician() {
        return dietician.get();
    }

    public StringProperty dieticianProperty() {
        return dietician;
    }

    public void setDietician(String dietician) {
        this.dietician.set(dietician);
    }
}
