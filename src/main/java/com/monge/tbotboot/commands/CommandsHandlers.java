/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.commands;

import com.monge.tbotboot.messenger.Xupdate;



/**
 *
 * @author CommandsHandlers
 * Interface, toda la interaccion con los usuarios seran a travez del metodo execute
 */
public interface CommandsHandlers {

    /*executar la logica de la interaccion de los usuarios de telegram con el nodo o bot*/
    public void execute(Xupdate xupdate);

}
