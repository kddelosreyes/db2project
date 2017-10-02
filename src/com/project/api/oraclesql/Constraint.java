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
public class Constraint {

    public enum ConstraintType {
        PRIMARY_KEY,
        FOREIGN_KEY;
    }

    private ConstraintType constraintType;

    private String constraintName;
    private Column[] primaryKeys;

    private String tableFKey;
    private Table referenceTable;
    private Column referenceTableColumn;

    public Constraint(ConstraintType constraintType,
            String constraintName,
            Column[] primaryKeys) {
        this.constraintType = constraintType;
        this.constraintName = constraintName;
        this.primaryKeys = primaryKeys;
    }

    public Constraint(ConstraintType constraintType,
            String tableFKey,
            Table referenceTable,
            Column referenceTableColumn) {
        this.constraintType = constraintType;
        this.tableFKey = tableFKey;
        this.referenceTable = referenceTable;
        this.referenceTableColumn = referenceTableColumn;
    }

    public ConstraintType getConstraintType() {
        return constraintType;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public Column[] getPrimaryKeys() {
        return primaryKeys;
    }

    public String getTableFKey() {
        return tableFKey;
    }

    public Table getReferenceTable() {
        return referenceTable;
    }

    public Column getReferenceTableColumn() {
        return referenceTableColumn;
    }

    @Override
    public String toString() {
        if (getConstraintType() == ConstraintType.PRIMARY_KEY) {
            return OracleConstants.SQL_CONSTRAINT + " " + getConstraintName() + " "
                    + OracleConstants.SQL_PRIMARY_KEY + "(" + getTableColumns() + ")";
        } else if (getConstraintType() == ConstraintType.FOREIGN_KEY) {
            return OracleConstants.SQL_CONSTRAINT + " " + getConstraintName() + " "
                    + OracleConstants.SQL_FOREIGN_KEY + "(" + getTableFKey() + ")"
                    + OracleConstants.SQL_REFERENCES + getReferenceTable().getTableName()
                    + "(" + getReferenceTableColumn().getColumnName() + ")";
        }
        return null;
    }

    private String getTableColumns() {
        String tableColumns = "";
        for (Column tableColumn : getPrimaryKeys()) {
            tableColumns += tableColumn.getColumnName() + ",";
        }
        return tableColumns.substring(0, tableColumns.length() - 1);
    }

}
