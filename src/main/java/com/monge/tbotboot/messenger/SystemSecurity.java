/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger;


import com.monge.tbotboot.objects.TelegramUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author DeliveryExpress
 */
public class SystemSecurity {

    static Map<String, Integer> requestCounter = new HashMap<>();
    static List<String>blackList=new ArrayList<>();
    static int MAX_REQUEST_COUNTER = 3;

    public static void init() {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                requestCounter.clear();

            }
        };

        timer.schedule(timerTask, 1000, 1000);

        System.out.println("System security Activate!");

    }

    public static boolean allowUpdate(Xupdate xupdate) {
        TelegramUser telegramUser = xupdate.getTelegramUser();
        
        if(blackList.contains(xupdate.getSenderId())){
        return false;
        }
       
        /*si proviene de un grupo*/
        if (xupdate.isGroupMessage()) {
            return true;
        }
        
        /*verificamos is expiro*/
        if(xupdate.isExpired()){
            System.out.println("update expired!");
        return false;
        }
        
        /*contador de mensajes*/

        increaseCounter(xupdate.getFromId(), 1);

        if (requestCounter.get(xupdate.getSenderId()) > MAX_REQUEST_COUNTER ) {

            blackList.add(xupdate.getSenderId());
            Response response = new Response(telegramUser);
            response.setText("Has sido bloqueado!\n demasiados mensajes consecutivos.");
            response.execute();
            return false;
        }else{
            
            System.out.println("messages count "+requestCounter.get(xupdate.getSenderId()));
        
            return true;
        
        }

    }

    private static void increaseCounter(String telegramId, int qty) {

        try {
            requestCounter.put(telegramId, requestCounter.get(telegramId) + qty);
        } catch (Exception e) {
            requestCounter.put(telegramId, 0);
        }

    }

    public static List<String> getBlackList() {
        return blackList;
    }

    public static void setBlackList(List<String> blackList) {
        SystemSecurity.blackList = blackList;
    }
    
    public static boolean isInBlackList(String id) {
        return blackList.contains(id);
    }
    
     public static boolean removeBlackList(String id) {
        return blackList.remove(id);
    }

}
