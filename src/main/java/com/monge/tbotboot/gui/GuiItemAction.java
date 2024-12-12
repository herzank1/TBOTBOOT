/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;

import com.monge.tbotboot.messenger.MessageMenu;
import com.monge.tbotboot.messenger.Xupdate;

/**
 *
 * @author DeliveryExpress
 */
public abstract class GuiItemAction extends GuiBase {

    String postMessage;

    /***
     * 
     * @param parent
     * @param text textbutton
     * @param initalMessage 
     */
    public GuiItemAction(GuiElement parent, String text,String initalMessage) {
        super(parent, text);
        this.postMessage = initalMessage;
    }

    public abstract void execute(Xupdate xupdate);

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }
    
    

    public MessageMenu.Button getAsButton(int index) {
        return new MessageMenu.Button("✴" +super.getText(), String.valueOf(index));
    }

}
