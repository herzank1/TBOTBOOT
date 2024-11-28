/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.monge.tbotboot.commands.CommandsHandlers;
import com.monge.tbotboot.messenger.Response;
import com.monge.tbotboot.objects.TelegramUser;
import com.monge.tbotboot.messenger.Xupdate;


/**
 *
 * @author DeliveryExpress
 */
public class UsersHandlers implements CommandsHandlers {

    @Override
    public void execute(Xupdate xupdate) {
    
        TelegramUser telegramUser = xupdate.getTelegramUser();
        Response.sendMessage(telegramUser, xupdate.getText(), null);
    
    }
    
}
