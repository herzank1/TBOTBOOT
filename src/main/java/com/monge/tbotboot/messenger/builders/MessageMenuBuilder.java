/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger.builders;

import com.monge.tbotboot.messenger.MessageMenu;

/**
 *
 * @author DeliveryExpress
 */
public class MessageMenuBuilder {

    MessageMenu menu;

    public MessageMenuBuilder() {
        this.menu = new MessageMenu();
    }

    public MessageMenu addButton(String text,String callBack) {
        this.menu.addButton(text, callBack);
        return this.menu;
    }
    
    public MessageMenu addUrlButton(String text,String callBack) {
        this.menu.addUrlButton(text, callBack);
        return this.menu;
    }

     public MessageMenu appendNewLine() {
        this.menu.addNewLine();
        return this.menu;
    }

    public MessageMenu getMenu() {
        return menu;
    }

   
     
}
