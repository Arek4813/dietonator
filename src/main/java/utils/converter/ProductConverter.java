package utils.converter;

import database.model.Category;
import database.model.Product;
import modelfx.CategoryFx;
import modelfx.ProductFx;

public class ProductConverter {
    private static ProductConverter INSTANCE;

    private ProductConverter() {}

    public static ProductConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ProductConverter();
        return INSTANCE;
    }

    public Product convertToProduct(ProductFx productFx) {
        Product product = new Product();
        Category category = new Category();
        category.setName(productFx.getCategory().getName());
        product.setCategory(category);
        product.setName(productFx.getName());
        product.setEnergy(productFx.getEnergy());
        product.setFat(productFx.getFat());
        product.setSaturates(productFx.getSaturates());
        product.setCarbohydrates(productFx.getCarbohydrates());
        product.setSugars(productFx.getSugars());
        product.setProtein(productFx.getProtein());
        product.setSalt(productFx.getSalt());
        return product;
    }

    public ProductFx convertToProductFx(Product product) {
        ProductFx productFx = new ProductFx();
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setName(product.getCategory().getName());
        productFx.setCategory(categoryFx);
        productFx.setName(product.getName());
        productFx.setEnergy(product.getEnergy());
        productFx.setFat(product.getFat());
        productFx.setSaturates(product.getSaturates());
        productFx.setCarbohydrates(product.getCarbohydrates());
        productFx.setSugars(product.getSugars());
        productFx.setProtein(product.getProtein());
        productFx.setSalt(product.getSalt());
        return productFx;
    }
}
