package com.pourist.swingy.model.artifact;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtifactFactory {

    private final List<ArtifactDef> artifacts;
    private final Random random = new Random();

    public ArtifactFactory(Path filePath) {
        artifacts = loadArtifacts(filePath);
    }

    private List<ArtifactDef> loadArtifacts(Path filePath) {

        List<ArtifactDef> loaded = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 5)
                    throw new IllegalStateException("Invalid artifact line: " + line);

                ArtifactType type = ArtifactType.valueOf(parts[0].trim());
                String name = parts[1].trim();
                int bonus = Integer.parseInt(parts[2].trim());
                int minLevel = Integer.parseInt(parts[3].trim());
                int maxLevel = Integer.parseInt(parts[4].trim());

                if (name.isBlank())
                    throw new IllegalStateException("Artifact name is blank");
                if (bonus <= 0)
                    throw new IllegalStateException("Artifact bonus must be positive");
                if (minLevel < 1 || minLevel > maxLevel)
                    throw new IllegalStateException("Invalid level range");

                loaded.add(new ArtifactDef(type, name, bonus, minLevel, maxLevel));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read Artifacts file.", e);
        }
        if (loaded.isEmpty())
            throw new IllegalStateException("No artifacts loaded");

        return loaded;
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
