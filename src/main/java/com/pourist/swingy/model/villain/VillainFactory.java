package com.pourist.swingy.model.villain;

import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.model.artifact.ArtifactFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class VillainFactory {
    private final List<VillainDef> villains;
    private final Random random =  new Random();

    public VillainFactory(Path filePath) {
        villains = loadVillains(filePath);
    }

    private List<VillainDef> loadVillains(Path filePath) {
        List<VillainDef> loaded =  new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            boolean firstLine = true;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty())
                    continue;
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 4)
                    throw new IllegalStateException("Invalid villain line: " + line);

                String name = parts[0].trim();
                int minLevel = Integer.parseInt(parts[1].trim());
                int maxLevel = Integer.parseInt(parts[2].trim());
                int powerModifier = Integer.parseInt(parts[3].trim());

                if (name.isBlank())
                    throw new IllegalStateException("Villain name is blank");
                if (powerModifier <= 0)
                    throw new IllegalStateException("Villain bonus must be positive");
                if (minLevel < 1 || minLevel > maxLevel)
                    throw new IllegalStateException("Invalid level range");

                loaded.add(new VillainDef(name, minLevel, maxLevel, powerModifier));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read Artifacts file.", e);
        }
        if (loaded.isEmpty())
            throw new IllegalStateException("No artifacts loaded");
        return loaded;
    }

    public Villain getRandomVillaintForLevel(int heroLevel) {
        List<VillainDef> eligible = new ArrayList<>();

        for (VillainDef def : villains) {
            if (heroLevel >= def.minLevel && heroLevel <= def.maxLevel) {
                eligible.add(def);
            }
        }

        if (eligible.isEmpty())
            return null;

        int index = random.nextInt(eligible.size());
        return eligible.get(index).createVillain();
    }
}
