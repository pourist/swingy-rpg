package com.pourist.swingy.controller;

import com.pourist.swingy.model.game.*;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.hero.HeroClass;
import com.pourist.swingy.persistence.HeroRepository;
import com.pourist.swingy.view.View;

import java.util.List;

public class GameController {

    private final View view;
    private final HeroRepository heroRepository;

    private GameEngine engine;
    private Hero hero;

    public GameController(View view, HeroRepository heroRepository) {
        this.view = view;
        this.heroRepository = heroRepository;
    }

    public void start() {
        createHero();
        engine = new GameEngine(hero);
        gameLoop();
    }

    private void gameLoop() {
        while (true) {
            switch (engine.getState()) {
                case WON -> handleWin();
                case LOST -> {
                    handleLoss();
                    return;
                }
                case PLAYING -> handleTurn();
            }
        }
    }

    private void handleTurn() {
        view.displayGameState(hero);
        view.displayHeroPosition(hero.getPosition());

        MoveResult result = heroMoves();
        if (result == MoveResult.ENCOUNTER) {
            handleEncounter();
        }
    }

    private MoveResult heroMoves() {
        Direction direction = view.askDirection();
        return engine.move(direction);
    }

    private void handleEncounter() {
        if (view.fightOrRunAway()) {
            if (engine.tryToRunAway()) {
                view.youLucky();
                engine.moveBack();
            } else {
                view.youUnlucky();
                handleFight();
            }
        } else {
            handleFight();
        }
    }

    private void handleFight() {
        engine.setFighting(true);
        while (engine.isFighting()) {
            FightEvent event = engine.fight();
            view.displayFightEvent(event);
        }
    }


    private void handleWin() {
        heroRepository.save(hero);
        view.youWon();
        engine.createNextLevelMap();
    }

    private void handleLoss() {
        heroRepository.save(hero);
        view.gameOver();
    }


    private void createHero() {
        List<Hero> savedHeroes = heroRepository.loadAll();

        if (!savedHeroes.isEmpty() && view.askLoadOrCreateHero()) {
            int index = view.choosHeroToLoad(savedHeroes) - 1;
            hero = savedHeroes.get(index);
        } else {
            String name = view.askHeroName();
            HeroClass heroClass = view.askHeroClass();
            hero = new Hero.Builder()
                    .withName(name)
                    .withHeroClass(heroClass)
                    .build();
            heroRepository.save(hero);
        }
    }
}
