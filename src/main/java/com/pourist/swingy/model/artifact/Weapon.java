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

    @Override
    public ArtifactType getType() {
        return ArtifactType.WEAPON;
    }

    @Override
    public int getBonusValue() {
        return attackBonus;
    }

    @Override
    public String toString() {
        return getName() + " (ATK +" + attackBonus + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weapon)) return false;
        Weapon weapon = (Weapon) o;
        return attackBonus == weapon.attackBonus &&
                getName().equals(weapon.getName());
    }

}
