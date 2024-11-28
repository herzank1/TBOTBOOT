/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.objects;

/**
 *
 * @author DeliveryExpress
 */
public class Receptor {
    
    String id;
    String bot;

    public Receptor(String id, String bot) {
        this.id = id;
        this.bot = bot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBot() {
        return bot;
    }

    public void setBot(String bot) {
        this.bot = bot;
    }

  
}
