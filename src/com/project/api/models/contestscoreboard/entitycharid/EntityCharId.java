/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.entitycharid;

import com.project.api.models.Attribute;
import com.project.api.models.entity.Entity;

/**
 *
 * @author Kim Howel delos Reyes
 */
public abstract class EntityCharId extends Entity {

    private Character entityId;

    public EntityCharId(Character entityId, String description) {
        super(description);
        this.entityId = entityId;
    }

    @Override
    public Object getAttribute(Attribute attribute) {
        EntityCharIdAttribute attrib = (EntityCharIdAttribute) attribute;
        switch (attrib) {
            case ENTITY_CHAR_ID_ID:
                return getEntityId();
            default:
                return null;
        }
    }

    @Override
    public Object getEntityId() {
        return entityId;
    }

    @Override
    public void setAttribute(Attribute attribute, Object value) {
        EntityCharIdAttribute attrib = (EntityCharIdAttribute) attribute;
        switch (attrib) {
            case ENTITY_CHAR_ID_ID:
                setEntityId(value);
                break;
        }
    }

    @Override
    public void setEntityId(Object entityId) {
        this.entityId = (Character) entityId;
    }
    
    public Object getSuperAttribute(Attribute attribute) {
        return super.getAttribute(attribute);
    }
    
    public void setSuperAttribute(Attribute attribute, Object value) {
        super.setAttribute(attribute, value);
    }
    
}
