
package com.monge.tbotboot.quizes;



import com.monge.tbotboot.messenger.Response;
import com.monge.tbotboot.messenger.Xupdate;
import com.monge.tbotboot.objects.Receptor;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DeliveryExpress
 */
public class QuizesControl {

    static Map<String, Quiz> quizes = new HashMap<>();

    public static boolean hasQuiz(String userId) {

        return quizes.containsKey(userId);
    }

    public static void add(Quiz e) {
        quizes.put(e.getUserId(), e);

    }

    public static Quiz getQuiz(String userId) {
        return quizes.get(userId);
    }

    public static void execute(Xupdate xupdate) {

        Quiz quiz = getQuiz(xupdate.getSenderId());
      
        if (quiz != null&&!xupdate.getCommand().isCommand()) {

            switch (xupdate.getText()) {

                case "/close":
                case "/cerrar":
                case "/salir":
                case "/exit":
                case "/esc":

                    quiz.destroy();
                    Response.sendMessage(xupdate.getTelegramUser(), "Proceso finalizado!", null);

                    break;

                default:
                    quiz.execute(xupdate);
                    break;

            }

        } else {
            System.out.println("No se encontro questionario o estas usando un //comando en un questionario!");

        }

    }

    static void destroy(Quiz aThis) {
        quizes.remove(aThis.getUserId());

    }

    public static void sendAlreadyInProcessMsg(Receptor to) {
        Response.sendMessage(to, "Ya tiene un proceso abierto, continue o ingrese /salir", null);

    }

}
