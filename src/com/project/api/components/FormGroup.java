/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.components;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.project.api.components.Component;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class FormGroup extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String caption;

	private static final int DEFAULT_HORIZONTAL_GAP = 10;
	private static final int DEFAULT_VERTICAL_GAP = 10;

	private List<Component> componentList;
	private Map<Object, Component> componentMapping;

	private FormGroup() {
		componentList = new ArrayList<>();
		componentMapping = new HashMap<>();
	}

	public FormGroup(String caption, int noOfColumns) {
		this();
		this.caption = caption;
		setLayout(new GridLayout(0, noOfColumns, DEFAULT_HORIZONTAL_GAP,
				DEFAULT_VERTICAL_GAP));
		setBorder(new TitledBorder(null, caption, TitledBorder.LEFT,
				TitledBorder.TOP));
	}

	public void addComponent(Component component) {
		add(component);
		componentList.add(component);
		componentMapping.put(component.getCaption(), component);
	}

	public void addComponents(Component... components) {
		for (Component component : components) {
			addComponent(component);
		}
	}

	public Component getComponent(Object propertyId) {
		return componentMapping.get(propertyId);
	}

	public List<Component> getAllComponents() {
		return componentList;
	}

	public String getCaption() {
		return caption;
	}
}
