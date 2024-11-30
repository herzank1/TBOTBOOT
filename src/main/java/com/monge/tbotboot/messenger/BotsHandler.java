/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger;

import com.monge.tbotboot.commands.CommandsHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DeliveryExpress
 */
public class BotsHandler {

    static Map<String, Bot> botsList = new HashMap<>();

    /***
     * 
     * @param ch CommandsHandlers, todos los bots debe usar el mismo CommandHandler para interactuar
     * @param bots Bot o nodo
     */
    public static void init(CommandsHandlers ch,Bot ... bots) {
        if(ch==null){
        throw new NullPointerException("CommandsHandlers no puede ser nulo.");
        }
        
        for (Bot b : bots) {
            System.out.println("Cargando bot "+b.getBotUsername()+" "+b.getBotToken());
           
            b.setCommandsHandlers(ch);
            b.init();
            add(b);
        }
        
        System.out.println("bots success!");

    }
    
    public static Bot getBotByUserName(String botUserName){
    return botsList.get(botUserName);
    }

    private static void add(Bot bot) {
        if (!botsList.containsKey(bot.getBotUsername())) {
            botsList.put(bot.getBotUsername(), bot);
        }
    }

    public static ArrayList<Bot> getBots() {
       
    return new ArrayList<>(botsList.values());
    }

}
