package com.pourist.swingy.controller;

import com.pourist.swingy.model.game.Direction;
import com.pourist.swingy.model.game.GameEngine;
import com.pourist.swingy.model.game.GameState;
import com.pourist.swingy.model.game.MoveResult;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.view.View;

import java.util.ArrayList;

public class GameController {
    private View view;
    private GameEngine engine;

    private Hero hero;

    public GameController(View  view) {
        this.view = view;
    }

    public void start() {
        hero = createHero();
        engine = new GameEngine(hero);

        gameLoop();
    }

    private gameLoop() {
        // go in loop
        while (engine.getState() != GameState.LOST) {
            if (engine.getState() == GameState.WON) {
                // ask engine to reassign map , reset engine
                // set state to playing
                continue;
            }
            // display position
            // ask for direction
            Direction direction ;
            MoveResult moveResult = engine.move(direction);
            if (moveResult == MoveResult.ENCOUNTER) {
                // ask if wants to fight or not,
                // int the engine if yes fight if not, calculate50% , if yes fight if not continue
                // simulate fight
            }
        }
    }

    private Hero createHero() {
        if (view.askLoadOrCreateHero()) {
            ArrayList<Hero> savedHeroes = LoadHeroes.load();
            view.choosHeroToLoad(savedHeroes);
        }
    }
}
