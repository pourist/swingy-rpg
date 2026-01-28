package com.pourist.swingy.view;

import com.pourist.swingy.model.hero.Hero;

import java.util.ArrayList;

public interface View {
    boolean askLoadOrCreateHero();

    int choosHeroToLoad(ArrayList<Hero> savedHeroes);
}
