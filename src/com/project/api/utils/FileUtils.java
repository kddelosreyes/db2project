/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class FileUtils {
    
    public static BufferedReader getFileReader(String filename) throws IOException, FileNotFoundException {
        return new BufferedReader(new FileReader(new File(filename)));
    }
    
    public static BufferedWriter getFileWriter(String filename) throws IOException, FileNotFoundException {
        return new BufferedWriter(new FileWriter(new File(filename)));
    }
    
    public static String getValue(String parameter) {
        return parameter.split("=")[1];
    }
    
}
