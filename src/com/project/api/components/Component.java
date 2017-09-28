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
import java.awt.Font;

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
    
    private final int CAPTION_FONT_SIZE = 8;
    private final int COMPONENT_FIELD_FONT_SIZE = 13;

    private void init() {
        initializeComponentField();
        setLayout(new GridLayout(0, 1));
        labelCaption = initLabel();
        
        add(labelCaption);
        add(componentField);
    }
    
    private JLabel initLabel() {
        JLabel label = new JLabel(caption);
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, CAPTION_FONT_SIZE));
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

    private void initializeComponentField() {
        switch (tableColumn.getDataType()) {
            case VARCHAR2:
            case VARCHAR:
                break;
            case CHAR:
                break;
            case NUMBER:
                break;
            case DATE:
                break;
        }
    }

}
