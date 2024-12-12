/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;

import com.monge.tbotboot.messenger.MessageMenu;
import java.util.ArrayList;

/**
 * *
 * Esta clase representa un menu con submenus o items(Botones)
 *
 * @author DeliveryExpress
 */
public class GuiMenu extends GuiElement {

    /*lista menus o items de este menu*/
    private ArrayList<GuiBase> elements;

    public GuiMenu(GuiElement parent, String text) {
        super(parent, text);
        elements = new ArrayList<>();
    }

    public GuiBase getElement(int index) {
        return elements.get(index);
    }

    @Override
    public String draw() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        sb.append(this.getText()).append(" --->\n");
        for (GuiBase e : elements) {

            if (e instanceof GuiMenu) {
                sb.append(" " + index + ") " + ((GuiMenu) e).getText() + " >").append("\n");
            } else if (e instanceof GuiItem) {
                sb.append(" " + index + ") " + ((GuiItem)e).getText()).append("\n");
            } else {
                 sb.append(" " + index + ") * " + ((GuiItemAction)e).getText()).append("\n");
            }
            index += 1;

        }

        return sb.toString();

    }

    public MessageMenu getAsMenu() {

        MessageMenu menu = new MessageMenu();
        int index = 0;

        for (GuiBase e : elements) {

            if (e instanceof GuiMenu) {
                // sb.append(" " + index + ") " + ((GuiMenu) e).getText() + " >").append("\n");
                menu.addAbutton(((GuiMenu) e).getAsButton(index), true);
            } else if (e instanceof GuiItem) {
                // sb.append(" " + index + ") " + e.getText()).append("\n");
                menu.addAbutton(((GuiItem) e).getAsButton(index), true);
            } else {
                menu.addAbutton(((GuiItemAction) e).getAsButton(index), true);
            }
            index += 1;

        }

        return menu;

    }

    public MessageMenu.Button getAsButton(int index) {
        return new MessageMenu.Button(super.getText() + " >", String.valueOf(index));
    }

    public void addItem(GuiBase e) {
        e.setParent(this);
        elements.add(e);

    }

}
