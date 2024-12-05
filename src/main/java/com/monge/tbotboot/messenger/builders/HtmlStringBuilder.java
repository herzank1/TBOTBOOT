/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.messenger.builders;

public class HtmlStringBuilder {

    private final StringBuilder textBuilder = new StringBuilder();

    public HtmlStringBuilder() {
    }

    // Método para append un salto de línea <br>
    public HtmlStringBuilder appendNewline() {
        textBuilder.append("\n");
        return this;
    }

    // Método para agregar texto común (sin etiquetas HTML)
    public HtmlStringBuilder append(String text) {
       textBuilder.append(escapeInvalids(text));
       return this;

    }

    // Método para append de texto en negrita
    public HtmlStringBuilder appendBold(String text) {
        textBuilder.append(formatText(Entities.BOLD, text));
        return this;
    }

    // Método para append de texto en cursiva
    public HtmlStringBuilder appendItalic(String text) {
        textBuilder.append(formatText(Entities.ITALIC, text));
        return this;
    }

    // Método para append de texto subrayado
    public HtmlStringBuilder appendUnderline(String text) {
        textBuilder.append(formatText(Entities.UNDERLINE, text));
        return this;
    }

    // Método para append de texto tachado
    public HtmlStringBuilder appendStrikethrough(String text) {
        textBuilder.append(formatText(Entities.STRIKETHROUGH, text));
        return this;
    }

    // Método para append de texto en código
    public HtmlStringBuilder appendCode(String text) {
        textBuilder.append(formatText(Entities.CODE, text));
        return this;
    }

    // Método para append de texto preformateado
    public HtmlStringBuilder appendPre(String text) {
        textBuilder.append(formatText(Entities.PRE, text));
        return this;
    }

    // Método para append de un enlace
    public HtmlStringBuilder appendLink(String text, String url) {
        textBuilder.append(formatLinkText(text, url));
        return this;
    }

    // Método para append una mención de usuario (formato de Telegram)
    public HtmlStringBuilder appendUserMention(String username, String userId) {
        String mention = "<a href=\"tg://user?id=" + userId + "\">" + username + "</a>";
        textBuilder.append(mention);
        return this;
    }

    // Método para obtener el contenido final en formato HTML
    public String build() {
        return textBuilder.toString();
    }

    // Función que aplica la entidad HTML seleccionada al texto
    private String formatText(String entity, String value) {
        return entity.replace("#value#", escapeInvalids(value));
    }

    // Función para las entidades con URL (en el caso de Link)
    public String formatLinkText(String value, String url) {
        return Entities.LINK.replace("#value#", escapeInvalids(value)).replace("#url#", url);
    }

  public static String escapeInvalids(String input) {
    if (input == null) return null;

    // Crear un StringBuilder para construir el resultado final
    StringBuilder escapedString = new StringBuilder();

    // Iterar sobre cada carácter del texto
    for (char c : input.toCharArray()) {
        // Escapar caracteres especiales usando switch
        switch (c) {
            case '&': 
                escapedString.append("&amp;");
                break;
            case '<':
                escapedString.append("&lt;");
                break;
            case '>':
                escapedString.append("&gt;");
                break;
            case '"': 
                escapedString.append("&quot;");
                break;
            case '\'': 
                escapedString.append("&#39;");
                break;
            case '\\': 
                escapedString.append("\\\\");
                break;
            case '_': 
                escapedString.append("\\_");
                break;
            case '*': 
                escapedString.append("\\*");
                break;
            case '[': 
                escapedString.append("\\[");
                break;
            case ']': 
                escapedString.append("\\]");
                break;
            case '(': 
                escapedString.append("\\(");
                break;
            case ')': 
                escapedString.append("\\)");
                break;
            case '~': 
                escapedString.append("\\~");
                break;
            case '`': 
                escapedString.append("\\`");
                break;
            case '#': 
                escapedString.append("\\#");
                break;
            case '+': 
                escapedString.append("\\+");
                break;
                /*
            case '-': 
             escapedString.append("\\-");
                break;
*/
            case '=': 
                escapedString.append("\\=");
                break;
            case '|': 
                escapedString.append("\\|");
                break;
            case '{': 
                escapedString.append("\\{");
                break;
            case '}': 
                escapedString.append("\\}");
                break;
            case '.': 
               // escapedString.append("\\.");
                break;
            case '!': 
                escapedString.append("\\!");
                break;
            default: 
                escapedString.append(c);
                break;
        }
    }

    return escapedString.toString();
}

    // Entidades HTML como constantes estáticas
    public static class Entities {

        public static final String NONE = "";
        public static final String BOLD = "<b>#value#</b>";
        public static final String ITALIC = "<i>#value#</i>";
        public static final String UNDERLINE = "<u>#value#</u>";
        public static final String STRIKETHROUGH = "<s>#value#</s>";
        public static final String CODE = "<code>#value#</code>";
        public static final String PRE = "<pre>#value#</pre>";
        public static final String LINK = "<a href=\"#url#\">#value#</a>";
       // public static final String NEWLINE = "<br>";  // Nueva entidad para salto de línea
    }
}
