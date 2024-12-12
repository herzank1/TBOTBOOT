/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger;

import com.monge.tbotboot.objects.Receptor;
import com.monge.tbotboot.objects.TelegramFile;

/**
 *
 * @author DeliveryExpress Representa la accion o el mensaje que se enviara a
 * TelegramUser contiene el cuerpo y lo elementos internos del mensaje o accion
 */
public class Response {

    /*Usuario de destino*/
    Receptor receptor;

    String action;

    /*basic*/
    String text;
    MessageMenu menu;
    /*for updating messages*/
    String editMessageId;
    String reference;

    /*extra*/
    String threadId;

    TelegramFile file;

    /*returned messageId*/
    String messageId;
    boolean gotoGroup;
    private boolean html;

    /**
     * *
     * Default constructor de response, un mensage simple
     */
    public Response() {
        this.setAction(ResponseAction.SEND_MESSAGE);

    }

    /**
     * *
     * Default constructor de response, un mensage simple
     */
    public Response(Receptor receptor) {
        this.receptor = receptor;
        this.setAction(ResponseAction.SEND_MESSAGE);
    }

    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageMenu getMenu() {
        return menu;
    }

    public void setMenu(MessageMenu menu) {
        this.menu = menu;
    }

    public String getEditMessageId() {
        return editMessageId;
    }

    public void setEditMessageId(String editMessageId) {
        this.editMessageId = editMessageId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public void setHtml() {
        this.html = true;
    }

    public boolean isHtml() {
        return this.html;
    }

    public TelegramFile getFile() {
        return file;
    }

    public void setFile(TelegramFile file) {
        this.file = file;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public boolean isGotoGroup() {
        return gotoGroup;
    }

    public void setGotoGroup(boolean gotoGroup) {
        this.gotoGroup = gotoGroup;
    }

    public static Response sendMessage(Receptor receptor, String text, MessageMenu menu) {
        Response response = new Response(receptor);
        response.setText(text);
        response.setMenu(menu);
        return response.execute();

    }

    public static Response editMediaMessage(Receptor receptor, String messageId, TelegramFile file, String text, MessageMenu menu) {

        Response response = new Response(receptor);
        response.setAction(ResponseAction.EDIT_MEDIA_MESSAGE);
        response.setEditMessageId(messageId);
        response.setFile(file);
        response.setText(text);
        response.setMenu(menu);
        return response.execute();

    }

    public static Response editMessage(Receptor receptor, String messageId, String text, MessageMenu menu) {

        Response response = new Response(receptor);
        response.setAction(ResponseAction.EDIT_MESSAGE);
        response.setEditMessageId(messageId);
        response.setText(text);
        response.setMenu(menu);
        return response.execute();
    }

    public static Response editHtmlMessage(Receptor receptor, String messageId, String text, MessageMenu menu) {

        Response response = new Response(receptor);
        response.setAction(ResponseAction.EDIT_MESSAGE);
        response.setEditMessageId(messageId);
        response.setText(text);
        response.setMenu(menu);
        response.setHtml();
        return response.execute();
    }

    public static Response deleteGroupMessage(Xupdate xupdate) {

        Response response = new Response(xupdate.getTelegramGroup());
        response.setAction(ResponseAction.DELETE_MESSAGE);
        response.setMessageId(xupdate.getMessageId());
        return response.execute();

    }

    public static Response deleteMessage(Xupdate xupdate) {

        Response response = new Response(xupdate.getTelegramUser());
        response.setAction(ResponseAction.DELETE_MESSAGE);
        response.setMessageId(xupdate.getMessageId());
        return response.execute();

    }

    public static Response deleteMessage(Receptor receptor, String messageId) {

        Response response = new Response(receptor);
        response.setAction(ResponseAction.DELETE_MESSAGE);
        response.setMessageId(messageId);
        return response.execute();

    }

    public static Response sendFile(Receptor receptor, TelegramFile file, String text, MessageMenu menu) {
        Response response = new Response(receptor);
        response.setAction(ResponseAction.SEND_FILE);
        response.setFile(file);
        response.setText(text);
        response.setMenu(menu);
        return response.execute();
    }

    public Response execute() {
        return Executor.execute(this);
    }

    public boolean actionSuccess() {
        return this.messageId != null;
    }

}
