package com.pourist.swingy.model.hero;

import com.pourist.swingy.model.artifact.Armor;
import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.model.artifact.Helm;
import com.pourist.swingy.model.artifact.Weapon;
import com.pourist.swingy.model.map.Position;

public class Hero {
    private final String    name;
    private final HeroClass heroClass;

    private int level;
    private int experience;

    private Position    position;
    private Weapon  weapon;
    private Armor   armor;
    private Helm    helm;

    private Hero(Builder builder) {
        this.name = builder.name;
        this.heroClass = builder.heroClass;
        this.level = builder.level;
        this.experience = builder.experience;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.helm = builder.helm;
    }

    public static class Builder {
        private String    name;
        private HeroClass heroClass;

        private int level = 1;
        private int experience = 0;

        private Weapon  weapon;
        private Armor   armor;
        private Helm    helm;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withHeroClass(HeroClass heroClass) {
            this.heroClass = heroClass;
            return this;
        }

        public Builder withLevel(int level) {
            if (level <= 0)
                throw new IllegalStateException("Hero level must be at least 1");
            this.level = level;
            return this;
        }

        public Builder withWeapon(Weapon weapon) {
            this.weapon = weapon;
            return this;
        }

        public Builder withArmor(Armor armor) {
            this.armor = armor;
            return this;
        }

        public Builder withHelm(Helm helm) {
            this.helm = helm;
            return this;
        }

        public Hero build() {
            if (name == null || name.isBlank())
                throw new IllegalStateException("Hero name is required");
            if (heroClass == null)
                throw new IllegalStateException("Hero class is required");

            return new Hero(this);
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void equipHero(Artifact artifact) {
        if (artifact instanceof Weapon) {
            this.weapon = (Weapon) artifact;
        } else if (artifact instanceof Armor) {
            this.armor = (Armor) artifact;
        } else if (artifact instanceof Helm) {
            this.helm = (Helm) artifact;
        } else {
            throw new IllegalArgumentException("Unknown artifact type");
        }
    }

    private int xpRequiredForNextLevel() {
        return level * 1000 + (level - 1) * (level - 1) * 450;
    }

    public void updateLevel() {
        while (experience >= xpRequiredForNextLevel()) {
            level++;
        }
    }

    public void addExperience(int gainedExperience) {
        if (gainedExperience < 0)
            throw new IllegalArgumentException("Experience gain must be non-negative");
        this.experience += gainedExperience;
        updateLevel();
    }

    public String getName() {
        return this.name;
    }

    public HeroClass getHeroClass() {
        return this.heroClass;
    }

    public int getLevel() {
        return this.level;
    }

    public int getExperience() {
        return this.experience;
    }

    public Position getPosition() {
        return position;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Helm getHelm() {
        return helm;
    }

    @Override
    public String toString() {
        return "Hero {" +
                "name='" + name + '\'' +
                ", class=" + heroClass +
                ", level=" + level +
                ", experience=" + experience +
                ", position=" + (position != null ? position : "not placed") +
                ", weapon=" + (weapon != null ? weapon : "none") +
                ", armor=" + (armor != null ? armor : "none") +
                ", helm=" + (helm != null ? helm : "none") +
                '}';
    }

}
