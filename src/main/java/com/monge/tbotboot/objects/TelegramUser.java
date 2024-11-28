/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.objects;

import com.monge.tbotboot.objects.Receptor;

/**
 *
 * @author DeliveryExpress
 */
public abstract class TelegramUser extends Receptor{

    
    @Override
    public abstract String getId();

    @Override
    public abstract String getBot();
    
}
