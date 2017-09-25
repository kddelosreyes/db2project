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
public class NumberFormatUtils {

	public static final String DECIMAL_FORMAT = "###,###.##";
    public static final String INTEGER_FORMAT = "###,###";

    public static final String DECIMAL_FORMAT_2ZEROS = "###,##0.00";
    public static final String DECIMAL_FORMAT_3ZEROS = "###,##0.000";
    public static final String DECIMAL_FORMAT_4ZEROS = "###,##0.0000";
    public static final String DECIMAL_FORMAT_5ZEROS = "###,##0.00000";
    public static final String DECIMAL_FORMAT_6ZEROS = "###,##0.000000";

    public static String formatValue(DecimalFormat formatter, Number value) {
    	return formatter.format(value);
    }
    
}
