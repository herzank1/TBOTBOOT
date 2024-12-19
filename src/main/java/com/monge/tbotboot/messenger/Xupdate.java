/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger;

import com.monge.tbotboot.objects.TelegramUser;
import com.monge.tbotboot.objects.TelegramGroup;
import com.monge.tbotboot.commands.Command;
import com.monge.tbotboot.objects.Position;
import com.monge.tbotboot.objects.Receptor;
import com.monge.tbotboot.objects.TelegramFile;
import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.Video;

/**
 *
 * @author DeliveryExpress
 */
public class Xupdate {

    Update update;
    String botUserName;

    /**
     * *
     *
     * @param update
     * @param botUserName incoming bot
     */
    public Xupdate(Update update, String botUserName) {
        this.update = update;
        this.botUserName = botUserName;

    }

    public Update getUpdate() {
        return update;
    }

    public String getBotUserName() {
        return botUserName;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public boolean isGroupMessage() {
        if (update.hasCallbackQuery()) {
            return isGroupChat(update.getCallbackQuery().getMessage().getChat());
        } else if (update.hasMessage()) {
            return isGroupChat(update.getMessage().getChat());
        } else if (update.hasEditedMessage()) {
            return isGroupChat(update.getEditedMessage().getChat());
        } else if (update.hasChannelPost()) {
            return update.getChannelPost().getSenderChat().isChannelChat();
        }
        return false;
    }

    private boolean isGroupChat(Chat chat) {
        if (chat == null) {
            return false;
        }
        // Verificar si es un grupo o un canal
        return chat.isGroupChat() || chat.isSuperGroupChat() || chat.isChannelChat();
    }

    public boolean isCallBack() {

        return this.update.hasCallbackQuery();

    }

    public boolean hasReply() {
        // Verifica si el update contiene un mensaje
        if (update.hasMessage()) {
            Message message = update.getMessage();
            // Verifica si el mensaje tiene un reply

            return message.getReplyToMessage() != null;
        }
        return false;
    }

    public Message getMessage() {
        return update.getMessage();
    }

    public Message getRepliedMessage() {
        return update.getMessage().getReplyToMessage();
    }

    public Long getRepliedUserId() {
        // Verifica que el Update tiene un mensaje y que hay un reply
        if (update.hasMessage() && update.getMessage().getReplyToMessage() != null) {
            Message repliedMessage = update.getMessage().getReplyToMessage();
            User repliedUser = repliedMessage.getFrom(); // Usuario del mensaje al que se replica
            return repliedUser.getId(); // Devuelve el ID del usuario
        }
        return null; // No hay reply o usuario
    }

    public ArrayList<User> getReplyMencionedUsers(Message message) {

        ArrayList<User> mentionsList = new ArrayList<>();
        // Obtén las entidades del mensaje
        List<MessageEntity> entities = message.getEntities();

        for (MessageEntity entity : entities) {
            // Verifica si la entidad es un text_mention (mención de usuario con ID)
            if ("text_mention".equals(entity.getType())) {
                mentionsList.add(entity.getUser()); // Devuelve el ID del usuario mencionado
            }
        }

        return mentionsList;

    }

    /**
     *
     * @return suer or group ids
     */
    public String getFromId() {
        if (isGroupMessage()) {
            // Obtener el ID del chat (grupo, supergrupo o canal)
            if (this.update.hasCallbackQuery()) {
                return this.update.getCallbackQuery().getMessage().getChat().getId().toString();
            } else if (this.update.hasMessage()) {
                return this.update.getMessage().getChat().getId().toString();
            } else if (this.update.hasEditedMessage()) {
                return this.update.getEditedMessage().getChat().getId().toString();
            } else if (this.update.hasChannelPost()) {
                return this.update.getChannelPost().getChat().getId().toString();
            } else if (this.update.hasEditedChannelPost()) {
                return this.update.getEditedChannelPost().getChat().getId().toString();
            }
        } else {
            // Si no es grupo o canal, devolver el ID del remitente
            return getSenderId();
        }
        return "";
    }

    public String getSenderId() {
        User sender = null;

        if (update.hasCallbackQuery()) {
            sender = update.getCallbackQuery().getFrom();
        } else if (update.hasMessage()) {
            sender = update.getMessage().getFrom();
        } else if (update.hasEditedMessage()) {
            sender = update.getEditedMessage().getFrom();
        } else if (update.hasChannelPost()) {
            // En un canal, el remitente no es un usuario, pero podemos devolver el senderChat ID si es relevante
            return update.getChannelPost().getSenderChat().getId().toString();
        } else if (update.hasEditedChannelPost()) {
            return update.getEditedChannelPost().getSenderChat().getId().toString();
        }

        // Retornar el ID del remitente si existe, o "null" en caso contrario
        return (sender != null) ? sender.getId().toString() : "null";
    }

    public String getText() {
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getData();
        } else if (update.hasMessage()) {
            return getTextFromMessage(update.getMessage());
        } else if (update.hasEditedMessage()) {
            return getTextFromMessage(update.getEditedMessage());
        }else if (update.hasChannelPost()) {
            return update.getChannelPost().getText();
        }
        return "null";
    }

    private String getTextFromMessage(Message message) {
        if (message.getCaption() != null) {
            return message.getCaption();
        }

        return (message != null && message.hasText()) ? message.getText() : "null";
    }

    public Command getCommand() {
        return new Command(getText());
    }

    public boolean hasLocation() {
        return getLocation() != null;
    }

    /**
     * @return Position object if location is available; otherwise null.
     */
    public Position getLocation() {
        if (this.update.hasMessage() && this.update.getMessage().hasLocation()) {
            return createPosition(this.update.getMessage().getLocation());
        } else if (this.update.hasEditedMessage() && this.update.getEditedMessage().hasLocation()) {
            return createPosition(this.update.getEditedMessage().getLocation());
        }
        return null;
    }

    private Position createPosition(Location location) {
        return new Position(location.getLatitude(), location.getLongitude());
    }

    /**
     * *
     *
     * @return explicitamente el usuario que envio este mensaje
     */
    public TelegramUser getTelegramUser() {

        return new TelegramUser(getSenderId(), getBotUserName());

    }

    /**
     * *
     *
     * @return el grupo o el usuario de este update
     */
    public TelegramGroup getTelegramGroup() {

        if (this.isGroupMessage()) {

            return new TelegramGroup(getFromId(), getBotUserName());
        } else {
            return null;
        }

    }

    /**
     * Retrieves the message ID from the update.
     *
     * @return the message ID as a String, or null if not applicable.
     */
    public String getMessageId() {
        if (this.update.hasMessage()) {
            return extractMessageId(this.update.getMessage().getMessageId());
        } else if (this.update.hasEditedMessage()) {
            return extractMessageId(this.update.getEditedMessage().getMessageId());
        } else if (this.update.hasCallbackQuery()) {
            return extractMessageId(this.update.getCallbackQuery().getMessage().getMessageId());
        }
        return null;
    }

    private String extractMessageId(Integer messageId) {
        return messageId != null ? messageId.toString() : null;
    }

    public boolean hasNewChatMember() {
        // Verificamos si el mensaje está presente y si tiene nuevos miembros en el chat
        if (this.update.hasMessage() && this.update.getMessage() != null) {
            List<User> newChatMembers = this.update.getMessage().getNewChatMembers();
            return newChatMembers != null && !newChatMembers.isEmpty();
        }
        return false; // Retorna false si no hay mensaje o si no hay nuevos miembros
    }

    public List<User> getNewChatMembers() {

        return this.update.getMessage().getNewChatMembers();

    }

    public boolean isExpired() {
        // Obtener la fecha del mensaje según el tipo de actualización
        Integer date = getUpdateDate();

        // Validar que la fecha exista
        if (date == null) {
            // Si no hay una fecha válida, considerarlo no expirado o manejarlo según el caso
            return false;
        }

        // Obtener el tiempo actual en segundos
        long currentTimestamp = System.currentTimeMillis() / 1000;

        // Comprobar si han pasado más de 10 minutos (600 segundos)
        return (currentTimestamp - date) > SystemSecurity.UPDATE_EXPIRATION_TIME;
    }

    private Integer getUpdateDate() {
        if (this.update.hasCallbackQuery()) {
            return this.update.getCallbackQuery().getMessage().getDate();
        } else if (this.update.hasMessage()) {
            return this.update.getMessage().getDate();
        } else if (this.update.hasEditedMessage()) {
            return this.update.getEditedMessage().getDate();
        }
        return null; // Retornar null si no se encuentra una fecha
    }

    public TelegramFile getFile() {
        if (this.update.hasMessage() && this.update.getMessage().hasPhoto()) {
            List<PhotoSize> photo = this.update.getMessage().getPhoto();
            return new TelegramFile(this, photo);
        }

        if (this.update.hasMessage() && this.update.getMessage().hasDocument()) {
            Document document = this.update.getMessage().getDocument();
            return new TelegramFile(this, document);
        }

        if (this.update.hasMessage() && this.update.getMessage().hasVideo()) {
            Video video = this.update.getMessage().getVideo();
            return new TelegramFile(this, video);
        }

        return null;

    }

    public String toStringDetails() {
        return "🤖 Bot User Name: " + getBotUserName() + "\n"
                + "📩 Update Type: " + (isCallBack() ? "Callback Query" : "Message") + "\n"
                + "👥 Group Message: " + (isGroupMessage() ? "Yes" : "No") + "\n"
                + "🔑 From ID: " + getFromId() + "\n"
                + "👤 Sender ID: " + getSenderId() + "\n"
                + "💬 Text: " + getText() + "\n"
                + "📌 Command: " + getCommand().command() + "\n"
                + "📍 Location: " + (hasLocation() ? getLocation().toString() : "No Location") + "\n"
                + "👤 Telegram User: " + getTelegramUser().toStringDetails() + "\n"
                + "👥 Telegram Group: " + (getTelegramGroup() != null ? getTelegramGroup().toStringDetails() : "No Group") + "\n"
                + "📝 Message ID: " + getMessageId() + "\n"
                + "🆕 New Chat Members: " + (hasNewChatMember() ? getNewChatMembers().toString() : "No New Members") + "\n"
                + "⏳ Is Expired: " + (isExpired() ? "Yes" : "No") + "\n"
                + "📂 File: " + (getFile() != null ? getFile().toString() : "No File Attached");
    }

}
