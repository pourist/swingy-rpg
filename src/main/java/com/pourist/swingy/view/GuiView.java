package com.pourist.swingy.view;

import com.pourist.swingy.model.hero.Hero;

import java.util.List;

public class GuiView implements View {

    @Override
    public boolean askLoadOrCreateHero() {
        return false;
    }

    @Override
    public int choosHeroToLoad(List<Hero> savedHeroes) {
        return 1;
    }
}
