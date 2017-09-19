/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.oraclesql;

import com.project.api.models.constants.OracleConstants;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Sequence {

    private Schema schema;
    private String sequenceName;
    private Integer startValue;
    private Integer incrementValue;
    private Integer minimumValue;
    private Integer maximumValue;
    private Boolean hasCycle;

    public Sequence() {
    }

    public Sequence(Schema schema, String sequenceName, Integer startValue,
            Integer incrementValue, Integer minimumValue, Integer maximumValue,
            Boolean hasCycle) {
        this.schema = schema;
        this.sequenceName = sequenceName;
        this.startValue = startValue;
        this.incrementValue = incrementValue;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.hasCycle = hasCycle;
    }

    public Schema getSchema() {
        return schema;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public Integer getStartValue() {
        return startValue;
    }

    public Integer getIncrementValue() {
        return incrementValue;
    }

    public Integer getMinimumValue() {
        return minimumValue;
    }

    public Integer getMaximumValue() {
        return maximumValue;
    }

    public Boolean hasCycle() {
        return hasCycle;
    }

    @Override
    public String toString() {
        String queryString = OracleConstants.SQL_CREATE_SEQUENCE + (getSchema() != null ? getSchema().getSchemaName() + "." : "") + getSequenceName();

        if (startValue != null) {
            queryString = queryString + OracleConstants.SQL_STARTS_WITH + getStartValue();
        }
        if (incrementValue != null) {
            queryString = queryString + OracleConstants.SQL_INCREMENT_BY + getIncrementValue();
        }
        if (minimumValue != null) {
            queryString = queryString + OracleConstants.SQL_MINVALUE + getMinimumValue();
        }
        if (maximumValue != null) {
            queryString = queryString + OracleConstants.SQL_MAXVALUE + getMaximumValue();
        }
        if (hasCycle != null) {
            queryString = queryString + (hasCycle ? OracleConstants.SQL_CYCLE : OracleConstants.SQL_NO_CYCLE);
        }

        return queryString + ";";
    }

}
