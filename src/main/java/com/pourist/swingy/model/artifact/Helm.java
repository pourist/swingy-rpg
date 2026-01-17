package com.pourist.swingy.model.artifact;

public class Helm extends Artifact {

    private final int hitPointBonus;

    public Helm(String name, int hitPointBonus) {
        super(name);

        if (hitPointBonus <= 0)
            throw new IllegalArgumentException("Hit point bonus must be positive");

        this.hitPointBonus = hitPointBonus;
    }

    public int getHitPointBonus() {
        return hitPointBonus;
    }
}
