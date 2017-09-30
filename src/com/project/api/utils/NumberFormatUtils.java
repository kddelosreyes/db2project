/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.utils;

import java.text.NumberFormat;
import java.util.Currency;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class NumberFormatUtils {

    private static final Currency currency = Currency.getInstance("en_PH");
    
    private static final NumberFormat decimalFormat;
    private static final NumberFormat integerFormat;
    private static final NumberFormat currencyFormat;
    private static final NumberFormat percentageFormat;

    static {
        decimalFormat = NumberFormat.getNumberInstance();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        
        integerFormat = NumberFormat.getIntegerInstance();
        
        currencyFormat = NumberFormat.getCurrencyInstance();
        currencyFormat.setGroupingUsed(true);
        currencyFormat.setMaximumFractionDigits(2);
        currencyFormat.setMinimumFractionDigits(2);
        currencyFormat.setCurrency(currency);
        
        percentageFormat = NumberFormat.getPercentInstance();
        percentageFormat.setMaximumFractionDigits(2);
        percentageFormat.setMinimumFractionDigits(2);
    }
    
    public static String formatValue(NumberFormat numberFormat, Number value) {
        return numberFormat.format(value);
    }
    
    public static NumberFormat getIntegerFormat() {
        return integerFormat;
    }
    
    public static NumberFormat getDecimalFormat() {
        return decimalFormat;
    }
    
    public static NumberFormat getCurrencyFormat() {
        return currencyFormat;
    }
    
    public static NumberFormat getPercentageFormat() {
        return percentageFormat;
    }

}
