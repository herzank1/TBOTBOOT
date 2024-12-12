/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;

import com.monge.tbotboot.messenger.MessageMenu;
import com.monge.tbotboot.messenger.Xupdate;

/**
 * El objetivo del paquete GUI esineractuar con un mensaje de telegram como si
 * fuera una interfaz o un miniapp Este paquete contiene las clases y metodos
 * necesarios para editar o actualizar la informacion de un mensaje de telegram,
 * en el cual se interactua como una consola y GUI, con botones y comandos para
 * realizar acciones.
 *
 * Diciendo esto, esta clase representa una ventana(Mensaje) que siempre debera
 * tener un menu principal para navegar entre las fuciones de la ventana
 *
 * @author DeliveryExpress
 */
public class GuiScreen {

    GuiMenu mainMenu;
    GuiBase current;
    /*true si las opciones de un menu se muestran en el mensaje o como botones*/
    boolean menuInterface;

    /**
     * *
     *
     * @param mainMenu es el menu principal del GUI
     */
    public GuiScreen(GuiMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.current = mainMenu;
    }

    /**
     * *
     *
     * @param mainMenu es el menu principal del GUI
     */
    public GuiScreen(GuiMenu mainMenu, boolean navWithMenu) {
        this.mainMenu = mainMenu;
        this.current = mainMenu;
        this.menuInterface = navWithMenu;
    }

    /**
     * *
     *
     * @return verdadero si hay un elemento anterior, o si se puede retroceder
     */
    public boolean canBack() {
        return current.getParent() != null;
    }

    /**
     * *
     * Cambia el foco del GUI al elemento padre o predesor
     */
    public void back() {
        if (current.getParent() != null) {
            current = current.getParent();
        }
    }

    /**
     *
     * @param item cambia el foco al elemento de un menu, en caso de que current
     * sea un meni, si es item(eqv. boton)
     *
     */
    public void enter(int item) {
        if (current instanceof GuiMenu) {
            GuiMenu menu = (GuiMenu) current;
            current = menu.getElement(item);
        }
    }

    /**
     * *
     * dibuja el metodo draw del item current, lo que se mostrara en el mensaje
     *
     * @return
     */
    public String draw() {

        if (this.menuInterface && current instanceof GuiMenu) {

            return "Seleccione una opcion.";
        } else if (current instanceof GuiItem) {
            return ((GuiItem) current).draw();
        } else if (current instanceof GuiItemAction) {
            return ((GuiItemAction) current).getPostMessage();
        } else {
            return ((GuiMenu) current).draw();

        }

    }

    /**
     * *
     * abre el menu , en caso de que current sea menu, o executa el metodo del
     * item
     *
     * @param data
     */
    public void execute(Xupdate update) {
        String data = update.getText();
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

        } else if (current instanceof GuiItem) {

            GuiItem item = (GuiItem) current;
            item.execute(update);
        } else {
            GuiItemAction item = (GuiItemAction) current;
            item.execute(update);

        }

    }

    /**
     * *
     *
     * @return el menu de navegacion del GUI
     */
    public MessageMenu getMenu() {
        MessageMenu menu = new MessageMenu();
        if (this.canBack()) {
            menu.addButton("⬅ Atras", "--back");
        }
        menu.addButton("❎ Salir ", "/salir");

        if (this.menuInterface && current instanceof GuiMenu) {
            GuiMenu c = (GuiMenu) current;
            menu.merge(c.getAsMenu());
        }


        /*mergeamos el menu de item (en caso de que tenga un menu)*/
        if (current instanceof GuiItem && current != null) {

            GuiItem c = (GuiItem) current;
            menu.merge(c.getMenu());
        }

        /*mergeamos el menu de item (en caso de que tenga un menu)*/
        if (current instanceof GuiItemAction && current != null) {

            GuiItemAction c = (GuiItemAction) current;
            menu.addNewLine();
            menu.addButton("✴ Executar ", "0");
        }

        return menu;

    }

    public GuiMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(GuiMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public GuiBase getCurrent() {
        return current;
    }

    public void setCurrent(GuiElement current) {
        this.current = current;
    }

    /*si es falso, el padre respondera el update (QuizGui Control)*/
    public boolean getDisableParentResponse() {

        if (current instanceof GuiItem) {
            return ((GuiItem) current).disableParentResponse;
        } else {
            return false;
        }

    }

}
