package controller.meal;

import database.dao.ProductMealDao;
import database.model.ProductMeal;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelfx.ProductMealFx;
import utils.dialog.DialogUtil;

import java.sql.SQLException;

public class ProductMealEditingController {

    @FXML
    private TextField productTextField;

    @FXML
    private TextField mealTextField;

    @FXML
    private TextField amountTextField;

    ProductMealDao productMealDao;

    @FXML
    public void initialize() {
        productMealDao = new ProductMealDao();
    }

    @FXML
    public void updateProductMeal() {
        try {
            String product = productTextField.getText();
            String meal = mealTextField.getText();
            Integer amount = Integer.parseInt(amountTextField.getText());
            ProductMeal productMeal = new ProductMeal();
            productMeal.setProduct(product);
            productMeal.setMeal(meal);
            productMeal.setAmount(amount);
            productMealDao.updateProductInMeal(productMeal);
            closeWindow();
        }
        catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void initializeTextFields(ProductMealFx productMealFx) {
        productTextField.setText(productMealFx.getProduct());
        mealTextField.setText(productMealFx.getMeal());
        amountTextField.setText(String.valueOf(productMealFx.getAmount()));
        productTextField.setEditable(false);
        mealTextField.setEditable(false);
    }

    private void closeWindow() {
        Stage stage = (Stage) this.productTextField.getScene().getWindow();
        stage.close();
    }
}
