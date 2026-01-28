package com.pourist.swingy.model.game;

import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.map.GameMap;
import com.pourist.swingy.model.map.MapFactory;
import com.pourist.swingy.model.map.Position;

public class GameEngine {
    private final Hero hero;
    private GameMap gameMap;
    private GameState state;

    public GameEngine(Hero hero) {
        this.hero = hero;
        setUpMap();
        this.state = GameState.PLAYING;
    }

    public MoveResult move(Direction direction) {
        Position next = nextPosition(direction);

        if (gameMap.isBound(next)) {
            hero.setPosition(next);
            state = GameState.WON;
            return MoveResult.WON;
        }

        if (gameMap.hasVillain(next)) {
            hero.setPosition(next);
            return MoveResult.ENCOUNTER;
        }

        hero.setPosition(next);
        return MoveResult.NORMAL;
    }

    private Position nextPosition(Direction directionType) {
        int x = hero.getPosition().getX();
        int y = hero.getPosition().getY();

        switch (directionType) {
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
    }
}
