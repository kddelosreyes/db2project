/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models;

import com.project.api.oraclesql.DataType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Item {
    
    private Map<Object, Object> propertyValue;
    private Map<Object, DataType> propertyDataType;
    
    public Item() {
        propertyValue = new HashMap<>();
        propertyDataType = new HashMap<>();
    }
    
    public void addItemPropertyValue(Object propertyId, Object value, DataType dataType) {
        setValue(propertyId, value);
        setDataType(propertyId, dataType);
    }
    
    public Object getValue(Object propertyId) {
        switch(getDataType(propertyId)) {
            case VARCHAR2:
            case VARCHAR:
                return (String) propertyValue.get(propertyId);
            case NUMBER:
                return (Long) propertyValue.get(propertyId);
            case CHAR:
                return (Character) propertyValue.get(propertyId);
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
        return propertyDataType.get(propertyId);
    }
    
    private void setDataType(Object propertyId, DataType dataType) {
        propertyDataType.put(propertyId, dataType);
    }
    
}
