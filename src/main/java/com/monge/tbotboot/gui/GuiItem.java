/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;

import com.monge.tbotboot.messenger.MessageMenu;
import com.monge.tbotboot.messenger.Xupdate;

/***
 * Esta clase abstracta representa la execucion de un GUI de interacion,
 * es como si representara un mini programa dentro telegram, atravez de un mensaje
 * @author DeliveryExpress
 */
public abstract class GuiItem extends GuiElement {
    
    
    String commandSplitter;
  
    /*indica si el padre responde el update de lo contrario en hijo es el que envia la respuesta*/
    boolean disableParentResponse = false;

    /***
     * 
     * @param parent elemeto padre
     * @param text nombre del item o boton
     */
    public GuiItem(GuiElement parent, String text) {
        super(parent, text);
        commandSplitter = "&";
    }

    /*executa los comandos o funciones del usuario*/
    public abstract void execute(Xupdate update);
    
    /***
     * 
     * @return debe regresar el menu de este item o mini programa
     */
    public abstract MessageMenu getMenu();

    public String getCommandSplitter() {
        return commandSplitter;
    }

    /***
     * Cambia el splitter para procesar los comandos.
     * Commandos de telegram de la libreria TBOTBOOT utiliza & por default
     * sin embargo para los objetos GUI puede ser necesario cambiarlos al asignar valores a travez de GUI
     * Agunos GUI pueden solicitar lo siquientes
     * Ejemplos
     * name = El alienigena (Aqui deberias usar & o = ya que el valor a signar contiene espacios
     * y esto puede generar errores al obtener parametros del comando)
     * send 500 ref:10001 (Aqui es un ejemplo de un comando para transferir, en este caso el comando requiere 
     * dos parametros (monto -> 500, ref:10001) el cual se utiliza espacio para generar los parametros.
     * revise la estrucura de la clase command {@link Command}
     * 
     * @param commandSplitter 
     */
    public void setCommandSplitter(String commandSplitter) {
        this.commandSplitter = commandSplitter;
    }

    public boolean isDisableParentResponse() {
        return disableParentResponse;
    }

    public void setDisableParentResponse(boolean disableParentResponse) {
        this.disableParentResponse = disableParentResponse;
    }

   
   
    
    

}
