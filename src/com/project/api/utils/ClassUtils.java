/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.utils;

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
    
}
