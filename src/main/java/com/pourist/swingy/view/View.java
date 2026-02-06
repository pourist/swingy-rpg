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

public interface View {

    HeroCreationCommand askHeroCreationCommand();

    int chooseHeroToLoad(List<Hero> savedHeroes);

    String askHeroName();
    HeroClass askHeroClass();

    InputCommand askCommand();

    void displayGameState(Hero hero);

    void youWon();

    void gameOver();

    void heroDied(Hero hero);

    void displayHeroPosition(Position position);

    void askIfWantsToFight();

    void youLucky();

    void youUnlucky();

    void displayFightEvent(FightEvent result);

    void displayVillainEncounter(Villain villain) ;

    void askIfWantsArtifact(Artifact artifact);

    void    invalidInput();

    void directionMenu();
}
