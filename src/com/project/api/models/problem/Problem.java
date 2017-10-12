/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.models.problem;

import com.project.api.models.Attribute;
import com.project.api.models.entity.EntityAttribute;
import com.project.api.models.entitycharid.EntityCharId;
import com.project.api.models.entitycharid.EntityCharIdAttribute;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Problem extends EntityCharId {
    
    private static Character problemId = 'A';
    
    public Problem(String problemName) {
        super(problemId++, problemName);
    }
    
    @Override
    public Object getAttribute(Attribute attribute) {
        ProblemAttribute attrib = (ProblemAttribute) attribute;
        switch(attrib) {
            case ATTRIBUTE_PROBLEM_ID:
                return super.getAttribute(EntityCharIdAttribute.ENTITY_CHAR_ID_ID);
            case ATTRIBUTE_PROBLEM_NAME:
                return getSuperAttribute(EntityAttribute.ENTITY_ATTRIBUTE_DESCRIPTION);
            default:
                return null;
        }
    }
    
    @Override
    public void setAttribute(Attribute attribute, Object value) {
        ProblemAttribute attrib = (ProblemAttribute) attribute;
        switch(attrib) {
            case ATTRIBUTE_PROBLEM_NAME:
                setSuperAttribute(EntityAttribute.ENTITY_ATTRIBUTE_DESCRIPTION, value);
                break;
            case ATTRIBUTE_PROBLEM_ID:
                super.setAttribute(EntityCharIdAttribute.ENTITY_CHAR_ID_ID, value);
                break;
        }
    }
    
}
