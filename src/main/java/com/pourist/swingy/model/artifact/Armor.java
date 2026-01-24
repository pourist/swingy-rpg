package com.pourist.swingy.model.artifact;

public class Armor extends Artifact {

    private final int defenseBonus;

    public Armor(String name, int defenseBonus) {
        super(name);
        if (defenseBonus <= 0)
            throw new IllegalArgumentException("Defense bonus must be positive");
        this.defenseBonus = defenseBonus;
    }

    @Override
    public ArtifactType getType() {
        return ArtifactType.ARMOR;
    }

    @Override
    public int getBonusValue() {
        return defenseBonus;
    }
}
