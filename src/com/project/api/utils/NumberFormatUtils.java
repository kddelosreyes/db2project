/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.utils;

import java.util.function.UnaryOperator;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class NumberFormatUtils {

    private static final UnaryOperator<TextFormatter.Change> INTEGER_FILTER = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("-?([1-9][0-9]*)?")) {
            return change;
        }
        return null;
    };

    private static final UnaryOperator<TextFormatter.Change> DECIMAL_FILTER = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\\\d{0,7}([\\\\.]\\\\d{0,4})?")) {
            return change;
        }
        return null;
    };

    public static UnaryOperator<TextFormatter.Change> getIntegerFilter() {
        return INTEGER_FILTER;
    }

    public static UnaryOperator<TextFormatter.Change> getDecimalFilter() {
        return DECIMAL_FILTER;
    }

}
