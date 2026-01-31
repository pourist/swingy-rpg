package com.pourist.swingy.persistence;

import com.pourist.swingy.model.hero.Hero;

import java.util.List;

public interface HeroRepository {
    List<Hero> loadAll();
    void save(Hero hero);
    void remove(Hero hero);
}
