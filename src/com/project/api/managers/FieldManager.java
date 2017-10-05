/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.managers;

import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import com.project.api.components.Component;
import com.project.api.utils.NumberFormatUtils;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class FieldManager {

    private static final String FONT_FACE = "Arial";
    private static final int CAPTION_FONT_SIZE = 8;
    private static final int FIELD_FONT_SIZE = 13;
    
    public static final Font CAPTION_FONT = Font.font(FONT_FACE, FontWeight.NORMAL, CAPTION_FONT_SIZE);
    public static final Font COMPONENT_FONT = Font.font(FONT_FACE, FontWeight.NORMAL, FIELD_FONT_SIZE);
    
    public Component getComponentField(Component component) {
    	Control control = null;
    	switch (component.getTableColumn().getDataType()) {
	        case VARCHAR2:
	        case VARCHAR:
	            control = getStringTextField();
	            break;
	        case CHAR:
	            control = getCharTextField();
	            break;
	        case NUMBER:
	            if (component.getTableColumn().getPrecision() != null && component.getTableColumn().getScale() != null) {
	            	control = getDecimalTextField();
	            } else {
	            	control = getIntegerTextField();
	            }
	            break;
	        case DATE:
	            control = getDateField();
	            break;
	        default:
	            control = null;
	    }
    	component.setComponentField(control);
    	return component;
    }
    
    public static TextField getStringTextField() {
        TextField textField = getTextField(false, null);
        textField.setAlignment(Pos.CENTER_LEFT);
        return textField;
    }

    public static TextField getCharTextField() {
        TextField textField = getStringTextField();
        textField.setPrefColumnCount(1);
        return textField;
    }

    public static DatePicker getDateField() {
        DatePicker datePicker = new DatePicker();
        datePicker.setShowWeekNumbers(true);
        return datePicker;
    }

    public static TextField getIntegerTextField() {
        TextField textField = getNumericField(true, new TextFormatter<>(new IntegerStringConverter(), 0, NumberFormatUtils.getIntegerFilter()));
        return textField;
    }

    public static TextField getDecimalTextField() {
        TextField textField = getNumericField(true, new TextFormatter<>(new DoubleStringConverter(), 0.00, NumberFormatUtils.getDecimalFilter()));
        return textField;
    }

    public static TextField getNumericField(boolean isFormatted, TextFormatter<?> numberFormat) {
        TextField textField = getTextField(isFormatted, numberFormat);
        textField.setAlignment(Pos.CENTER_RIGHT);
        return textField;
    }

    public static TextField getTextField(boolean isFormatted, TextFormatter<?> numberFormat) {
        TextField textField = new TextField();
        if (isFormatted) {
            textField.setTextFormatter(numberFormat);
        }
        textField.setFont(COMPONENT_FONT);
        return textField;
    }
    
}
