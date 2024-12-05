/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger.builders;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 *
 * @author DeliveryExpress
 */
public class EntityStringBuilder {
    // Texto acumulado

    private final StringBuilder textBuilder = new StringBuilder();
    // Lista de entidades
    private final List<MessageEntity> entities = new ArrayList<>();

    public EntityStringBuilder() {
    }

    

    /**
     * Método para añadir texto con una entidad.
     */
    public EntityStringBuilder append(String text, String entityType, Object entityValue) {
        int offset = textBuilder.length(); // Offset inicial
        int length = text.length();       // Longitud del texto

        // Añadir el texto al acumulador
        textBuilder.append(text);

        // Registrar la entidad
        MessageEntity entity = new MessageEntity();
        entity.setType(entityType);
        entity.setLength(length);
        entity.setOffset(offset);
        entity.setText(text);

        switch (entityType) {

            case EntityType.TEXT_LINK:
                entity.setUrl((String) entityValue);
                break;
            case EntityType.TEXT_MENTION:
            case EntityType.MENTION:
                entity.setUser((User) entityValue);

                break;

            default:

                break;

        }

        entities.add(entity);
        return this;
    }

    /**
     * Método para añadir texto sin entidades.
     */
    public EntityStringBuilder append(String text) {
        textBuilder.append(text);
        return this;
    }

    /**
     * Devuelve el texto completo.
     */
    public String getText() {
        return textBuilder.toString();
    }

    /**
     * Devuelve las entidades registradas.
     */
    public List<MessageEntity> getEntities() {
        return entities;
    }

    /**
     * Interfaz para definir los tipos de entidades.
     */
    public interface EntityType {

        String MENTION = "mention";
        String TEXT_MENTION = "text_mention";
        String HASHTAG = "hashtag";
        String CASHTAG = "cashtag";
        String BOT_COMMAND = "bot_command";
        String URL = "url";
        String EMAIL = "email";
        String PHONE_NUMBER = "phone_number";
        String BOLD = "bold";
        String ITALIC = "italic";
        String UNDERLINE = "underline";
        String STRIKETHROUGH = "strikethrough";
        String SPOILER = "spoiler";
        String CODE = "code";
        String PRE = "pre";
        String TEXT_LINK = "text_link";
        String CUSTOM_EMOJI = "custom_emoji";
    }

}
