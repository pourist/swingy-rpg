package com.pourist.swingy.view;

import com.pourist.swingy.model.game.Direction;
import com.pourist.swingy.model.game.FightEvent;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.hero.HeroClass;
import com.pourist.swingy.model.map.Position;

import java.util.List;

public interface View {

    boolean askLoadOrCreateHero();

    int choosHeroToLoad(List<Hero> savedHeroes);

    String askHeroName();
    HeroClass askHeroClass();

    Direction askDirection();

    void displayGameState(Hero hero);

    void youWon();

    void gameOver();

    void heroDied(Hero hero);

    void displayHeroPosition(Position position);

    boolean fightOrRunAway();

    void youLucky();

    void youUnlucky();

    void displayFightEvent(FightEvent result);
}
