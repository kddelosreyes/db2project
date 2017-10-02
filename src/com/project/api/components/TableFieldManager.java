package com.project.api.components;

import java.util.Date;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import com.project.api.models.Item;

public class TableFieldManager {
	
	public TableColumn<Item, String> getStringTableColumn(String columnName) {
		TableColumn<Item, String> tableColumn = new TableColumn<Item, String>(columnName);
		tableColumn.setCellValueFactory(new Callback<CellDataFeatures<Item, String>, ObservableValue<String>> () {

			@SuppressWarnings("unchecked")
			@Override
			public ObservableValue<String> call(
					CellDataFeatures<Item, String> value) {
				return (ObservableValue<String>) value.getValue().getValue(columnName);
			}
			
		});
		return tableColumn;
	}
	
	public TableColumn<Item, Character> getCharacterTableColumn(String columnName) {
		TableColumn<Item, Character> tableColumn = new TableColumn<Item, Character>(columnName);
		tableColumn.setCellValueFactory(new Callback<CellDataFeatures<Item, Character>, ObservableValue<Character>> () {

			@SuppressWarnings("unchecked")
			@Override
			public ObservableValue<Character> call(
					CellDataFeatures<Item, Character> value) {
				return (ObservableValue<Character>) value.getValue().getValue(columnName);
			}
			
		});
		return tableColumn;
	}
	
	public TableColumn<Item, Double> getDoubleTableColumn(String columnName) {
		TableColumn<Item, Double> tableColumn = new TableColumn<Item, Double>(columnName);
		tableColumn.setCellValueFactory(new Callback<CellDataFeatures<Item, Double>, ObservableValue<Double>> () {

			@SuppressWarnings("unchecked")
			@Override
			public ObservableValue<Double> call(
					CellDataFeatures<Item, Double> value) {
				return (ObservableValue<Double>) value.getValue().getValue(columnName);
			}
			
		});
		return tableColumn;
	}
	
	public TableColumn<Item, Long> getLongTableColumn(String columnName) {
		TableColumn<Item, Long> tableColumn = new TableColumn<Item, Long>(columnName);
		tableColumn.setCellValueFactory(new Callback<CellDataFeatures<Item, Long>, ObservableValue<Long>> () {

			@SuppressWarnings("unchecked")
			@Override
			public ObservableValue<Long> call(
					CellDataFeatures<Item, Long> value) {
				return (ObservableValue<Long>) value.getValue().getValue(columnName);
			}
			
		});
		return tableColumn;
	}
	
	public TableColumn<Item, Date> getDateTableColumn(String columnName) {
		TableColumn<Item, Date> tableColumn = new TableColumn<Item, Date>(columnName);
		tableColumn.setCellValueFactory(new Callback<CellDataFeatures<Item, Date>, ObservableValue<Date>> () {

			@SuppressWarnings("unchecked")
			@Override
			public ObservableValue<Date> call(
					CellDataFeatures<Item, Date> value) {
				return (ObservableValue<Date>) value.getValue().getValue(columnName);
			}
			
		});
		return tableColumn;
	}
	
}
