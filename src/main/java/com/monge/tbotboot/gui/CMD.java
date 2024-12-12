/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monge.tbotboot.gui;

import com.monge.tbotboot.commands.Command;
import com.monge.tbotboot.messenger.MessageMenu;
import com.monge.tbotboot.messenger.Xupdate;
import java.util.ArrayList;

/**
 *
 * @author DeliveryExpress
 */
public abstract class CMD extends GuiItem {

    String title = "⬜⬜⬜⬜⬜⬜⬜⬜⬜[title]⬜⬜⬜⬜⬜⬜⬜⬜⬜";
    String bottom = "⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜";
    ArrayList<String> lines = new ArrayList<>();
    String root = "C:\\@DeliveryExpress>";
    private int max_lines = 20;

    public CMD(GuiElement parent, String title) {
        super(parent, title);
        this.title = this.title.replace("[title]", title);
        lines.add(root);
    }

    @Override
    public void execute(Xupdate xupdate) {
        String data =  xupdate.getText();
        if (lines.size() == max_lines) {
            lines.remove(0);
        }
        /*agregamos e input*/
        updateLastLine(data);
        lines.add(executeCmd(new Command(data, " ")));
        lines.add(root);
    }

    private void updateLastLine(String data) {

        // Obtén el índice del último elemento
        int lastIndex = lines.size() - 1;

        // Obtén el texto actual del último elemento y agrégale el nuevo texto
        String updatedText = lines.get(lastIndex) + data;

        // Actualiza el último elemento en el ArrayList
        lines.set(lastIndex, updatedText);

    }

    public abstract String executeCmd(Command command);

    @Override
    public MessageMenu getMenu() {
        return new MessageMenu();
    }

    @Override
    public String draw() {
        return drawWindow();
    }

    private String drawWindow() {
        return this.title
                + "\n\n"
                + String.join("\n", this.lines)
                + "\n\n"
                + this.bottom;

    }

}
