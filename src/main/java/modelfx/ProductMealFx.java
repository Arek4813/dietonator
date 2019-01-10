package modelfx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductMealFx {
    private StringProperty product;
    private StringProperty meal;
    private IntegerProperty amount;

    public ProductMealFx() {
        product = new SimpleStringProperty();
        meal = new SimpleStringProperty();
        amount = new SimpleIntegerProperty();
    }

    public String getProduct() {
        return product.get();
    }

    public StringProperty productProperty() {
        return product;
    }

    public void setProduct(String product) {
        this.product.set(product);
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

    public Integer getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }
}
