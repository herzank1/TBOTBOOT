/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.objects;

import com.monge.tbotboot.messenger.Bot;
import com.monge.tbotboot.messenger.BotsHandler;
import com.monge.tbotboot.messenger.Executor;
import com.monge.tbotboot.messenger.Xupdate;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.methods.GetFile;

import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Video;

/**
 *
 * @author DeliveryExpress Los archivos de telegram generan un id y diferente
 * cuando se envia a otro usuario o Bot es necesario que bots y que id esta
 * relacionado con el archivo.
 */
public class TelegramFile {

    String fileId;
    String fileName;
    String details;
    String type;
    String bot;

    private byte[] data;

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
    
    public TelegramFile(Xupdate aThis, List<PhotoSize> photos) {
         // Obtener la foto con la mayor resolución
            PhotoSize largestPhoto = photos.stream()
                    .max(Comparator.comparing(PhotoSize::getFileSize))
                    .orElse(null);
        
     this.fileId = largestPhoto.getFileId();
        this.details = aThis.getText();
        this.fileName = aThis.getText();
        this.type = FileType.IMAGE;
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

    public boolean download() {
        try {

            GetFile getFile = new GetFile();
            getFile.setFileId(this.fileId);
            File telegramFile = Executor.execute(this.bot, getFile);
            Bot b = BotsHandler.getBotByUserName(this.bot);
            String fileUrl = "https://api.telegram.org/file/bot" + b.getBotToken() + "/" + telegramFile.getFilePath();
            this.data = downloadFile(fileUrl);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void saveFile() {
        if (this.data == null) {
            return;
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream("files")) {
            fileOutputStream.write(data);
        } catch (IOException ex) {
            Logger.getLogger(TelegramFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private byte[] downloadFile(String fileUrl) {
        try (InputStream inputStream = new URL(fileUrl).openStream(); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            return byteArrayOutputStream.toByteArray(); // Convertir a array de bytes y retornar
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo de errores: retorna null si ocurre una excepción
        }
    }

    public byte[] getData() {
        return data;
    }

    
    
    

}
