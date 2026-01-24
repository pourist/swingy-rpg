package com.pourist.swingy.model.villain;

public class VillainDef {

    public final String name;
    public final int minLevel;
    public final int maxLevel;
    public final int powerModifier;

    public VillainDef(String name, int minLevel, int maxLevel, int powerModifier) {
        this.name = name;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.powerModifier = powerModifier;
    }

    public Villain createVillain() {

        int baseAttack = 5 + powerModifier;
        int baseDefense = 5 + powerModifier;
        int baseHitPoints = 20 + (powerModifier * 5);

        return new Villain.Builder()
                .withName(name)
                .withAttack(baseAttack)
                .withDefense(baseDefense)
                .withHitPoints(baseHitPoints)
                .build();
    }
}
