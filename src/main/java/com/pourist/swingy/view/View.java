package com.pourist.swingy.view;

import com.pourist.swingy.model.hero.Hero;
import java.util.List;

public interface View {

    boolean askLoadOrCreateHero();

    int choosHeroToLoad(List<Hero> savedHeroes);
}
