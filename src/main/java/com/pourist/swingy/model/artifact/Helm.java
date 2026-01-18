package com.pourist.swingy.model.artifact;

public class Helm extends Artifact {

    private final int hitPointBonus;

    public Helm(String name, int hitPointsBonus) {
        super(name);

        if (hitPointsBonus <= 0)
            throw new IllegalArgumentException("Hit point bonus must be positive");

        this.hitPointBonus = hitPointsBonus;
    }

    public int getHitPointsBonus() {
        return hitPointBonus;
    }
}
