/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger;

import com.monge.tbotboot.objects.Receptor;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.groupadministration.CreateChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.ChatInviteLink;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;

/**
 *
 * @author DeliveryExpress
 */
public class Methods {

    /**
     * *
     *
     * @param botUserName caller bot
     * @return
     */
    public static ArrayList<ChatMember> getChatAdmins(Receptor receptor,String chatId) {
        //org.telegram.telegrambots.meta.api.methods

        GetChatAdministrators chatAdmins = new GetChatAdministrators();
        chatAdmins.setChatId(chatId);

        return Executor.execute(receptor.getBot(), chatAdmins);

    }

    /***
     * 
     * @param receptor
     * @param chatId group chat id, receptor must be admin to do this
     * @param memberslimit
     * @return 
     */
    public static ChatInviteLink createChatInviteLink(Receptor receptor,String chatId,int memberslimit) {
        CreateChatInviteLink link = new CreateChatInviteLink();
        link.setChatId(chatId);
        link.setMemberLimit(memberslimit);
        return Executor.execute(receptor.getBot(), link);

    }
    
    
    public static void sendLocalPhoto(Receptor receptor,byte[] photo,String text,MessageMenu menu){
    
            // Verificar que los parámetros no sean nulos
        if (receptor == null || photo == null) {
            throw new IllegalArgumentException("Receptor, photo o bot no pueden ser nulos.");
        }
        
            
        try {
            // Guardar temporalmente la foto en un archivo local
            String tempFilePath = "temp_photo.jpg";
            Files.write(java.nio.file.Paths.get(tempFilePath), photo);
            
             // Crear el objeto SendPhoto
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(receptor.getId());
            sendPhoto.setPhoto(new InputFile(new java.io.File(tempFilePath))); // Cargar el archivo temporal
            sendPhoto.setCaption(text);
            
            if(menu!=null){
            sendPhoto.setReplyMarkup(menu.getReplyKeyboard());
            }
            
            // Enviar la foto usando el bot
            
             Executor.execute(receptor.getBot(), sendPhoto);

            // Eliminar el archivo temporal después de enviarlo
            Files.deleteIfExists(java.nio.file.Paths.get(tempFilePath));
            
        } catch (IOException ex) {
            Logger.getLogger(Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
