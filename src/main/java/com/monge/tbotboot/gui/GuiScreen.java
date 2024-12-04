/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;

import com.monge.tbotboot.messenger.MessageMenu;

/**
 *
 * @author DeliveryExpress
 */
public class GuiScreen {

    GuiMenu mainMenu;
    GuiElement current;

    public GuiScreen(GuiMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.current = mainMenu;
    }

    public boolean canBack() {
        return current.getParent() != null;
    }

    public void back() {
        if (current.getParent() != null) {
            current = current.getParent();
        }
    }

    public void enter(int item) {
        if (current instanceof GuiMenu) {
            GuiMenu menu = (GuiMenu) current;
            current = menu.getElement(item);
        }
    }

    public String draw() {
        return current.draw();

    }

    public void execute(String data) {
        if (data.equals("--back")) {
            this.back();
            return;
        }

        if (current instanceof GuiMenu) {

            try {
                int select = Integer.parseInt(data);
                this.enter(select);
                return;
            } catch (NumberFormatException e) {
                return;
            }

        } else {

            GuiItem item = (GuiItem) current;
            item.execute(data);
        }

    }

    public MessageMenu getMenu() {
        MessageMenu menu = new MessageMenu();
        if (this.canBack()) {
            menu.addButton(" <-- ", "--back");
        }
        menu.addButton(" Salir ", "/salir");

        if (current instanceof GuiItem && current != null) {

            GuiItem c = (GuiItem) current;
            menu.merge(c.getMenu());
        }

        return menu;

    }

}
