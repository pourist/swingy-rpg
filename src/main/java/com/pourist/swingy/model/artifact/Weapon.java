package com.pourist.swingy.model.artifact;

public class Weapon extends Artifact {

    private final int attackBonus;

    public Weapon(String name, int attackBonus) {
        super(name);

        if (attackBonus <= 0)
            throw new IllegalArgumentException("Attack bonus must be positive");

        this.attackBonus = attackBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }
}
