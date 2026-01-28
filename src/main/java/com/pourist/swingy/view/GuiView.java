package com.pourist.swingy.view;

import com.pourist.swingy.model.hero.Hero;

import java.util.ArrayList;

public class GuiView implements View {

    @Override
    public boolean askLoadOrCreateHero() {
        return false;
    }

    @Override
    public int choosHeroToLoad(ArrayList<Hero> savedHeroes) {
        return 1;
    }
}
