/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.exceptions;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class FunctionalException extends Exception {
        
    public FunctionalException(String message) {
        super(message);
        System.err.println(message);
    }
    
}
