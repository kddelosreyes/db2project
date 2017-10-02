/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.components;

import com.project.api.managers.FieldManager;
import com.project.api.oraclesql.TableColumn;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Component extends GridPane {

    private static Label labelCaption;
    private static Control componentField;

    private final String caption;
    private final TableColumn tableColumn;

    private void init() {
        labelCaption = initializeLabel();
        componentField = initializeComponentField();

        add(labelCaption, 0, 0);
        add(componentField, 0, 1);
    }

    private Label initializeLabel() {
        Label label = new Label(caption);
        label.setFont(FieldManager.CAPTION_FONT);
        return label;
    }

    public Component(String caption, TableColumn tableColumn) {
        this.caption = caption;
        this.tableColumn = tableColumn;

        init();
    }

    public String getCaption() {
        return caption;
    }

    private Control initializeComponentField() {
        switch (tableColumn.getDataType()) {
            case VARCHAR2:
            case VARCHAR:
                return FieldManager.getStringTextField();
            case CHAR:
                return FieldManager.getCharTextField();
            case NUMBER:
                if (tableColumn.getPrecision() != null && tableColumn.getScale() != null) {
                    return FieldManager.getDecimalTextField();
                }
                return FieldManager.getIntegerTextField();
            case DATE:
                return FieldManager.getDateField();
            default:
                return null;
        }
    }

    public void setEditable(boolean isEditable) {
        componentField.setDisable(isEditable);
    }
}
