/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;

/**
 *
 * @author DeliveryExpress
 */
public class GuiBase {

    private GuiElement parent;
    /*preview text*/
    private String text;

    public GuiBase(GuiElement parent, String text) {
        this.parent = parent;
        this.text = text;
    }
    

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
