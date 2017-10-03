/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

import com.project.api.managers.TableFieldManager;
import com.project.api.models.Item;
import com.project.api.oraclesql.Column;
import com.project.api.translations.I18nUI;
import com.project.api.translations.Translations;
import com.project.api.utils.ClassUtils;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class DataTable extends GridPane {

    private List<Item> items;
    private List<TableColumn<Item, ?>> tableColumns = new ArrayList<>();
    private Map<Button, TableColumn<Item, ?>> tableColumnButtonMap = new LinkedHashMap<>();
    private TilePane buttonPane = new TilePane();
    
    private TableView<Item> table = new TableView<>();
    private Footer footer = new Footer(table);
    private TextField searchField = new TextField();

    private final TableFieldManager tableFieldManager = new TableFieldManager();

    public DataTable(List<Item> items) {
        this.items = items;
        table.setTableMenuButtonVisible(true);

        init();
    }

    private void init() {
        searchField.setPromptText(I18nUI.getString(Translations.SEARCH));
    }

    private int getRecordsSize() {
        return items.size();
    }
    
    private void resetTableColumns() {
    	tableColumns.clear();
    	for(Button buttonKey : tableColumnButtonMap.keySet()) {
    		tableColumns.add(tableColumnButtonMap.get(buttonKey));
    	}
    }

    public void addTableColumn(Column column) {
        Class<?> classDataType = ClassUtils.getWrapper(column);

        TableColumn<Item, ?> tableColumn = null;
        Button button = new Button(column.getColumnName());
        if (String.class.equals(classDataType)) {
        	tableColumn = tableFieldManager.getStringTableColumn(column.getColumnName());
        } else if (Character.class.equals(classDataType)) {
        	tableColumn = tableFieldManager.getCharacterTableColumn(column.getColumnName());
        } else if (Double.class.equals(classDataType)) {
        	tableColumn = tableFieldManager.getDoubleTableColumn(column.getColumnName());
        } else if (Long.class.equals(classDataType)) {
        	tableColumn = tableFieldManager.getLongTableColumn(column.getColumnName());
        } else if (Date.class.equals(classDataType)) {
        	tableColumn = tableFieldManager.getDateTableColumn(column.getColumnName());
        }
        
        if(column.getColumnName().toUpperCase().contains("ID")) {
        	tableColumn.setSortType(SortType.ASCENDING);
        }
        
        tableColumns.add(tableColumn);
        button.setOnAction((ActionEvent e) -> {
        	
        });
        tableColumnButtonMap.put(button, tableColumn);
    }

    public List<TableColumn<Item, ?>> getTableColumns() {
        return tableColumns;
    }
    
    public Footer getFooter() {
    	return footer;
    }
    
	private class Pair<TableColumn, Boolean> {
    	private TableColumn key;
    	private Boolean value;
    	
    	public Pair(TableColumn key, Boolean value) {
    		this.key = key;
    		this.value = value;
    	}
    	
    	public TableColumn getKey() {
    		return key;
    	}
    	
    	public Boolean getValue() {
    		return value;
    	}
    }

    private class Footer extends TilePane {

        private ObservableList<Integer> noOfRecords = FXCollections.observableArrayList(5, 10, 50, 100, 200, 100);

        private Button firstButton = new Button(I18nUI.getString(Translations.FIRST));
        private Button previousButton = new Button(I18nUI.getString(Translations.PREVIOUS));
        private TextField currentPageField = new TextField();
        private Label totalPagesLabel = new Label();
        private Button nextButton = new Button(I18nUI.getString(Translations.NEXT));
        private Button lastButton = new Button(I18nUI.getString(Translations.LAST));
        private Label entriesLabel = new Label(I18nUI.getString(Translations.SHOWING_OUT_OF_ENTRIES));
        private Label pageSizeLabel = new Label(I18nUI.getString(Translations.PAGE_SIZE));
        private ComboBox<Integer> pageOptions = new ComboBox<>(noOfRecords);

        private final int DEFAULT_NO_OF_RECORDS = 50;

        private TableView<Item> table;
        private int currentPage = 1;

        public Footer(TableView<Item> table) {
        	setPrefColumns(3);
        	
            this.table = table;
            init();
        }

        private void init() {
        	GridPane gridPane = new GridPane();
        	gridPane.add(entriesLabel, 0, 0);
            getChildren().add(gridPane);
            
            gridPane = new GridPane();
            pageOptions.valueProperty().addListener((ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) -> {
                if (!oldValue.equals(newValue)) {
                    setDefaultState(newValue);
                }
            });
            gridPane.add(pageSizeLabel, 0, 0);
            gridPane.add(pageOptions, 1, 0);
            getChildren().add(gridPane);

            gridPane = new GridPane();
            firstButton.setOnAction((ActionEvent e) -> {
                currentPage = 1;
                setCurrentPage(currentPage);
                loadTableContent();

                loadButtonState(true, true,
                        currentPage == getCurrentTotalPages(),
                        currentPage == getCurrentTotalPages());
            });
            gridPane.add(firstButton, 0, 0);

            previousButton.setOnAction((ActionEvent e) -> {
                currentPage--;
                setCurrentPage(currentPage);

                loadButtonState(currentPage == 1, currentPage == 1, false,
                        false);
            });
            gridPane.add(previousButton, 1, 0);
            gridPane.add(currentPageField, 2, 0);
            gridPane.add(totalPagesLabel, 3, 0);

            nextButton.setOnAction((ActionEvent e) -> {
                currentPage++;
                setCurrentPage(currentPage);

                loadButtonState(false, false,
                        currentPage == getCurrentTotalPages(),
                        currentPage == getCurrentTotalPages());
            });
            gridPane.add(nextButton, 4, 0);

            lastButton.setOnAction((ActionEvent e) -> {
                currentPage = getCurrentTotalPages();
                setCurrentPage(currentPage);

                loadButtonState(true, true,
                        currentPage == getCurrentTotalPages(),
                        currentPage == getCurrentTotalPages());
            });
            gridPane.add(lastButton, 5, 0);
            getChildren().add(gridPane);
        }

        private void setDefaultState(Integer newValue) {
            loadButtonState(true, true, true, true);

            if (newValue == null) {
                pageOptions.setValue(DEFAULT_NO_OF_RECORDS);
            } else {
                pageOptions.setValue(newValue);
            }
            totalPagesLabel.setText(String.valueOf(getRecordsSize() %  newValue == 0 ? getRecordsSize() / newValue : getRecordsSize() / newValue + 1));
            setCurrentPage(1);
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

        private void loadButtonState(boolean first, boolean previous, boolean next, boolean last) {
            firstButton.setDisable(first);
            previousButton.setDisable(previous);
            nextButton.setDisable(next);
            lastButton.setDisable(last);
        }

    }
}
