/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;


public abstract class GuiElement {

    private GuiElement parent;
    private String text;

    public GuiElement(GuiElement parent, String text) {
        this.parent = parent;
        this.text = text;
    }

    public abstract String draw();

    public GuiElement getParent() {
        return parent;
    }

    public void setParent(GuiElement parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    

}
