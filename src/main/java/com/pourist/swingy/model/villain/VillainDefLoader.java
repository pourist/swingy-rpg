package com.pourist.swingy.model.villain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class VillainDefLoader {

    public List<VillainDef> loadVillains(String resourcePath) {
        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream(resourcePath);

        if (is == null)
            throw new IllegalStateException("Resource not found: " + resourcePath);

        List<VillainDef> loaded = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            boolean firstLine = true;
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 4)
                    throw new IllegalStateException("Invalid villain line: " + line);

                try {
                    String name = parts[0].trim();
                    int minLevel = Integer.parseInt(parts[1].trim());
                    int maxLevel = Integer.parseInt(parts[2].trim());
                    int powerModifier = Integer.parseInt(parts[3].trim());

                    if (name.isBlank())
                        throw new IllegalStateException("Villain name is blank");
                    if (minLevel < 1 || minLevel > maxLevel)
                        throw new IllegalStateException("Invalid level range");

                    loaded.add(new VillainDef(name, minLevel, maxLevel, powerModifier));
                } catch (RuntimeException e) {
                    throw new IllegalStateException("Invalid villain definition: " + line, e);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read villains resource.", e);
        }

        if (loaded.isEmpty())
            throw new IllegalStateException("No villains loaded");

        return loaded;
    }
}
