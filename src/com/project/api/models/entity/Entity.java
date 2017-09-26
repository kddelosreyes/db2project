/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.entity;

import java.io.Serializable;

/**
 *
 * @author Kim Howel delos Reyes
 */
public abstract class Entity implements Serializable {

    private Integer id;

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public abstract Object getAttribute(EntityAttribute attrib);

    public abstract void setAttribute(EntityAttribute attrib, Object value);

}
