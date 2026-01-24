package com.pourist.swingy.model.artifact;

public class Helm extends Artifact {

    private final int hitPointsBonus;

    public Helm(String name, int hitPointsBonus) {
        super(name);
        if (hitPointsBonus <= 0)
            throw new IllegalArgumentException("Hit points bonus must be positive");
        this.hitPointsBonus = hitPointsBonus;
    }

    @Override
    public ArtifactType getType() {
        return ArtifactType.HELM;
    }

    @Override
    public int getBonusValue() {
        return hitPointsBonus;
    }
}
