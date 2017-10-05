/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.translations;

import java.util.ResourceBundle;

import com.project.api.app.Property;
import com.project.api.app.Property.PropertyPath;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class I18nUI {
    
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(Property.getPropertyFile(PropertyPath.TRANSLATION).getPathFile());
    
    public static String getString(Translations key) {
        return BUNDLE.getString(key.name());
    }
    
}
