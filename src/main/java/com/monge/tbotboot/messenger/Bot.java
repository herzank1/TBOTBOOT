/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger;

import com.monge.tbotboot.commands.CommandsHandlers;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 *
 * @author DeliveryExpress
 */
public class Bot extends TelegramLongPollingBot {

    String userName;
    String apiKey;

    CommandsHandlers commandsHandlers;

    public int MAX_MSG_PER_SECOND = 30; //telegram Api allow 30 msg per seconds
    public int MAX_MSG_PER_SECOND_TO_SAME_GROUP = 20;//telegram Api allow 20 msg per minute / one msg each 3 seconds

    public Bot() {
    }

    public Bot(String userName, String apiKey) {
        this.userName = userName;
        this.apiKey = apiKey;

    }

    public Bot(String userName, String apiKey, CommandsHandlers commandsHandlers) {
        this.userName = userName;
        this.apiKey = apiKey;
        this.commandsHandlers = commandsHandlers;

    }

    public CommandsHandlers getCommandsHandlers() {
        return commandsHandlers;
    }

    public void setCommandsHandlers(CommandsHandlers commandsHandlers) {
        this.commandsHandlers = commandsHandlers;
    }

    public boolean init() {
        TelegramBotsApi telegramBotsApi;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            BotSession registerBot = telegramBotsApi.registerBot(this);
            System.out.println(this.userName + " running:" + registerBot.isRunning());
            return true;
        } catch (TelegramApiException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    @Override
    public void onUpdateReceived(Update update) {

        Xupdate xupdate = new Xupdate(update, this.getBotUsername());
    

        if (SystemSecurity.allowUpdate(xupdate)) {
            commandsHandlers.execute(xupdate);
        } else {
            System.out.println("Update invalido");
        }

    }

    @Override
    public String getBotUsername() {
        return userName; // Devuelve el username del bot
    }

    @Override
    public String getBotToken() {
        return apiKey; // Devuelve el token del bot
    }

}
