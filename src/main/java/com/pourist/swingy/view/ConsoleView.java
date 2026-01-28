package com.pourist.swingy.view;

import com.pourist.swingy.model.hero.Hero;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView implements View {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public boolean askLoadOrCreateHero() {
        while (true) {
            System.out.println("Create a new Hero (1)");
            System.out.println("Load a saved Hero (2)");

            String input = scanner.nextLine().trim();

            if (input.equals("1")) return false; // create
            if (input.equals("2")) return true;  // load

            System.out.println("Invalid choice, try again.");
        }
    }

    @Override
    public int choosHeroToLoad(ArrayList<Hero> savedHeroes) {
        while (true) {
            System.out.println("Choose your Hero:");
            for (int i = 0; i < savedHeroes.size(); i++) {
                System.out.println("Hero: " + savedHeroes.get(i).getName() + " (" + i + ")");
                System.out.println("\tExperience: " + savedHeroes.get(i).getExperience() +
                        " Hero class: " + savedHeroes.get(i).getHeroClass());
            }
            int input = scanner.nextInt();
            if (input > 0 && input < savedHeroes.size())
                return input;
            System.out.println("Invalid choice, try again.");
        }
    }

}
