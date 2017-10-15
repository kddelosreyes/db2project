/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api.core;

/**
 *
 * @author Kim Howel delos Reyes
 * @param <View> user-interface matching
 * @param <Model> model-entity matching
 */
public abstract class Controller<View, Model> {

    private View view;
    private Model model;
    
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }
    
    public View getView() {
        return view;
    }
    
    public Model getModel() {
        return model;
    }
    
    public void setView(View view) {
        this.view = view;
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
}
