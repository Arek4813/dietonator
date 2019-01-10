package modelfx;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlanFx {
    private StringProperty name;
    private FloatProperty energy;
    private FloatProperty fat;
    private FloatProperty saturates;
    private FloatProperty carbohydrates;
    private FloatProperty sugars;
    private FloatProperty protein;
    private FloatProperty salt;

    public PlanFx() {
        name = new SimpleStringProperty();
        energy = new SimpleFloatProperty();
        fat = new SimpleFloatProperty();
        saturates = new SimpleFloatProperty();
        carbohydrates = new SimpleFloatProperty();
        sugars = new SimpleFloatProperty();
        protein = new SimpleFloatProperty();
        salt = new SimpleFloatProperty();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Float getEnergy() {
        return energy.get();
    }

    public FloatProperty energyProperty() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy.set(energy);
    }

    public Float getFat() {
        return fat.get();
    }

    public FloatProperty fatProperty() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat.set(fat);
    }

    public Float getSaturates() {
        return saturates.get();
    }

    public FloatProperty saturatesProperty() {
        return saturates;
    }

    public void setSaturates(float saturates) {
        this.saturates.set(saturates);
    }

    public Float getCarbohydrates() {
        return carbohydrates.get();
    }

    public FloatProperty carbohydratesProperty() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates.set(carbohydrates);
    }

    public Float getSugars() {
        return sugars.get();
    }

    public FloatProperty sugarsProperty() {
        return sugars;
    }

    public void setSugars(float sugars) {
        this.sugars.set(sugars);
    }

    public Float getProtein() {
        return protein.get();
    }

    public FloatProperty proteinProperty() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein.set(protein);
    }

    public Float getSalt() {
        return salt.get();
    }

    public FloatProperty saltProperty() {
        return salt;
    }

    public void setSalt(float salt) {
        this.salt.set(salt);
    }
}
