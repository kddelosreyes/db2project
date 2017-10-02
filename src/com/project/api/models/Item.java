/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.project.api.oraclesql.DataType;
import com.project.api.oraclesql.Column;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Item {

    private final Map<Object, Object> propertyValue;
    private final Map<Object, Column> propertyColumn;

    public Item() {
        propertyValue = new HashMap<>();
        propertyColumn = new HashMap<>();
    }

    public void addItemPropertyValue(Object propertyId, Object value, Column tableColumn) {
        setValue(propertyId, value);
        setColumn(propertyId, tableColumn);
    }

    public Object getValue(Object propertyId) {
        switch (getDataType(propertyId)) {
            case VARCHAR2:
            case VARCHAR:
                return (String) propertyValue.get(propertyId);
            case CHAR:
                return (Character) propertyValue.get(propertyId);
            case NUMBER:
                if (hasScale(propertyId)) {
                    return (Double) propertyValue.get(propertyId);
                }
                return (Long) propertyValue.get(propertyId);
            case DATE:
                return (Date) propertyValue.get(propertyId);
            default:
                return null;
        }
    }

    private void setValue(Object propertyId, Object value) {
        propertyValue.put(propertyId, value);
    }

    private DataType getDataType(Object propertyId) {
        return propertyColumn.get(propertyId).getDataType();
    }

    private void setColumn(Object propertyId, Column tableColumn) {
        propertyColumn.put(propertyId, tableColumn);
    }

    private boolean hasScale(Object propertyId) {
        return propertyColumn.get(propertyId).getScale() != null;
    }

}
