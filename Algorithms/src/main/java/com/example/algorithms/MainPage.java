package com.example.algorithms;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainPage {

    @FXML
    private Button AbiutUsMain;

    @FXML
    private Button SearchMain;

    @FXML
    private TextField SearchingNameMain;

    @FXML
    private Button SignOutMain;

    @FXML
    private Button SortMain;

    @FXML
    private TableView<ResidentialComplex> TableViewMain;

    @FXML
    private ComboBox<String> comboBoxMain;

    private ObservableList<ResidentialComplex> houses;

    private ArrayList<ResidentialComplex> residentialComplexes;

    @FXML
    private Button findMainPage;

    @FXML
    void initialize() {
        Methods methods = new Methods();
        SignOutMain.setOnAction(event -> {
            methods.openPage(SignOutMain, "hello-view.fxml");
        });

        residentialComplexes = new ArrayList<>();
        residentialComplexes.add(new ResidentialComplex("Assyl Arman", 90000, 120 , "Available"));
        residentialComplexes.add(new ResidentialComplex("Batyr", 85000, 90 ,"Non-Available"));
        residentialComplexes.add(new ResidentialComplex("Norweg", 71000, 60 , "Available "));
        residentialComplexes.add(new ResidentialComplex("Gulder", 121000, 115 , "Non-Available"));
        residentialComplexes.add(new ResidentialComplex("Samal", 50000, 40 ,"Available "));


        TableViewMain.getColumns().clear();
        TableViewMain.getItems().clear();

        TableColumn<ResidentialComplex, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<ResidentialComplex, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        TableColumn<ResidentialComplex, Double> areaColumn = new TableColumn<>("Area");
        areaColumn.setCellValueFactory(cellData -> cellData.getValue().areaProperty().asObject());

        TableColumn<ResidentialComplex, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());;

        TableViewMain.getColumns().addAll(nameColumn, priceColumn, areaColumn , statusColumn);

        updateTableView();
        SearchMain.setOnAction(event -> searchComplexByName());
        comboBoxMain.getItems().addAll("Area", "Price");
        comboBoxMain.setValue("Area");

        SortMain.setOnAction(event -> {

            String selectedSortingCriteria = comboBoxMain.getValue();
            ArrayList<ResidentialComplex> sortedList = new ArrayList<>(residentialComplexes);
            if ("Area".equals(selectedSortingCriteria)) {
                bubbleSort(sortedList, Comparator.comparing(ResidentialComplex::getArea));
            } else if ("Price".equals(selectedSortingCriteria)) {
                bubbleSort(sortedList, Comparator.comparing(ResidentialComplex::getPrice));
            }


            updateTableView(sortedList);
        });
        SearchMain.setOnAction(event -> searchComplexByName());
        findMainPage.setOnAction(event -> findAvailableHouses());

    }

    private void searchComplexByName() {
        String nameToSearch = SearchingNameMain.getText().trim().toLowerCase();
        ObservableList<ResidentialComplex> filteredList = filterData(nameToSearch);
        TableViewMain.setItems(filteredList);
    }

    private int binarySearch(ArrayList<ResidentialComplex> list, String targetName) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = list.get(mid).getName().compareTo(targetName);

            if (result == 0) {
                return mid;
            } else if (result < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private void findAvailableHouses() {
        ArrayList<ResidentialComplex> availableHouses = new ArrayList<>();
        for (ResidentialComplex complex : residentialComplexes) {
            if ("Available".equalsIgnoreCase(complex.getStatus().trim())) {
                availableHouses.add(complex);
            }
        }

        updateTableView(availableHouses);
    }

    private void bubbleSort(ArrayList<ResidentialComplex> list, Comparator<ResidentialComplex> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }



    private ObservableList<ResidentialComplex> filterData(String query) {
        ObservableList<ResidentialComplex> filteredList = FXCollections.observableArrayList();

        if (isListSortedByName()) {
            int index = binarySearch(residentialComplexes, query);
            if (index != -1) {
                filteredList.add(residentialComplexes.get(index));
            }
        } else {
            for (ResidentialComplex complex : residentialComplexes) {
                if (complex.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(complex);
                }
            }
        }

        return filteredList;
    }

    private boolean isListSortedByName() {
        for (int i = 1; i < residentialComplexes.size(); i++) {
            if (residentialComplexes.get(i - 1).getName().compareTo(residentialComplexes.get(i).getName()) > 0) {
                return false;
            }
        }
        return true;
    }

    private void updateTableView(ArrayList<ResidentialComplex> list) {
        houses = FXCollections.observableArrayList(list);
        TableViewMain.setItems(houses);
    }

    private void updateTableView() {
        updateTableView(residentialComplexes);
    }

    private static class ResidentialComplex {
        private final StringProperty name;
        private final DoubleProperty price;
        private final DoubleProperty area;
        private final StringProperty status;

        public ResidentialComplex(String name, double price, double area , String status) {
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleDoubleProperty(price);
            this.area = new SimpleDoubleProperty(area);
            this.status = new SimpleStringProperty(status);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public double getPrice() {
            return price.get();
        }

        public DoubleProperty priceProperty() {
            return price;
        }

        public double getArea() {
            return area.get();
        }

        public String getStatus() {
            return status.get();
        }


        public DoubleProperty areaProperty() {
            return area;
        }
        public StringProperty statusProperty(){
            return status;
        }
    }
}
