package com.pourist.swingy.persistence;

import com.pourist.swingy.model.hero.Hero;

import java.util.List;

public class FileHeroRepository implements HeroRepository {

    @Override
    public List<Hero> loadAll() {

        return null;
    }

    @Override
    public void save(Hero hero) {
        // write hero to text file
    }
}

