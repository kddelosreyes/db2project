/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.contestscoreboard.entity;

import com.project.api.models.Attribute;
import com.project.api.core.Model;

/**
 *
 * @author Kim Howel delos Reyes
 */
public abstract class Entity implements Model {

    private String description;

    public Entity(String description) {
        this.description = description;
    }
    
    public abstract void setEntityId(Object entityId);
    public abstract Object getEntityId();

    @Override
    public Object getAttribute(Attribute attribute) {
        EntityAttribute attrib = (EntityAttribute) attribute;
        switch (attrib) {
            case ENTITY_ATTRIBUTE_DESCRIPTION:
                return getDescription();
            default:
                return null;
        }
    }

    private String getDescription() {
        return description;
    }

    @Override
    public void setAttribute(Attribute attribute, Object value) {
        EntityAttribute attrib = (EntityAttribute) attribute;
        switch (attrib) {
            case ENTITY_ATTRIBUTE_DESCRIPTION:
                setDescription((String) value);
                break;
        }
    }

    private void setDescription(String description) {
        this.description = description;
    }

}
