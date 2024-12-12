/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.quizes;

import com.monge.tbotboot.gui.GuiMenu;
import com.monge.tbotboot.gui.GuiScreen;
import com.monge.tbotboot.messenger.Response;
import com.monge.tbotboot.messenger.Xupdate;
import com.monge.tbotboot.objects.Receptor;
import com.monge.tbotboot.objects.TelegramUser;

/**
 *
 * @author DeliveryExpress
 * Esta clase es la encargada de controlar el GUIScreen con la interacion del usuario 
 * a traves de los metodos de QUIZ
 */
public abstract class QuizGui extends Quiz {

    GuiScreen screen;
    Receptor receptor;
    String screenMsgId;

    /**
     * *
     *
     * @param userId
     * @param screen
     */
    public QuizGui(Receptor receptor) {
        super(receptor.getId());
        this.receptor = receptor;
        screen = new GuiScreen(null);

    }
    
    public QuizGui(Receptor receptor,boolean menuGUI) {
        super(receptor.getId());
        this.receptor = receptor;
        screen = new GuiScreen(null,menuGUI);

    }

    public void setMainMenu(GuiMenu main) {
        screen.setMainMenu(main);
        screen.setCurrent(main);
    }

    @Override
    public void execute(Xupdate xupdate) {
        if (screen == null) {
            throw new java.lang.NullPointerException("No se ha definido el Screen");
        }
        
        if (screen.getMainMenu() == null) {
            throw new java.lang.NullPointerException("No se ha definido el menu principal del Screen");
        }

        switch (super.getStep()) {

            case 0:
                Response sendMessage = Response.sendMessage(xupdate.getTelegramUser(), screen.draw(), screen.getMenu());
                screenMsgId = sendMessage.getMessageId();
                super.next();
                break;

            case 1:
                screen.execute(xupdate);

                if (!xupdate.isCallBack()) {
                    Response.deleteMessage(xupdate);
                }
                
                if(!screen.getDisableParentResponse()){
                   Response.editMessage(xupdate.getTelegramUser(), this.screenMsgId, screen.draw(), screen.getMenu());
                }

             
                break;

        }

    }

    @Override
    public void destroy() {
        super.destroy();
        onDestroy();
    }

    private void onDestroy() {
        Response.deleteMessage(receptor, this.screenMsgId);
    }

    public String getScreenMsgId() {
        return screenMsgId;
    }

    /*Establece el id del mensage que se estara actualizando cuando se execute el metodo super.execute(xupdate)*/
    public void setScreenMsgId(String screenMsgId) {
        this.screenMsgId = screenMsgId;
    }

    public GuiScreen getScreen() {
        return screen;
    }

    public void setScreen(GuiScreen screen) {
        this.screen = screen;
    }

}
