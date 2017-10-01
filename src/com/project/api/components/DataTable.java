/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.components;

import com.project.api.models.Item;
import com.project.api.translations.I18nUI;
import com.project.api.translations.Translations;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class DataTable extends GridPane {

    private TableView table;
    private List<Item> items;
    private Footer footer;

    public DataTable(List<Item> items) {
        this.items = items;
        table = new TableView();
        footer = new Footer(table);
    }

    private int getRecordsSize() {
        return items.size();
    }

    private class Footer extends GridPane {

        private ObservableList<Integer> noOfRecords = FXCollections.observableArrayList(5, 10, 50, 100, 200, 100);

        private Button firstButton = new Button(I18nUI.getString(Translations.FIRST));
        private Button previousButton = new Button(I18nUI.getString(Translations.PREVIOUS));
        private TextField currentPageField = new TextField();
        private Label totalPagesLabel = new Label();
        private Button nextButton = new Button(I18nUI.getString(Translations.NEXT));
        private Button lastButton = new Button(I18nUI.getString(Translations.LAST));
        private Label entriesLabel = new Label(I18nUI.getString(Translations.SHOWING_OUT_OF_ENTRIES));
        private Label pageSizeLabel = new Label(I18nUI.getString(Translations.PAGE_SIZE));
        private ComboBox pageOptions = new ComboBox(noOfRecords);

        private final int DEFAULT_NO_OF_RECORDS = 50;

        private TableView table;
        private int currentPage = 1;

        public Footer(TableView table) {
            this.table = table;
            init();
        }

        private void init() {
            firstButton.setOnAction((ActionEvent e) -> {
                currentPage = 1;
                setCurrentPage(currentPage);
                loadTableContent();

                loadButtonState(true,
                        true,
                        currentPage == getCurrentTotalPages(),
                        currentPage == getCurrentTotalPages());
            });
            add(firstButton, 0, 0);

            previousButton.setOnAction((ActionEvent e) -> {
                currentPage--;
                setCurrentPage(currentPage);

                loadButtonState(currentPage == 1,
                        currentPage == 1,
                        false,
                        false);
            });
            add(previousButton, 1, 0);

            add(currentPageField, 2, 0);

            add(totalPagesLabel, 3, 0);

            nextButton.setOnAction((ActionEvent e) -> {
                currentPage++;
                setCurrentPage(currentPage);
                
                loadButtonState(false,
                        false,
                        currentPage == getCurrentTotalPages(),
                        currentPage == getCurrentTotalPages());
            });
            add(nextButton, 4, 0);

            lastButton.setOnAction((ActionEvent e) -> {
                currentPage = getCurrentTotalPages();
                setCurrentPage(currentPage);

                loadButtonState(true,
                        true,
                        currentPage == getCurrentTotalPages(),
                        currentPage == getCurrentTotalPages());
            });
            add(lastButton, 5, 0);
            
            add(entriesLabel, 6, 0);
            add(pageSizeLabel, 7, 0);
            
            pageOptions.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    
                }
            });
            add(pageOptions, 8, 0);

            setDefaultState();
        }

        private void loadTableContent() {
            List<Item> temp = new ArrayList<>();
            for (int i = (currentPage - 1) * getCurrentPageSize(); i < currentPage * getCurrentPageSize(); i++) {
                if (items.get(i) != null) {
                    temp.add(items.get(i));
                } else {
                    break;
                }
            }
            table.setItems(FXCollections.observableList(temp));
        }

        private int getCurrentPageSize() {
            return Integer.parseInt(pageOptions.getValue().toString());
        }

        private int getCurrentTotalPages() {
            return Integer.parseInt(totalPagesLabel.getText());
        }

        private void setCurrentPage(int currPage) {
            currentPageField.setText(String.valueOf(currPage));
            loadTableContent();
        }

        private void setDefaultState() {
            loadButtonState(true, true, true, true);
            pageOptions.setValue(DEFAULT_NO_OF_RECORDS);
            setCurrentPage(1);
        }

        private void loadButtonState(boolean first, boolean previous, boolean next, boolean last) {
            firstButton.setDisable(first);
            previousButton.setDisable(previous);
            nextButton.setDisable(next);
            lastButton.setDisable(last);
        }

    }
}
