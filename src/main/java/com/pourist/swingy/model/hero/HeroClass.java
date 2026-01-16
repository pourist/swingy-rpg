package com.pourist.swingy.model.hero;

public enum HeroClass {

    WARRIOR(7, 10, 100),
    ROGUE(12, 5, 70),
    MAGE(9, 7, 80);

    private final int baseAttack;
    private final int baseDefense;
    private final int baseHitPoints;

    HeroClass(int baseAttack, int baseDefense, int baseHitPoints) {
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseHitPoints = baseHitPoints;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getBaseHitPoints() {
        return baseHitPoints;
    }
}
