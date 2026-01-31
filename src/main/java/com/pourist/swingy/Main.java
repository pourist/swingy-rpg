package com.pourist.swingy;

import com.pourist.swingy.controller.GameController;
import com.pourist.swingy.persistence.DatabaseHeroRepository;
import com.pourist.swingy.persistence.DatabaseManager;
import com.pourist.swingy.persistence.HeroRepository;
import com.pourist.swingy.view.ConsoleView;
import com.pourist.swingy.view.GuiView;
import com.pourist.swingy.view.View;

public class Main {

    public static void main(String[] args) {

        try {
            DatabaseManager.init();

            HeroRepository heroRepository = new DatabaseHeroRepository();

            View view = createView(args);

            GameController controller =
                    new GameController(view, heroRepository);

            controller.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static View createView(String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("console")) {
            return new ConsoleView();
        }
        if (args[0].equalsIgnoreCase("gui")) {
            return new GuiView();
        }
        throw new IllegalArgumentException("Usage: console | gui");
    }
}
