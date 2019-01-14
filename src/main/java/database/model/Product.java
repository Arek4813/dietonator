package database.model;

public class Product {
    private String name;
    private Category category;
    private Float energy;
    private Float fat;
    private Float saturates;
    private Float carbohydrates;
    private Float sugars;
    private Float protein;
    private Float salt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public Float getSaturates() {
        return saturates;
    }

    public void setSaturates(Float saturates) {
        this.saturates = saturates;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Float getSugars() {
        return sugars;
    }

    public void setSugars(Float sugars) {
        this.sugars = sugars;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getSalt() {
        return salt;
    }

    public void setSalt(Float salt) {
        this.salt = salt;
    }
}
