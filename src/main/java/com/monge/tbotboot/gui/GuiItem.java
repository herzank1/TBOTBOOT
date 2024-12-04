/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;

import com.monge.tbotboot.messenger.MessageMenu;

public abstract class GuiItem extends GuiElement {

    public GuiItem(GuiElement parent, String text) {
        super(parent, text);
    }

    public abstract void execute(String data);
    
    public abstract MessageMenu getMenu();

}
