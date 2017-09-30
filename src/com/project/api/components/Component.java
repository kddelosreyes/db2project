/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.components;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.project.api.oraclesql.TableColumn;
import com.project.api.utils.NumberFormatUtils;
import java.awt.Font;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Component extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static JLabel labelCaption;
    private static JComponent componentField;

    private final String caption;
    private final TableColumn tableColumn;
    
    private final String FONT_FACE = "Arial";
    private final int CAPTION_FONT_SIZE = 8;
    private final int FIELD_FONT_SIZE = 13;
    private final int ALIGNMENT_CENTER = 0;
    private final int ALIGNMENT_LEFT = 2;
    private final int ALIGNMENT_RIGHT = 4;
    
    private final Font CAPTION_FONT = new Font(FONT_FACE, Font.PLAIN, CAPTION_FONT_SIZE);
    private final Font COMPONENT_FONT = new Font(FONT_FACE, Font.PLAIN, FIELD_FONT_SIZE);

    private void init() {        
        setLayout(new GridLayout(0, 1));
        labelCaption = initLabel();
        componentField = initializeComponentField();
        
        add(labelCaption);
        add(componentField);
    }
    
    private JLabel initLabel() {
        JLabel label = new JLabel(caption);
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

    private JComponent initializeComponentField() {
        switch (tableColumn.getDataType()) {
            case VARCHAR2:
            case VARCHAR:
                return getStringTextField();
            case CHAR:
                return getCharTextField();
            case NUMBER:
                if(tableColumn.getPrecision() != null && tableColumn.getScale() != null) {
                    return getDecimalTextField();
                }
                return getIntegerTextField();
            case DATE:
                return null;
            default:
                return null;
        }
    }

    private JTextField getStringTextField() {
        JTextField textField = getTextField(false, null);
        textField.setHorizontalAlignment(ALIGNMENT_LEFT);
        return textField;
    }
    
    private JTextField getCharTextField() {
        JTextField textField = getStringTextField();
        textField.setColumns(1);
        return textField;
    }
    
    /*private JDateField getDateField() {
        
    }*/
    
    private JTextField getIntegerTextField() {
        JTextField textField = getNumericField(true, NumberFormatUtils.getIntegerFormat());
        textField.setHorizontalAlignment(ALIGNMENT_RIGHT);
        return textField;
    }
    
    private JTextField getDecimalTextField() {
        JTextField textField = getNumericField(true, NumberFormatUtils.getDecimalFormat());
        return textField;
    }
    
    private JTextField getNumericField(boolean isFormatted, NumberFormat numberFormat) {
        JTextField textField = getTextField(isFormatted, numberFormat);
        textField.setHorizontalAlignment(ALIGNMENT_RIGHT);
        return textField;
    }
    
    private JTextField getTextField(boolean isFormatted, NumberFormat numberFormat) {
        JTextField textField = isFormatted ? new JFormattedTextField(numberFormat) : new JTextField();
        textField.setFont(COMPONENT_FONT);
        return textField;
    }
    
    public void setEditable(boolean isEditable) {
        componentField.setEnabled(isEditable);
    }
}
