
package com.monge.tbotboot.commands;


/**
 *
 * @author DeliveryExpress Dont use cammelCase instead use _ use always / in
 * starting string commands examples delete_msg
 */

public class Command {

    String data;
    String[] params;

    public static final String COMMAND_SEPARATOR = "&";

    public Command(String data) {
        this.data = data;
        if (data.startsWith("/")) {
            this.params = data.split(COMMAND_SEPARATOR);
        }

    }
    
    /***
     * Crea un comando splitiando el texto con otro splitter
     * @param data
     * @param splitter 
     */
     public Command(String data,String splitter) {
        this.data = data;
        if (data.startsWith("/")) {
            this.params = data.split(splitter);
        }

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    

    /**
     * *
     *
     * @param index
     * @return
     */
    public String getParam(int index) {

        try {

            return params[index];

        } catch (Exception e) {
            return null;
        }

    }

    public String command() {

        if (getParam(0)==null||getParam(0).isEmpty()) {
            return this.data;
        } else {
            return getParam(0);
        }

    }

    public boolean isCommand() {
        return this.data.startsWith("//");
    }

}
