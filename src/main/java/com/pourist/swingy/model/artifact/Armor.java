package com.pourist.swingy.model.artifact;

import java.util.Objects;

public class Armor extends Artifact {

    private final int defenseBonus;

    public Armor(String name, int defenseBonus) {
        super(name);
        if (defenseBonus <= 0)
            throw new IllegalArgumentException("Defense bonus must be positive");
        this.defenseBonus = defenseBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public ArtifactType getType() {
        return ArtifactType.ARMOR;
    }

    @Override
    public int getBonusValue() {
        return defenseBonus;
    }

    @Override
    public String toString() {
        return getName() + " (DEF +" + defenseBonus + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Armor)) return false;
        Armor armor = (Armor) o;
        return defenseBonus == armor.defenseBonus &&
                getName().equals(armor.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), defenseBonus);
    }
}
