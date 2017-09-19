/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.oraclesql;

/**
 *
 * @author Kim Howel delos Reyes
 */
public enum OrderType {

    ASCENDING("ASC"),
    DESCENDING("DESC");

    String value;

    OrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
