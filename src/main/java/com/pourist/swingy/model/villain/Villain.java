package com.pourist.swingy.model.villain;

import com.pourist.swingy.model.artifact.Artifact;

public class Villain {
    private final String name;

    private final int attack;
    private final int defense;
    private final int hitPoints;

    private final Artifact equipment;

    Villain(Builder builder) {
        name = builder.name;;
        attack = builder.attack;;
        defense = builder.defense;
        hitPoints = builder.hitPoints;

        equipment = builder.equipment;
    }

    public static class Builder {
        private String name;
        private int    attack;
        private int    defense;
        private int    hitPoints;

        private Artifact equipment;

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

        public Builder withEquipment(Artifact equipment) {
            this.equipment = equipment;
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

    public int getHitPoints() {
        return hitPoints;
    }

    public Artifact getEquipment() {
        return equipment;
    }
}

