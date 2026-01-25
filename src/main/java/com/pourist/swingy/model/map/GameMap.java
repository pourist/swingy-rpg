package com.pourist.swingy.model.map;

  import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.model.artifact.ArtifactFactory;
import com.pourist.swingy.model.villain.Villain;
import com.pourist.swingy.model.villain.VillainFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class GameMap {

    private Map<Position, Villain> villains = new HashMap<>();

    private final int mapHeight;
    private final int level;

    private final VillainFactory villainFactory;
    private final ArtifactFactory artifactFactory;

    private final Random random =  new Random();

    private static final int VILLAIN_SPAWN_CHANCE = 49;

    public GameMap(int level,
                   VillainFactory villainFactory,
                   ArtifactFactory artifactFactory) {
        if (level < 1)
            throw new IllegalArgumentException("Minimum Level is 1");

        this.level = level;
        this.villainFactory = villainFactory;
        this.artifactFactory = artifactFactory;
        mapHeight = (level - 1) * 5 + 10 - (level % 2);
        generateMap();
    }

    private void generateMap() {
        for (int x = 0; x < mapHeight; x++) {
            for (int y = 0; y < mapHeight; y++) {
                    assignVillain(x, y);
            }
        }
    }

    private void assignVillain(int x, int y) {
        int roll = random.nextInt(100);
        if (roll >= VILLAIN_SPAWN_CHANCE) {
            return;
        }

        Position position = new Position(x, y);
        Artifact artifact = artifactFactory.getRandomArtifactForLevel(level);
        Villain villain = villainFactory.getRandomVillaintForLevel(level);
        if (villain == null)
            return;
        villain.equip(artifact);

        villains.put(position, villain);
    }

    public boolean isBound(Position position) {
        int x = position.getX();
        int y = position.getY();

        return x == 0 || y == 0 || y == mapHeight - 1 || x == mapHeight - 1;
    }

    public Position getCenterOfTheMap() {
        int center = (mapHeight - 1) / 2;
        return new Position(center, center);
    }
}
