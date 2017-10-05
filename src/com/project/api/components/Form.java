package com.project.api.components;

import com.project.api.managers.FieldManager;

public abstract class Form {
	
	protected FieldManager manager = new FieldManager();
	
	public Form() {}
	
	
	/*
	 * FormGroup formGroup = new FormGroup("Caption", 5);
	 * formGroup.addComponent(manager.getComponentField(new Component("caption", column)));
	 * */
}
