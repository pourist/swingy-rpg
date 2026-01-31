package com.pourist.swingy.model.hero;

import com.pourist.swingy.model.artifact.Armor;
import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.model.artifact.Helm;
import com.pourist.swingy.model.artifact.Weapon;
import com.pourist.swingy.model.map.Position;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Hero {

    @NotBlank(message = "Hero name cannot be empty")
    private final String    name;

    @NotNull(message = "Hero class must be selected")
    private final HeroClass heroClass;

    private int level;
    private int experience;
    private int currentHitPoints;

    private Position    position;
    private Weapon  weapon;
    private Armor   armor;
    private Helm    helm;

    private Hero(Builder builder) {
        this.name = builder.name;
        this.heroClass = builder.heroClass;
        this.experience = builder.experience;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.helm = builder.helm;

        this.level = 1;
        updateLevelIfNeeded();

        this.currentHitPoints = getMaxHitPoints();
    }

    public static class Builder {
        private String    name;
        private HeroClass heroClass;

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

        public Builder withExperience(int experience) {
            if (experience < 0)
                throw new IllegalArgumentException("Experience must be non-negative");
            this.experience = experience;
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
            return new Hero(this);
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void equip(Artifact artifact) {
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

    private static int xpRequiredForNextLevel(int currentLevel) {
        return currentLevel * 1000 + (currentLevel - 1) * (currentLevel - 1) * 450;
    }

    private void updateLevelIfNeeded() {
        while (experience >= xpRequiredForNextLevel(this.level)) {
            int oldMax = getMaxHitPoints();

            this.level++;

            int newMax = getMaxHitPoints();
            currentHitPoints += (newMax - oldMax);
        }
    }

    public void addExperience(int gainedExperience) {
        if (gainedExperience < 0)
            throw new IllegalArgumentException("Experience gain must be non-negative");
        this.experience += gainedExperience;
        updateLevelIfNeeded();
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

    public int getAttack() {
        int attack = heroClass.getBaseAttack();
        attack += (level - 1); // +1 per level
        if (weapon != null)
            attack += weapon.getBonusValue();
        return attack;
    }

    public int getDefense() {
        int defense = heroClass.getBaseDefense();
        defense += (level - 1); // +1 per level
        if (armor != null)
            defense += armor.getBonusValue();
        return defense;
    }

    public int getHitPoints() {
        return currentHitPoints;
    }

    public int getMaxHitPoints() {
        int hp = heroClass.getBaseHitPoints();
        hp += (level - 1) * 5;
        if (helm != null)
            hp += helm.getBonusValue();
        return hp;
    }

    public void takeDamage(int damage) {
        if (damage < 0)
            throw new IllegalArgumentException("Damage must be non-negative");

        currentHitPoints = Math.max(0, currentHitPoints - damage);
    }


    public boolean isAlive() {
        return currentHitPoints > 0;
    }


    @Override
    public String toString() {
        return "Hero {" +
                "name='" + name + '\'' +
                ", class=" + heroClass +
                ", level=" + level +
                ", experience=" + experience +
                ", hp=" + currentHitPoints + "/" + getMaxHitPoints() +
                ", position=" + (position != null ? position : "not placed") +
                ", weapon=" + (weapon != null ? weapon : "none") +
                ", armor=" + (armor != null ? armor : "none") +
                ", helm=" + (helm != null ? helm : "none") +
                '}';
    }


}
