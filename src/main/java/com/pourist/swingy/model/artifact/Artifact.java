package com.pourist.swingy.model.artifact;

public abstract class Artifact {

    private final String name;

    protected Artifact(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Artifact name must not be null or blank");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract ArtifactType getType();
    public abstract int getBonusValue();

    @Override
    public String toString() {
        return name;
    }
}
