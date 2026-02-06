package com.pourist.swingy.view;

import com.pourist.swingy.controller.HeroCreationCommand;
import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.controller.InputCommand;
import com.pourist.swingy.model.game.FightEvent;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.hero.HeroClass;
import com.pourist.swingy.model.map.Position;
import com.pourist.swingy.model.villain.Villain;

import java.util.List;

public class GuiView implements View {

    @Override
    public HeroCreationCommand askHeroCreationCommand() {
        return null;
    }

    @Override
    public int chooseHeroToLoad(List<Hero> savedHeroes) {
        return 1;
    }

    @Override
    public String askHeroName() {
        return "Mamad";
    }

    @Override
    public HeroClass askHeroClass() {
        return null;
    }

    @Override
    public void displayGameState(Hero hero) {

    }



    @Override
    public void youWon() {

    }

    @Override
    public void heroDied(Hero hero) {
    }

    @Override
    public void gameOver() {
    }

    @Override
    public void displayHeroPosition(Position position) {
    }

    @Override
    public void askIfWantsToFight() {

    }

    @Override
    public void youLucky() {

    }

    @Override
    public void youUnlucky() {

    }

    @Override
    public InputCommand askCommand() {
        return null;
    }

    @Override
    public void displayFightEvent(FightEvent event) {
    }

    @Override
    public void displayVillainEncounter(Villain villain) {

    }

    @Override
    public void askIfWantsArtifact(Artifact artifact) {

    }

    @Override
    public void    invalidInput() {

    }

    @Override
    public void directionMenu() {}
}
