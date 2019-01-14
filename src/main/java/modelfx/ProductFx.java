package modelfx;

import javafx.beans.property.*;

public class ProductFx {
    private StringProperty name;
    private ObjectProperty<CategoryFx> category;
    private FloatProperty energy;
    private FloatProperty fat;
    private FloatProperty saturates;
    private FloatProperty carbohydrates;
    private FloatProperty sugars;
    private FloatProperty protein;
    private FloatProperty salt;

    public ProductFx() {
        name = new SimpleStringProperty();
        category = new SimpleObjectProperty<>();
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

    public void setEnergy(Float energy) {
        this.energy.set(energy);
    }

    public Float getFat() {
        return fat.get();
    }

    public FloatProperty fatProperty() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat.set(fat);
    }

    public Float getSaturates() {
        return saturates.get();
    }

    public FloatProperty saturatesProperty() {
        return saturates;
    }

    public void setSaturates(Float saturates) {
        this.saturates.set(saturates);
    }

    public Float getCarbohydrates() {
        return carbohydrates.get();
    }

    public FloatProperty carbohydratesProperty() {
        return carbohydrates;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates.set(carbohydrates);
    }

    public Float getSugars() {
        return sugars.get();
    }

    public FloatProperty sugarsProperty() {
        return sugars;
    }

    public void setSugars(Float sugars) {
        this.sugars.set(sugars);
    }

    public Float getProtein() {
        return protein.get();
    }

    public FloatProperty proteinProperty() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein.set(protein);
    }

    public Float getSalt() {
        return salt.get();
    }

    public FloatProperty saltProperty() {
        return salt;
    }

    public void setSalt(Float salt) {
        this.salt.set(salt);
    }

    public CategoryFx getCategory() {
        return category.get();
    }

    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }

    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }
}
