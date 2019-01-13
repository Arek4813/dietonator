package controller.plan;

import database.dao.PlanDao;
import database.model.Plan;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelfx.PlanFx;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PlanViewController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView planTableView;

    @FXML
    private TableColumn<PlanFx, String> nameTableColumn;

    @FXML
    private TableColumn<PlanFx, Float> energyTableColumn;

    @FXML
    private TableColumn<PlanFx, Float> fatTableColumn;

    @FXML
    private TableColumn<PlanFx, Float> saturatesTableColumn;

    @FXML
    private TableColumn<PlanFx, Float> carbohydratesTableColumn;

    @FXML
    private TableColumn<PlanFx, Float> sugarsTableColumn;

    @FXML
    private TableColumn<PlanFx, Float> proteinTableColumn;

    @FXML
    private TableColumn<PlanFx, Float> saltTableColumn;

    @FXML
    private TableColumn<PlanFx, PlanFx> editTableColumn;

    @FXML
    private TableColumn<PlanFx, PlanFx> deleteTableColumn;

    @FXML
    private TableColumn<PlanFx, PlanFx> mealsTableColumn;

    @FXML
    private TextField addPlanTextField;

    private PlanDao planDao;

    @FXML
    public void initialize() {
        planDao = new PlanDao();
        initializeTable();
        refreshTable();
    }

    @FXML
    public void addPlan() {
        String name = addPlanTextField.getText();
        if (!name.equals("")) {
            Plan plan = new Plan();
            plan.setName(name);
            try {
                planDao.addPlan(plan);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            refreshTable();
        }
        addPlanTextField.clear();
    }

    private void refreshTable() {
        try {
            planTableView.setItems(planDao.getPlan(""));
        } catch (SQLException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }

    private void initializeTable() {
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        energyTableColumn.setCellValueFactory(cellData -> cellData.getValue().energyProperty().asObject());
        fatTableColumn.setCellValueFactory(cellData -> cellData.getValue().fatProperty().asObject());
        saturatesTableColumn.setCellValueFactory(cellData -> cellData.getValue().saturatesProperty().asObject());
        carbohydratesTableColumn.setCellValueFactory(cellData -> cellData.getValue().carbohydratesProperty().asObject());
        sugarsTableColumn.setCellValueFactory(cellData -> cellData.getValue().sugarsProperty().asObject());
        proteinTableColumn.setCellValueFactory(cellData -> cellData.getValue().proteinProperty().asObject());
        saltTableColumn.setCellValueFactory(cellData-> cellData.getValue().saltProperty().asObject());
        deleteTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteTableColumn.setCellFactory(cellData -> new TableCell<PlanFx, PlanFx>() {
            Button button = new Button("X");
            @Override
            protected void updateItem(PlanFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            planDao.deletePlan(item.getName());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });
        editTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editTableColumn.setCellFactory(cellData -> new TableCell<PlanFx, PlanFx>() {
            Button button = new Button("EDIT");
            @Override
            protected void updateItem(PlanFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/plan/planEditing.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        PlanEditingController controller = loader.getController();
                        controller.initializeField(item);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        refreshTable();
                    });
                }
            }
        });

        mealsTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        mealsTableColumn.setCellFactory(cellData -> new TableCell<PlanFx, PlanFx>() {
            Button button = new Button("SHOW DETAILS");
            @Override
            protected void updateItem(PlanFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/plan/mealsOfPlanView.fxml"));
                        try {
                            borderPane.setCenter(loader.load());
                            MealsOfPlanController controller = loader.getController();
                            controller.showDetails(item);
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });
    }
}
