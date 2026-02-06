package com.pourist.swingy;

import com.pourist.swingy.controller.GameController;
import com.pourist.swingy.controller.GameController.GameEvent;
import com.pourist.swingy.persistence.DatabaseHeroRepository;
import com.pourist.swingy.persistence.DatabaseManager;
import com.pourist.swingy.persistence.HeroRepository;
import com.pourist.swingy.view.ConsoleView;
import com.pourist.swingy.view.GuiView;
import com.pourist.swingy.view.View;
import com.pourist.swingy.view.ViewProvider;

public class Main {

    public static void main(String[] args) {

        try {
            DatabaseManager.init();

            HeroRepository heroRepository = new DatabaseHeroRepository();

            ViewProvider viewProvider =
                    new ViewProvider(createView(args));

            GameController controller =
                    new GameController(viewProvider, heroRepository);

            controller.start();

            boolean running = true;

            while (running) {

                GameEvent event = controller.tick();

                switch (event) {

                    case CONTINUE -> {
                        continue;
                    }

                    case SWITCH_TO_GUI -> {
                        viewProvider.set(new GuiView());
                    }

                    case SWITCH_TO_CONSOLE -> {
                        viewProvider.set(new ConsoleView());
                    }

                    case END_GAME -> {
                        running = false;
                    }
                }
            }

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
