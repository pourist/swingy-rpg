package com.pourist.swingy.model.villain;

import com.pourist.swingy.model.artifact.Armor;
import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.model.artifact.Helm;
import com.pourist.swingy.model.artifact.Weapon;

public class Villain {
    private final String name;

    private final int attack;
    private final int defense;
    private final int maxHitPoints;
    private int currentHitPoints;
    private Artifact equipment;

    private Villain(Builder builder) {
        name = builder.name;
        attack = builder.attack;
        defense = builder.defense;
        maxHitPoints = builder.hitPoints;
        currentHitPoints = maxHitPoints;
    }

    public static class Builder {
        private String name;
        private int    attack;
        private int    defense;
        private int    hitPoints;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAttack(int attack) {
            this.attack = attack;
            return this;
        }

        public Builder withDefense(int defense) {
            this.defense = defense;
            return this;
        }

        public Builder withHitPoints(int hitPoints) {
            this.hitPoints = hitPoints;
            return this;
        }

        public Villain build() {
            if (name == null || name.isBlank())
                throw new IllegalStateException("Villain name is required");
            if (attack < 1 || attack > 100)
                throw new IllegalStateException("Villain attack must be between 1 and 100");
            if (defense < 1 || defense > 100)
                throw new IllegalStateException("Villain defense must be between 1 and 100");
            if (hitPoints < 1 || hitPoints > 100)
                throw new IllegalStateException("Villain hit points must be between 1 and 100");

            return new Villain(this);
        }
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getHitPoints() { return currentHitPoints; }

    public boolean isAlive() {
        return (currentHitPoints > 0);
    }

    public Artifact getEquipment() {
        return equipment;
    }

    public void equip(Artifact artifact) {
        if (artifact instanceof Weapon) {
            this.equipment = artifact;
        } else if (artifact instanceof Armor) {
            this.equipment = artifact;
        } else if (artifact instanceof Helm) {
            this.equipment = artifact;
        } else {
            throw new IllegalArgumentException("Unknown artifact type");
        }
    }

    public void takeDamage(int damage) {
        if (damage < 0)
            throw new IllegalArgumentException("Damage must be non-negative");
        currentHitPoints = Math.max(0, currentHitPoints - damage);
    }

    public int getExperienceReward() {
        return (attack + defense + maxHitPoints) * 10;
    }
}

