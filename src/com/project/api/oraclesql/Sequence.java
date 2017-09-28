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
public class Sequence {

	private static final String CREATE_SEQUENCE = "CREATE SEQUENCE ";
	private static final String STARTS_WITH = " START WITH ";
	private static final String INCREMENT_BY = " INCREMENT BY ";
	private static final String MINVALUE = " MINVALUE ";
	private static final String MAXVALUE = " MAXVALUE ";
	private static final String CYCLE = " CYCLE ";
	private static final String NO_CYCLE = " NO CYCLE ";

	private Schema schema;
	private String sequenceName;
	private Integer startValue;
	private Integer incrementValue;
	private Integer minimumValue;
	private Integer maximumValue;
	private Boolean hasCycle;

	public Sequence() {
	}

	public Sequence(Schema schema, String sequenceName) {
		this.schema = schema;
		this.sequenceName = sequenceName;
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
		String queryString = CREATE_SEQUENCE
				+ (getSchema().getSchemaName() != null ? getSchema()
						.getSchemaName() + "." : "") + getSequenceName();

		if (startValue != null) {
			queryString = queryString + STARTS_WITH + getStartValue();
		}
		if (incrementValue != null) {
			queryString = queryString + INCREMENT_BY + getIncrementValue();
		}
		if (minimumValue != null) {
			queryString = queryString + MINVALUE + getMinimumValue();
		}
		if (maximumValue != null) {
			queryString = queryString + MAXVALUE + getMaximumValue();
		}
		if (hasCycle != null) {
			queryString = queryString + (hasCycle ? CYCLE : NO_CYCLE);
		}

		return queryString + ";";
	}

}
