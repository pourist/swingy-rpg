package com.pourist.swingy.application;

import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.model.game.*;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.villain.Villain;
import com.pourist.swingy.persistence.HeroRepository;

public class GameService {

    private final HeroRepository heroRepository;

    private GameEngine engine;
    private Hero hero;

    public GameService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public void startGame(Hero hero) {
        this.hero = hero;
        this.engine = new GameEngine(hero);
    }

    public GameState getState() {
        return engine.getState();
    }

    public Hero getHero() {
        return hero;
    }

    public MoveResult move(Direction direction) {
        return engine.move(direction);
    }

    public Villain getCurrentVillain() {
        return engine.getCurrentVillain();
    }

    public boolean tryToRunAway() {
        return engine.tryToRunAway();
    }

    public void moveBack() {
        engine.moveBack();
    }

    public void beginFight() {
        engine.setFighting(true);
    }

    public boolean isFighting() {
        return engine.isFighting();
    }

    public FightEvent fightStep() {
        return engine.fight();
    }

    public void equipArtifact(Artifact artifact) {
        hero.equip(artifact);
    }

    public void handleWin() {
        heroRepository.save(hero);
        engine.createNextLevelMap();
    }

    public void handleLoss() {
        heroRepository.save(hero);
    }
}
