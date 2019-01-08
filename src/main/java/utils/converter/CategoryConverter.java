package utils.converter;

import database.model.Category;
import modelfx.CategoryFx;

public class CategoryConverter {
    private static CategoryConverter INSTANCE;

    private CategoryConverter() {}

    public static CategoryConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CategoryConverter();
        return INSTANCE;
    }

    public Category convertToCategory(CategoryFx categoryFx) {
        Category category = new Category();
        category.setName(categoryFx.getName());
        return category;
    }

    public CategoryFx convertToCategoryFx(Category category) {
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setName(category.getName());
        return categoryFx;
    }
}
