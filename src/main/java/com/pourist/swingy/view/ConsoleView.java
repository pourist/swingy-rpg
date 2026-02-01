package com.pourist.swingy.view;

import com.pourist.swingy.model.game.Direction;
import com.pourist.swingy.model.game.FightEvent;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.hero.HeroClass;
import com.pourist.swingy.model.map.Position;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {

    private final Scanner scanner = new Scanner(System.in);

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
    public int choosHeroToLoad(List<Hero> savedHeroes) {
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

    @Override
    public String askHeroName() {
        while (true) {
            System.out.println("*- Creating new Hero -*");
            System.out.println("Enter Name: ");
            String input = scanner.nextLine().trim();
            if(input.length() < 8)
                return input;
            System.out.println("Name is too long - max 8 character.");
        }
    }

    @Override
    public HeroClass askHeroClass() {
        while (true) {
            System.out.println("Choose Hero Class:");
            System.out.println("Warrior (1)");
            System.out.println("Rogue (2)");
            System.out.println("Mage (3)");

            int input = scanner.nextInt();

            switch (input) {
                case 1:
                    return HeroClass.WARRIOR;
                case 2:
                    return HeroClass.ROGUE;
                case 3:
                    return HeroClass.MAGE;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    @Override
    public void displayGameState(Hero hero) {

    }

    @Override
    public void youWon() {
        System.out.println("You reached the bound. won!");
        System.out.println("You won!");
    }

    @Override
    public void heroDied(Hero hero) {
        System.out.println(hero.getName() + " died" );
    }

    @Override
    public void gameOver() {
        System.out.println("Game Over!");
    }

    @Override
    public void displayHeroPosition(Position position) {
        System.out.println("Hero Position: " + position.x() + "," + position.y());
    }

    @Override
    public Direction askDirection() {
        while (true) {
            System.out.println("Choose direction to move:");
            System.out.println("North (n)");
            System.out.println("East (e)");
            System.out.println("South (S)");
            System.out.println("West (w)");

            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "w":
                    return Direction.WEST;
                case "e":
                    return Direction.EAST;
                case "n":
                    return Direction.NORTH;
                case "s":
                    return Direction.SOUTH;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    @Override
    public void displayFightEvent(FightEvent event) {

        System.out.println(
                event.attacker() + " hits " +
                        event.defender() + " for " +
                        event.damage() + " damage."
        );

        System.out.println(
                event.attacker() + " HP: " + event.attackerHp() +
                        " | " +
                        event.defender() + " HP: " + event.defenderHp()
        );

        if (event.defenderDied()) {
            System.out.println(event.defender() + " has died!");
        }

        System.out.println("--------------------------------");
    }

}
