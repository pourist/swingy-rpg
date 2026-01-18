package com.pourist.swingy.model.artifact;

import java.util.Objects;

public class Helm extends Artifact {

    private final int hitPointsBonus;

    public Helm(String name, int hitPointsBonus) {
        super(name);
        if (hitPointsBonus <= 0)
            throw new IllegalArgumentException("Hit points bonus must be positive");
        this.hitPointsBonus = hitPointsBonus;
    }

    public int getHitPointsBonus() {
        return hitPointsBonus;
    }

    @Override
    public ArtifactType getType() {
        return ArtifactType.HELM;
    }

    @Override
    public int getBonusValue() {
        return hitPointsBonus;
    }

    @Override
    public String toString() {
        return getName() + " (HP +" + hitPointsBonus + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Helm)) return false;
        Helm helm = (Helm) o;
        return hitPointsBonus == helm.hitPointsBonus &&
                getName().equals(helm.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), hitPointsBonus);
    }
}
