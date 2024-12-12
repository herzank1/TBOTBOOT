package com.monge.tbotboot.gui;

import com.monge.tbotboot.messenger.MessageMenu.Button;

/**
 * *
 * Representa un elemento del GUI
 *
 * @author DeliveryExpress
 */
public abstract class GuiElement extends GuiBase{

    public GuiElement(GuiElement parent, String text) {
        super(parent, text);
    }

    /*Muestra la informacion en el mensaje de telegram*/
    public abstract String draw();

   

    public Button getAsButton(int index) {
        return new Button(super.getText(), String.valueOf(index));
    }

}
