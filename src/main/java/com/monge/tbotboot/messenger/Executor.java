/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger;

import com.monge.tbotboot.objects.FileType;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.groupadministration.CreateChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.ChatInviteLink;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author DeliveryExpress Esta clase executa el Telegram action o envia el
 * mensaje
 */
public class Executor {

    /**
     * *
     * Executa el Response conforme a la configuracion establecida
     *
     * @param response
     * @return el mismo telegramAction pero con el messageID generado al
     * entregar
     */
    public static Response execute(Response response) {

        if (response.getReceptor() == null) {
            throw new NullPointerException("El receptor no puede ser nulo");
        }

        Bot botOut = BotsHandler.botsList.get(response.getReceptor().getBot());

        if (botOut == null) {
            throw new NullPointerException("No se encontro bot");
        }

        System.out.println("TelegramAction - bot " + botOut.getBotUsername());

        switch (response.getAction()) {

            case ResponseAction.SEND_MESSAGE:

                SendMessage sm = new SendMessage();
                sm.setChatId(response.getReceptor().getId());
                sm.setText(response.getText());
                if (response.isHtml()) {
                    sm.enableHtml(true);

                }
                if (response.getThreadId() != null) {
                    sm.setMessageThreadId(Integer.valueOf(response.getThreadId()));

                }

                if (response.getMenu() != null) {
                    sm.setReplyMarkup(response.getMenu().getReplyKeyboard());

                }

                 {
                    try {
                        Message execute = botOut.execute(sm);

                        response.setMessageId(String.valueOf(execute.getMessageId()));

                    } catch (TelegramApiException ex) {
                        Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;

            case ResponseAction.EDIT_MESSAGE:
                try {
                    EditMessageText emt = new EditMessageText();
                    emt.setMessageId(Integer.valueOf(response.getEditMessageId()));
                    emt.setChatId(response.getReceptor().getId());
                    
                    if(response.getText()!=null){
                    emt.setText(response.getText());
                    }else{
                    emt.setText("null");
                    }
                    
                    
                    
                    if (response.isHtml()) {
                        emt.enableHtml(true);
                    }

                    if (response.getMenu() != null) {
                        emt.setReplyMarkup((InlineKeyboardMarkup) response.getMenu().getReplyKeyboard());

                    }

                    Serializable execute = botOut.execute(emt);

                    response.setMessageId(String.valueOf(execute.toString()));

                } catch (TelegramApiException ex) {
                    String message = ex.getMessage();
                    // 

                    if (message.equals(TelegramApiExecptionsMessage.CANT_EDIT_MSG)) {
                        response.setAction(ResponseAction.SEND_MESSAGE);
                        response.execute();

                    } else {
                        Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                break;

            case ResponseAction.EDIT_MEDIA_MESSAGE:

                EditMessageMedia emd = new EditMessageMedia();
                emd.setChatId(response.getReceptor().getId());
                emd.setMessageId(Integer.parseInt(response.getMessageId()));
                 if (response.getMenu() != null) {
                        emd.setReplyMarkup((InlineKeyboardMarkup) response.getMenu().getReplyKeyboard());

                    }
             
                try {
                        Serializable execute = botOut.execute(emd);

                        response.setMessageId(String.valueOf(execute.toString()));

                    } catch (TelegramApiException ex) {
                        Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                break;

            case ResponseAction.DELETE_MESSAGE:

                DeleteMessage dm = new DeleteMessage();
                dm.setMessageId(Integer.parseInt(response.getMessageId()));
                dm.setChatId(response.getReceptor().getId());

                 {
                    try {
                        Boolean execute = botOut.execute(dm);

                        response.setMessageId(String.valueOf(execute.toString()));

                    } catch (TelegramApiException ex) {
                        Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;

            case ResponseAction.SEND_FILE:

                /*obtenemos el file id relacionado con el bot que solicita*/
                String fileId = response.getFile().getFileId();

                switch (response.getFile().getType()) {

                    case FileType.VIDEO:

                        SendVideo sendVideo = new SendVideo();
                        sendVideo.setChatId(response.getReceptor().getId());
                        sendVideo.setCaption(response.getText());
                        InputFile video = new InputFile(fileId);
                        sendVideo.setVideo(video);

                        if (response.getMenu() != null) {
                            sendVideo.setReplyMarkup((InlineKeyboardMarkup) response.getMenu().getReplyKeyboard());

                        }

                         {
                            try {
                                botOut.execute(sendVideo);
                            } catch (TelegramApiException ex) {
                                Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        break;
                        
                    case FileType.IMAGE:
                        
                        SendPhoto sendPhoto = new SendPhoto();
                        sendPhoto.setChatId(response.getReceptor().getId());
                        sendPhoto.setCaption(response.getText());
                        InputFile photo = new InputFile(fileId);
                        sendPhoto.setPhoto(photo);

                        if (response.getMenu() != null) {
                            sendPhoto.setReplyMarkup((InlineKeyboardMarkup) response.getMenu().getReplyKeyboard());

                        }

                         {
                            try {
                                botOut.execute(sendPhoto);
                            } catch (TelegramApiException ex) {
                                Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        
                        break;


                }

                break;

        }

        return response;
    }

    public static ArrayList<ChatMember> execute(String botUserName, GetChatAdministrators chatAdmins) {

        try {
            Bot botOut = BotsHandler.botsList.get(botUserName);
            ArrayList<ChatMember> execute = botOut.execute(chatAdmins);
            return execute;
        } catch (TelegramApiException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ChatInviteLink execute(String botUserName, CreateChatInviteLink link) {

        try {
            Bot botOut = BotsHandler.botsList.get(botUserName);
            ChatInviteLink execute = botOut.execute(link);
            return execute;
        } catch (TelegramApiException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static File execute(String botUserName, GetFile file) {

        Bot botOut = BotsHandler.botsList.get(botUserName);
        try {
            File execute = botOut.execute(file);
            return execute;
        } catch (TelegramApiException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Message execute(String botUserName, SendPhoto sendPhoto) {

        Bot botOut = BotsHandler.botsList.get(botUserName);
        try {
            Message execute = botOut.execute(sendPhoto);
            return execute;
        } catch (TelegramApiException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static Message execute(String botUserName, SendDocument sendDocument) {

        Bot botOut = BotsHandler.botsList.get(botUserName);
        try {
            Message execute = botOut.execute(sendDocument);
            return execute;
        } catch (TelegramApiException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static interface TelegramApiExecptionsMessage {

        String CANT_EDIT_MSG = "Error executing org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText query: [400] Bad Request: message can't be edited";
        String NO_TEXT_TO_EDIT = "Error executing org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText query: [400] Bad Request: there is no text in the message to edit";

    }

}
