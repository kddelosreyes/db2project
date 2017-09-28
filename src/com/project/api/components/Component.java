/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.components;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.project.api.oraclesql.TableColumn;

/**
*
* @author Kim Howel delos Reyes
*/
public class Component extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JLabel labelCaption;
	private static JComponent componentField;
	
	private final String caption;
	private final TableColumn tableColumn;

	public void init() {
		initializeComponentField();
		setLayout(new GridLayout(0, 1));
		labelCaption = new JLabel(caption);
		add(labelCaption);
		add(componentField);
	}
	
	public Component(String caption, TableColumn tableColumn) {
		this.caption = caption;
		this.tableColumn = tableColumn;
		
		init();
	}
	
	public String getCaption() {
		return caption;
	}
	
	private void initializeComponentField() {
		switch(tableColumn.getDataType()) {
			case VARCHAR2:
			case VARCHAR:
				break;
			case CHAR:
				break;
			case NUMBER:
				break;
			case DATE:
				break;
		}
	}

}
