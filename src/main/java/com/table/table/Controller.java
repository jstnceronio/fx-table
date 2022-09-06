package com.table.table;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    public TableView<Coffee> tvItems;
    public TableColumn<Coffee, String> colName;
    public TableColumn<Coffee, Double>  colPrize;
    public TableColumn<Coffee, String> colType;
    public TextField txtSearch;
    public ChoiceBox<String> cbType;

    ObservableList<Coffee> data = FXCollections.observableArrayList(
            new Coffee("Espresso", 2.50, "Drink"),
            new Coffee("Cappuccino", 4.50, "Drink"),
            new Coffee("Affogato", 4.50, "Dessert")
    );

    // Filters
    private String nameFilter = "";
    private String typeFilter = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCoffees();
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            nameFilter = newValue;
            refreshTableView();
        });
        cbType.getItems().addAll(
                "Drink", "Dessert"
        );
        cbType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String newVal = cbType.getItems().get((Integer) t1);
                typeFilter = newVal;
                refreshTableView();
            }
        });
    }

    public void showCoffees() {
        colName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        colPrize.setCellValueFactory(
                new PropertyValueFactory<>("prize")
        );
        colType.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        tvItems.setItems(data);
    }

    public void refreshTableView() {
        tvItems.setItems(
                data.stream()
                        .filter(i -> i.getName().contains(nameFilter))
                        .filter(i -> i.getType().contains(typeFilter))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList))
        );
    }
}