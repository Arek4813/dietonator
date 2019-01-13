package database.model;

public class DietUser {
    private String user;
    private String plan;
    private String dietician;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getDietician() {
        return dietician;
    }

    public void setDietician(String dietician) {
        this.dietician = dietician;
    }
}
