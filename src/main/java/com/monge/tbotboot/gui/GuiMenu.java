/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;

import java.util.ArrayList;

public class GuiMenu extends GuiElement {

    private ArrayList<GuiElement> elements;

    public GuiMenu(GuiElement parent, String text) {
        super(parent, text);
        elements = new ArrayList<>();
    }

    public GuiElement getElement(int index) {
        return elements.get(index);
    }

    @Override
    public String draw() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        sb.append(this.getText()).append(" --->\n");
        for (GuiElement e : elements) {

            if (e instanceof GuiMenu) {
                sb.append(" " + index + ") " + ((GuiMenu) e).getText() + " >").append("\n");
            } else {
                sb.append(" " + index + ") " + e.getText()).append("\n");
            }
            index += 1;

        }

        return sb.toString();

    }

    public void addItem(GuiElement e) {
        e.setParent(this);
        elements.add(e);

    }

}
