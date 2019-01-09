package modelfx;

import javafx.beans.property.*;

public class ProductFx {
    private StringProperty name;
    private ObjectProperty<CategoryFx> category;
    private IntegerProperty energy;
    private IntegerProperty fat;
    private IntegerProperty saturates;
    private IntegerProperty carbohydrates;
    private IntegerProperty sugars;
    private IntegerProperty protein;
    private IntegerProperty salt;

    public ProductFx() {
        name = new SimpleStringProperty();
        category = new SimpleObjectProperty<>();
        energy = new SimpleIntegerProperty();
        fat = new SimpleIntegerProperty();
        saturates = new SimpleIntegerProperty();
        carbohydrates = new SimpleIntegerProperty();
        sugars = new SimpleIntegerProperty();
        protein = new SimpleIntegerProperty();
        salt = new SimpleIntegerProperty();
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

    public Integer getEnergy() {
        return energy.get();
    }

    public IntegerProperty energyProperty() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy.set(energy);
    }

    public Integer getFat() {
        return fat.get();
    }

    public IntegerProperty fatProperty() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat.set(fat);
    }

    public Integer getSaturates() {
        return saturates.get();
    }

    public IntegerProperty saturatesProperty() {
        return saturates;
    }

    public void setSaturates(int saturates) {
        this.saturates.set(saturates);
    }

    public Integer getCarbohydrates() {
        return carbohydrates.get();
    }

    public IntegerProperty carbohydratesProperty() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates.set(carbohydrates);
    }

    public Integer getSugars() {
        return sugars.get();
    }

    public IntegerProperty sugarsProperty() {
        return sugars;
    }

    public void setSugars(int sugars) {
        this.sugars.set(sugars);
    }

    public Integer getProtein() {
        return protein.get();
    }

    public IntegerProperty proteinProperty() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein.set(protein);
    }

    public Integer getSalt() {
        return salt.get();
    }

    public IntegerProperty saltProperty() {
        return salt;
    }

    public void setSalt(int salt) {
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
