/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.layout.GridPane;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class FormGroup extends GridPane {

    private static final int DEFAULT_HORIZONTAL_GAP = 10;
    private static final int DEFAULT_VERTICAL_GAP = 10;

    private String caption;
    private int noOfColumns;
    private List<Component> componentList;
    private List<Component> readOnlyComponents;
    private Map<Object, Component> componentMapping;

    private int rowIndex = 0;
    private int columnIndex = 0;

    private FormGroup() {
        componentList = new ArrayList<>();
        componentMapping = new HashMap<>();
    }

    public FormGroup(String caption, int noOfColumns) {
        this();
        this.caption = caption;
        this.noOfColumns = noOfColumns;
        setVgap(DEFAULT_VERTICAL_GAP);
        setHgap(DEFAULT_HORIZONTAL_GAP);
    }

    public void addComponent(Component component) {
        add(component, columnIndex++, rowIndex);
        if (columnIndex == noOfColumns) {
            columnIndex = 0;
            rowIndex = rowIndex + 1;
        }
        componentList.add(component);
        componentMapping.put(component.getCaption(), component);
    }

    public void addReadOnlyComponent(Component component) {
        addComponent(component);
        readOnlyComponents.add(component);
    }

    public void addComponents(Component... components) {
        for (Component component : components) {
            addComponent(component);
        }
    }

    public void addReadOnlyComponents(Component... components) {
        for (Component component : components) {
            addComponent(component);
            addReadOnlyComponent(component);
        }
    }

    public Component getComponent(Object propertyId) {
        return componentMapping.get(propertyId);
    }

    public Component getReadOnlyComponent(Object propertyId) {
        if (isReadOnly(propertyId)) {
            return componentMapping.get(propertyId);
        }
        return null;
    }

    public boolean isReadOnly(Object propertyId) {
        return readOnlyComponents.contains(componentMapping.get(propertyId));
    }

    public List<Component> getAllComponents() {
        return componentList;
    }

    public String getCaption() {
        return caption;
    }

}
