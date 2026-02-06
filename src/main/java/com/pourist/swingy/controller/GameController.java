package com.pourist.swingy.controller;

import com.pourist.swingy.application.GameService;
import com.pourist.swingy.model.game.*;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.hero.HeroClass;
import com.pourist.swingy.persistence.HeroRepository;
import com.pourist.swingy.view.ConsoleView;
import com.pourist.swingy.view.GuiView;
import com.pourist.swingy.view.View;
import com.pourist.swingy.view.ViewProvider;

import java.util.List;

public class GameController {

    public enum GameEvent {
        CONTINUE,
        END_GAME,
        SWITCH_TO_GUI,
        SWITCH_TO_CONSOLE
    }

    private final ViewProvider viewProvider;
    private final HeroRepository heroRepository;
    private final GameService gameService;

    public GameController(ViewProvider viewProvider,
                          HeroRepository heroRepository) {
        this.viewProvider = viewProvider;
        this.heroRepository = heroRepository;
        this.gameService = new GameService(heroRepository);
    }

    public void start() {
        Hero hero = createHero();
        gameService.startGame(hero);
    }

    public GameEvent tick() {

        View view = viewProvider.get();

        switch (gameService.getState()) {

            case WON -> {
                gameService.handleWin();
                view.youWon();
                return GameEvent.CONTINUE;
            }

            case LOST -> {
                gameService.handleLoss();
                view.gameOver();
                return GameEvent.END_GAME;
            }

            case PLAYING -> {
                return handleTurn(view);
            }
        }
        return GameEvent.END_GAME;
    }

    private GameEvent handleTurn(View view) {

        Hero hero = gameService.getHero();

        view.displayGameState(hero);
        view.displayHeroPosition(hero.getPosition());

        MoveResult result;
        while (true) {
            view.directionMenu();
            InputCommand command = view.askCommand();
            switch (command) {

                case SWITCH_TO_GUI:
                    return GameEvent.SWITCH_TO_GUI;

                case SWITCH_TO_CONSOLE:
                    return GameEvent.SWITCH_TO_CONSOLE;

                case MOVE_NORTH:
                    result = gameService.move(Direction.NORTH);
                    break;

                case MOVE_SOUTH:
                    result = gameService.move(Direction.SOUTH);
                    break;

                case MOVE_EAST:
                    result = gameService.move(Direction.EAST);
                    break;

                case MOVE_WEST:
                    result = gameService.move(Direction.WEST);
                    break;
                default:
                    view.invalidInput();
                    continue;
            }
            break;
        }

        if (result == MoveResult.ENCOUNTER) {
            handleEncounter(view);
        }

        return GameEvent.CONTINUE;
    }

    private GameEvent handleEncounter(View view) {

        view.displayVillainEncounter(gameService.getCurrentVillain());

        while (true) {
            view.askIfWantsToFight();
            InputCommand command = view.askCommand();

            switch (command) {

                case RUN:
                    if (gameService.tryToRunAway()) {
                        view.youLucky();
                        gameService.moveBack();
                        return GameEvent.CONTINUE;
                    }
                    view.youUnlucky();
                    return handleFight(view);

                case FIGHT:
                    return handleFight(view);

                default:
                    view.invalidInput();
            }
        }
    }


    private GameEvent handleFight(View view) {

        gameService.beginFight();

        while (gameService.isFighting()) {

            FightEvent event = gameService.fightStep();

            view.displayFightEvent(event);

            if (event.defenderDied() && event.droppedArtifact() != null) {

                while (true) { ///  Have issue at this stage
                    view.askIfWantsArtifact(gameService.getCurrentVillain().getEquipment());
                    InputCommand command = view.askCommand();

                    switch (command) {

                        case SWITCH_TO_GUI:
                            return GameEvent.SWITCH_TO_GUI;

                        case SWITCH_TO_CONSOLE:
                            return GameEvent.SWITCH_TO_CONSOLE;

                        case TAKE_ARTIFACT:
                            gameService.equipArtifact(event.droppedArtifact());
                            break;

                        case SKIP_ARTIFACT:
                            break;

                        default:
                            continue;
                    }

                    break;
                }
            }
        }

        return GameEvent.CONTINUE;
    }


    private Hero createHero() {

        View view = viewProvider.get();

        List<Hero> savedHeroes = heroRepository.loadAll();

        while (true) {

            HeroCreationCommand command =
                    view.askHeroCreationCommand();

            switch (command) {

                case SWITCH_TO_GUI:
                    viewProvider.set(new GuiView());
                    view = viewProvider.get();
                    continue;

                case SWITCH_TO_CONSOLE:
                    viewProvider.set(new ConsoleView());
                    view = viewProvider.get();
                    continue;

                case LOAD_HERO:
                    int index = view.chooseHeroToLoad(savedHeroes) - 1;
                    return savedHeroes.get(index);

                case CREATE_HERO:
                    String name = view.askHeroName();
                    HeroClass heroClass = view.askHeroClass();

                    Hero hero = new Hero.Builder()
                            .withName(name)
                            .withHeroClass(heroClass)
                            .build();

                    heroRepository.save(hero);
                    return hero;
            }
        }
    }

}
