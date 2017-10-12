/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.user;

import com.project.api.models.Attribute;

/**
 *
 * @author Kim Howel delos Reyes
 */
public enum UserAttribute implements Attribute {

    ATTRIBUTE_USER_ID,
    ATTRIBUTE_USER_NAME,
    ATTRIBUTE_USER_PASSWORD,
    ATTRIBUTE_USER_LAST_LOGIN_DATE,
    ATTRIBUTE_LOCALE;

}
