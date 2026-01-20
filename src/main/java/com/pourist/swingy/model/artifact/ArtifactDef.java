package com.pourist.swingy.model.artifact;

public class ArtifactDef {
    public final ArtifactType type;
    public final String name;
    public final int bonus;
    public final int minLevel;
    public final int maxLevel;

    public ArtifactDef(ArtifactType type, String name, int bonus, int minLevel, int maxLevel) {
        this.type = type;
        this.name = name;
        this.bonus = bonus;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public Artifact toArtifact() {
        return switch (type) {
            case WEAPON -> new Weapon(name, bonus);
            case ARMOR  -> new Armor(name, bonus);
            case HELM   -> new Helm(name, bonus);
        };
    }
}
