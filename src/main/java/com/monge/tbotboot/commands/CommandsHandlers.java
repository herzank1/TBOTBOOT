/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.commands;

import com.monge.tbotboot.messenger.Xupdate;



/**
 *
 * @author DeliveryExpress
 */
public interface CommandsHandlers {

    /*executar la logica de la interaccion de los usuarios de telegram con el nodo o bot*/
    void execute(Xupdate xupdate);

}
