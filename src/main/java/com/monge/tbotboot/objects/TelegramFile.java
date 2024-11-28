/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.objects;


import com.monge.tbotboot.messenger.Xupdate;
import java.util.HashMap;

import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Video;

/**
 *
 * @author DeliveryExpress
 * Los archivos de telegram generan un id y diferente cuando se envia a otro usuario o Bot
 * es necesario que bots y que id esta relacionado con el archivo.
 */

public class TelegramFile{

    String fileId;
    String fileName;
    String details;
    String type;
    String bot;

    
        /**
     * *
     *
     * @param document
     * @param mirror
     */
    public TelegramFile(Xupdate aThis, Document document) {
        this.fileId = document.getFileId();
        this.fileName = document.getFileName();
        this.type = FileType.DOCUMENT;
        this.bot = aThis.getBotUserName();


    }

    public TelegramFile(Xupdate aThis, Video video) {

        this.fileId = video.getFileId();
        this.details = aThis.getText();
        this.fileName = aThis.getText();
        this.type = FileType.VIDEO;
        this.bot = aThis.getBotUserName();


    }

    public TelegramFile() {
      }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBot() {
        return bot;
    }

    public void setBot(String bot) {
        this.bot = bot;
    }
    
    
    


}
