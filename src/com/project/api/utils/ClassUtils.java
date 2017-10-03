/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.utils;

import java.util.Date;

import com.project.api.oraclesql.Column;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ClassUtils {
    
    private static final ClassLoader classLoader = new ClassLoader() {};
    private static final String MODEL_PACKAGE = "com.project.api.models.";
    
    public static Object loadClass(String className) throws ClassNotFoundException {
    	return classLoader.loadClass(MODEL_PACKAGE + className);
    }
    
    public static Class getWrapper(Column column) {
    	switch(column.getDataType()) {
	    	case VARCHAR:
	    	case VARCHAR2:
	    		return String.class;
	    	case CHAR:
	    		return Character.class;
	    	case NUMBER:
	    		if(column.getPrecision() != null && column.getScale() != null) {
	    			return Double.class;
	    		}
	    		return Long.class;
	    	case DATE:
	    		return Date.class;
	    	default:
	    			return null;
    	}
    }
    
}
