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
        this.params = data.split(COMMAND_SEPARATOR);

    }

    /**
     * *
     * Crea un comando splitiando el texto con otro splitter
     *
     * @param data
     * @param splitter
     */
    public Command(String data, String splitter) {
        this.data = data;
        this.params = data.split(splitter);

    }

    /**
     * *
     *
     * @return all data
     */
    public String getData() {
        return data;
    }

    /**
     * *
     *
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Obtiene el parámetro en el índice dado y lo convierte en un booleano.
     *
     * @param index El índice del parámetro en el array.
     * @return El parámetro como un booleano, o null si la conversión falla.
     */
    public Boolean getParamAsBoolean(int index) {
        try {
            String param = getParam(index);  // Obtenemos el parámetro como String
            if (param != null) {
                return Boolean.parseBoolean(param);  // Intentamos convertirlo a booleano
            }
        } catch (Exception e) {
            // Log o manejo del error si es necesario
        }
        return null;  // Retorna null en caso de error o si el valor no puede ser convertido
    }

    /**
     * Obtiene el parámetro en el índice dado y lo convierte en un Integer.
     *
     * @param index El índice del parámetro en el array.
     * @return El parámetro como un Integer, o null si la conversión falla.
     */
    public Integer getParamAsInteger(int index) {
        try {
            String param = getParam(index);  // Obtenemos el parámetro como String
            if (param != null) {
                return Integer.parseInt(param);  // Intentamos convertirlo a Integer
            }
        } catch (Exception e) {
            // Log o manejo del error si es necesario
        }
        return null;  // Retorna null si la conversión falla o si el valor no puede ser convertido
    }

    /**
     * Obtiene el parámetro en el índice dado y lo convierte en un Float.
     *
     * @param index El índice del parámetro en el array.
     * @return El parámetro como un Float, o null si la conversión falla.
     */
    public Float getParamAsFloat(int index) {
        try {
            String param = getParam(index);  // Obtenemos el parámetro como String
            if (param != null) {
                return Float.parseFloat(param);  // Intentamos convertirlo a Float
            }
        } catch (Exception e) {
            // Log o manejo del error si es necesario
        }
        return null;  // Retorna null si la conversión falla o si el valor no puede ser convertido
    }

    /**
     * Obtiene el parámetro en el índice dado y lo convierte en un Double.
     *
     * @param index El índice del parámetro en el array.
     * @return El parámetro como un Double, o null si la conversión falla.
     */
    public Double getParamAsDouble(int index) {
        try {
            String param = getParam(index);  // Obtenemos el parámetro como String
            if (param != null) {
                return Double.parseDouble(param);  // Intentamos convertirlo a Double
            }
        } catch (Exception e) {
            // Log o manejo del error si es necesario
        }
        return null;  // Retorna null si la conversión falla o si el valor no puede ser convertido
    }

    /**
     * Obtiene el parámetro en el índice dado y lo convierte en un Long.
     *
     * @param index El índice del parámetro en el array.
     * @return El parámetro como un Long, o null si la conversión falla.
     */
    public Long getParamAsLong(int index) {
        try {
            String param = getParam(index);  // Obtenemos el parámetro como String
            if (param != null) {
                return Long.parseLong(param);  // Intentamos convertirlo a Long
            }
        } catch (Exception e) {
            // Log o manejo del error si es necesario
        }
        return null;  // Retorna null si la conversión falla o si el valor no puede ser convertido
    }

    /**
     * Obtiene el parámetro en el índice dado como un String.
     *
     * @param index El índice del parámetro en el array.
     * @return El parámetro como un String, o null si el índice es inválido o el
     * valor es nulo.
     */
    public String getParam(int index) {
        try {
            return params[index];  // Intentamos acceder al parámetro en el índice dado
        } catch (Exception e) {
            // Si ocurre un error (por ejemplo, índice fuera de rango), retornamos null
        }
        return null;  // Retorna null si el índice es inválido o hay un error
    }

    /**
     * *
     *
     * @return first param (0) as command String
     */
    public String command() {

        if (getParam(0) == null || getParam(0).isEmpty()) {
            return this.data;
        } else {
            return getParam(0);
        }

    }

    /**
     * *
     *
     * @return if is TelegramCommand /start
     */
    public boolean isCommand() {
        return this.data.startsWith("//");
    }

    @Override
    public String toString() {
        return this.data;
    }

}
