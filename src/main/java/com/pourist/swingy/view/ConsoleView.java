package com.pourist.swingy.view;

import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.model.game.Direction;
import com.pourist.swingy.model.game.FightEvent;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.hero.HeroClass;
import com.pourist.swingy.model.map.Position;
import com.pourist.swingy.model.villain.Villain;

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
                System.out.println("Hero: " + savedHeroes.get(i).getName() + " (" + (i + 1) + ")");
                System.out.println("\tExperience: " + savedHeroes.get(i).getExperience() +
                        " Hero class: " + savedHeroes.get(i).getHeroClass());
            }
            int input = scanner.nextInt();
            scanner.nextLine();
            if (input > 0 && input < savedHeroes.size() + 1)
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
            scanner.nextLine();
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
        System.out.println();
        System.out.println("===== HERO STATUS =====");
        System.out.print("Name: " + hero.getName() + "  -  ");
        System.out.println("Class: " + hero.getHeroClass());
        System.out.print("Level: " + hero.getLevel() + "  -  ");
        System.out.println("Experience: " + hero.getExperience());
        System.out.print("HP: " + hero.getHitPoints() + "/" + hero.getMaxHitPoints()+ "  -  ");
        System.out.print("Attack: " + hero.getAttack() + "  -  ");
        System.out.println("Defense: " + hero.getDefense());

        System.out.println("Weapon: " +
                (hero.getWeapon() != null
                        ? hero.getWeapon().getName() + " (+" + hero.getWeapon().getBonusValue() + ")"
                        : "none"));

        System.out.println("Armor: " +
                (hero.getArmor() != null
                        ? hero.getArmor().getName() + " (+" + hero.getArmor().getBonusValue() + ")"
                        : "none"));

        System.out.println("Helm: " +
                (hero.getHelm() != null
                        ? hero.getHelm().getName() + " (+" + hero.getHelm().getBonusValue() + ")"
                        : "none"));

        System.out.println("========================");
    }


    @Override
    public void youWon() {
        System.out.println("You reached the edge of the map!");
        System.out.println("You won this level!");
    }


    @Override
    public void heroDied(Hero hero) {
        System.out.println("💀 " + hero.getName() + " has fallen in battle.");
    }


    @Override
    public void gameOver() {
        System.out.println("===== GAME OVER =====");
    }


    @Override
    public void displayHeroPosition(Position position) {
        System.out.println("Hero Position: " + position.x() + "," + position.y());
    }

    @Override
    public void displayVillainEncounter(Villain villain) {

        System.out.println("⚔️  You encountered a villain!");
        System.out.println("--------------------------------");

        System.out.println("Name: " + villain.getName());
        System.out.println("Attack: " + villain.getAttack());
        System.out.println("Defense: " + villain.getDefense());
        System.out.println("Hit Points: " +
                villain.getHitPoints() + "/" + villain.getMaxHitPoints());

        if (villain.getEquipment() != null) {
            System.out.println("Artifact dropped on death:");
            System.out.println(" - " + villain.getEquipment().getName()
                    + " (+" + villain.getEquipment().getBonusValue() + ")");
        } else {
            System.out.println("Artifact: none");
        }

        System.out.println("--------------------------------");
    }


    @Override
    public boolean askIfWantsToFight() {
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("Fight (f)");
            System.out.println("Run away (r)");

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("f"))
                return true;   // fight

            if (input.equals("r"))
                return false;  // run

            System.out.println("Invalid choice, please enter 'f' or 'r'.");
        }
    }


    @Override
    public void youLucky() {
        System.out.println("You were lucky and managed to run away!");
    }

    @Override
    public void youUnlucky() {
        System.out.println("You failed to run away. You must fight!");
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

    @Override
    public boolean askIfWantsArtifact(Artifact artifact) {
        System.out.println("The villain dropped: " + artifact.getName() +
                " (+" + artifact.getBonusValue() + ")");
        System.out.println("Do you want to equip it? (y/n)");

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("Please enter y or n.");
        }
    }


}
