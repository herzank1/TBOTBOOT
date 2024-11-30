
import com.monge.tbotboot.messenger.Bot;
import com.monge.tbotboot.messenger.BotsHandler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author DeliveryExpress
 */
public class TelegramBotFramework {

    public static void main(String[] args) {
        
        
        BotsHandler.init(new UsersHandlers(),new Bot("@xeye_1_bot"
                ,"7582619986:AAGZOTcf4tzEFDL-OaGpoQN1oU3dggrUao4"
                ));
    }
}
