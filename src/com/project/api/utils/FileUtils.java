/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Kim Howel delos Reyes
 */
public abstract class FileUtils {
    
    public abstract BufferedReader getFileReader() throws IOException, FileNotFoundException;
    public abstract BufferedWriter getFileWriter() throws IOException, FileNotFoundException;
    
    public String getValue(String parameter) {
        return parameter.split("=")[1];
    }
    
}
