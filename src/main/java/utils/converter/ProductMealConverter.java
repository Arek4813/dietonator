package utils.converter;

import database.model.ProductMeal;
import modelfx.ProductMealFx;

public class ProductMealConverter {
    private static ProductMealConverter INSTANCE;

    private ProductMealConverter() {}

    public static ProductMealConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ProductMealConverter();
        return INSTANCE;
    }

    public ProductMeal convertToProductMeal(ProductMealFx productMealFx) {
        ProductMeal productMeal = new ProductMeal();
        productMeal.setProduct(productMealFx.getProduct());
        productMeal.setMeal(productMealFx.getMeal());
        productMeal.setAmount(productMealFx.getAmount());
        return productMeal;
    }

    public ProductMealFx convertToProductMealFx(ProductMeal productMeal) {
        ProductMealFx productMealFx = new ProductMealFx();
        productMealFx.setProduct(productMeal.getProduct());
        productMealFx.setMeal(productMeal.getMeal());
        productMealFx.setAmount(productMeal.getAmount());
        return productMealFx;
    }
}
