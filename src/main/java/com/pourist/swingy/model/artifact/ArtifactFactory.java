package com.pourist.swingy.model.artifact;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtifactFactory {

    private final List<ArtifactDef> artifacts;
    private final Random random = new Random();

    public ArtifactFactory(List<ArtifactDef> artifacts) {
        if (artifacts == null || artifacts.isEmpty())
            throw new IllegalStateException("No artifacts found.");
        this.artifacts = artifacts;
    }

    public Artifact getRandomArtifactForLevel(int heroLevel) {
        List<ArtifactDef> eligible = new ArrayList<>();

        for (ArtifactDef def : artifacts) {
            if (heroLevel >= def.minLevel && heroLevel <= def.maxLevel) {
                eligible.add(def);
            }
        }

        if (eligible.isEmpty())
            return null;

        int index = random.nextInt(eligible.size());
        return eligible.get(index).toArtifact();
    }
}
