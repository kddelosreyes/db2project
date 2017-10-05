/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.components;

import com.project.api.managers.FieldManager;
import com.project.api.oraclesql.Column;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Component extends GridPane {

    private final Label labelCaption = new Label();
    private Control componentField;

    private final String caption;
    private final Column tableColumn;

    private void init() {
        labelCaption.setText(caption);
        labelCaption.setFont(FieldManager.CAPTION_FONT);

        add(labelCaption, 0, 0);
    }

    public Component(String caption, Column tableColumn) {
        this.caption = caption;
        this.tableColumn = tableColumn;

        init();
    }

    public String getCaption() {
        return caption;
    }
    
    public Column getTableColumn() {
    	return tableColumn;
    }
    
    public void setComponentField(Control componentField) {
    	this.componentField = componentField;
    	add(componentField, 0, 1);
    }

    public void setEditable(boolean isEditable) {
        componentField.setDisable(isEditable);
    }
}
