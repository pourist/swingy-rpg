package com.pourist.swingy;

import com.pourist.swingy.controller.GameController;
import com.pourist.swingy.view.ConsoleView;
import com.pourist.swingy.view.GuiView;
import com.pourist.swingy.view.View;

public class Main {
    public static void main(String[] args) {
        try {

            View view;

            if (args.length == 0 || args[0].equalsIgnoreCase("console")) {
                view = new ConsoleView();
            } else if (args[0].equalsIgnoreCase("gui")) {
                view = new GuiView();
            } else {
                throw new IllegalArgumentException("Usage: console | gui");
            }

            GameController controller = new GameController(view);


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
