package com.pourist.swingy.model.game;

import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.map.GameMap;
import com.pourist.swingy.model.map.MapFactory;
import com.pourist.swingy.model.map.Position;
import com.pourist.swingy.model.villain.Villain;

import java.util.Random;

public class GameEngine {
    private final Hero hero;
    private GameMap gameMap;
    private GameState state;
    private boolean fighting;
    private final Random random = new Random();
    private Villain currentVillain;

    public GameEngine(Hero hero) {
        this.hero = hero;
        setUpMap();
        this.state = GameState.PLAYING;
    }

    public MoveResult move(Direction direction) {
        Position next = nextPosition(direction);

        if (gameMap.isBound(next)) {
            hero.setPreviousPosition();
            hero.setPosition(next);
            state = GameState.WON;
            return MoveResult.WON;
        }

        if (gameMap.hasVillain(next)) {
            currentVillain = gameMap.getVillainAt(next);
            hero.setPreviousPosition();
            hero.setPosition(next);
            return MoveResult.ENCOUNTER;
        }

        hero.setPreviousPosition();
        hero.setPosition(next);
        return MoveResult.NORMAL;
    }

    private Position nextPosition(Direction direction) {
        int x = hero.getPosition().x();
        int y = hero.getPosition().y();

        switch (direction) {
            case NORTH:
                y--;
                break;
            case SOUTH:
                y++;
                break;
            case WEST:
                x--;
                break;
            case EAST:
                x++;
                break;
        }
        return new Position(x, y);
    }

    public Hero getHero() {
        return hero;
    }

    public GameState getState() {
        return state;
    }

    public void setUpMap(){
        this.gameMap = MapFactory.createMap(hero.getLevel());
        hero.setPosition(gameMap.getCenterOfTheMap());
    }

    public void createNextLevelMap () {
        setUpMap();
        this.state = GameState.PLAYING;
    }

    public boolean tryToRunAway() {
        return random.nextBoolean();
    }

    public void moveBack() {
        hero.setPosition(hero.getPreviousPosition());
    }

    public boolean isFighting() {
        return fighting;
    }

    public void setFighting(boolean fightingState) {
        fighting = fightingState;
    }

    public Villain getCurrentVillain() {
        return currentVillain;
    }


    public FightEvent fight() {

        Villain villain = gameMap.getVillainAt(hero.getPosition());
        fighting = true;

        // --- Compute attack probabilities ---
        int heroPower = hero.getAttack() + hero.getDefense();
        int villainPower = villain.getAttack() + villain.getDefense();
        int totalPower = heroPower + villainPower;

        boolean heroAttacks =
                random.nextInt(totalPower) < heroPower;

        if (heroAttacks) {
            // HERO ATTACKS
            int damage = Math.max(
                    0,
                    hero.getAttack() - villain.getDefense() + random.nextInt(3)
            );
            villain.takeDamage(damage);

            boolean villainDied = !villain.isAlive();

            if (villainDied) {
                hero.addExperience(villain.getExperienceReward());

                Artifact dropped = villain.getEquipment();

                currentVillain = null;
                gameMap.removeVillainAt(hero.getPosition());
                fighting = false;

                return new FightEvent(
                        hero.getName(),
                        villain.getName(),
                        damage,
                        hero.getHitPoints(),
                        0,
                        true,
                        true,
                        dropped
                );
            }


            return new FightEvent(
                    hero.getName(),
                    villain.getName(),
                    damage,
                    hero.getHitPoints(),
                    Math.max(0, villain.getHitPoints()),
                    villainDied,
                    villainDied,
                    null
            );
        }

        // VILLAIN ATTACKS
        int damage = Math.max(
                0,
                villain.getAttack() - hero.getDefense() + random.nextInt(3)
        );
        hero.takeDamage(damage);

        boolean heroDied = !hero.isAlive();
        if (heroDied) {
            state = GameState.LOST;
            fighting = false;
        }

        return new FightEvent(
                villain.getName(),
                hero.getName(),
                damage,
                Math.max(0, villain.getHitPoints()),
                hero.getHitPoints(),
                heroDied,
                heroDied,
                null
        );
    }



}
