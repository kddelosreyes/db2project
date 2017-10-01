/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.components;

import com.project.api.oraclesql.TableColumn;
import com.project.api.utils.NumberFormatUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Component extends GridPane {

    private static Label labelCaption;
    private static Control componentField;

    private final String caption;
    private final TableColumn tableColumn;

    private final String FONT_FACE = "Arial";
    private final int CAPTION_FONT_SIZE = 8;
    private final int FIELD_FONT_SIZE = 13;

    private final Font CAPTION_FONT = Font.font(FONT_FACE, FontWeight.NORMAL, CAPTION_FONT_SIZE);
    private final Font COMPONENT_FONT = Font.font(FONT_FACE, FontWeight.NORMAL, FIELD_FONT_SIZE);

    

    private void init() {
        labelCaption = initializeLabel();
        componentField = initializeComponentField();

        add(labelCaption, 0, 0);
        add(componentField, 0, 1);
    }

    private Label initializeLabel() {
        Label label = new Label(caption);
        label.setFont(CAPTION_FONT);
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
                return getStringTextField();
            case CHAR:
                return getCharTextField();
            case NUMBER:
                if (tableColumn.getPrecision() != null && tableColumn.getScale() != null) {
                    return getDecimalTextField();
                }
                return getIntegerTextField();
            case DATE:
                return getDateField();
            default:
                return null;
        }
    }

    private TextField getStringTextField() {
        TextField textField = getTextField(false, null);
        textField.setAlignment(Pos.CENTER_LEFT);
        return textField;
    }

    private TextField getCharTextField() {
        TextField textField = getStringTextField();
        textField.setPrefColumnCount(1);
        return textField;
    }

    private DatePicker getDateField() {
        DatePicker datePicker = new DatePicker();
        datePicker.setShowWeekNumbers(true);
        return datePicker;
    }

    private TextField getIntegerTextField() {
        TextField textField = getNumericField(true, new TextFormatter<>(new IntegerStringConverter(), 0, NumberFormatUtils.getIntegerFilter()));
        return textField;
    }

    private TextField getDecimalTextField() {
        TextField textField = getNumericField(true, new TextFormatter<>(new DoubleStringConverter(), 0.00, NumberFormatUtils.getDecimalFilter()));
        return textField;
    }

    private TextField getNumericField(boolean isFormatted, TextFormatter numberFormat) {
        TextField textField = getTextField(isFormatted, numberFormat);
        textField.setAlignment(Pos.CENTER_RIGHT);
        return textField;
    }

    private TextField getTextField(boolean isFormatted, TextFormatter numberFormat) {
        TextField textField = new TextField();
        if (isFormatted) {
            textField.setTextFormatter(numberFormat);
        }
        textField.setFont(COMPONENT_FONT);
        return textField;
    }

    public void setEditable(boolean isEditable) {
        componentField.setDisable(isEditable);
    }
}
