/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.core;

import com.project.api.models.Attribute;

/**
 *
 * @author Kim Howel delos Reyes
 */
public interface Model {
    
    public Object getAttribute(Attribute attribute);
    
    public void setAttribute(Attribute attribute, Object value);
    
}
