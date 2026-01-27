package com.pourist.swingy.model.artifact;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ArtifactDefLoader {

    public List<ArtifactDef> loadArtifacts(String resourcePath) {
        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream(resourcePath);

        if (is == null)
            throw new IllegalStateException("Resource not found: " + resourcePath);

        List<ArtifactDef> loaded = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

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

                try {
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
                } catch (RuntimeException e) {
                    throw new IllegalStateException("Invalid artifact definition: " + line, e);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read artifacts resource.", e);
        }

        if (loaded.isEmpty())
            throw new IllegalStateException("No artifacts loaded");

        return loaded;
    }
}
