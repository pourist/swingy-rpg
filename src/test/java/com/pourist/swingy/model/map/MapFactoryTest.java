package com.pourist.swingy.model.map;

import com.pourist.swingy.model.artifact.ArtifactDef;
import com.pourist.swingy.model.artifact.ArtifactFactory;
import com.pourist.swingy.model.artifact.ArtifactType;
import com.pourist.swingy.model.villain.VillainDef;
import com.pourist.swingy.model.villain.VillainFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {

    @Test
    void centerPosition_shouldNotBeBound() {
        GameMap map = createTestMap(1);

        Position center = map.getCenterOfTheMap();

        assertFalse(map.isBound(center));
    }

    @Test
    void edgePositions_shouldBeBound() {
        int level = 1;
        GameMap map = createTestMap(level);

        int size = (level - 1) * 5 + 10 - (level % 2);

        assertTrue(map.isBound(new Position(0, 0)));
        assertTrue(map.isBound(new Position(0, size - 1)));
        assertTrue(map.isBound(new Position(size - 1, 0)));
        assertTrue(map.isBound(new Position(size - 1, size - 1)));
    }

    @Test
    void map_shouldAlwaysHaveValidCenterPosition() {
        GameMap map = createTestMap(5);

        Position center = map.getCenterOfTheMap();

        assertNotNull(center);
        assertTrue(center.x() >= 0);
        assertTrue(center.y() >= 0);
    }

    @Test
    void hasVillain_shouldNotThrow_forAnyPosition() {
        int level = 3;
        GameMap map = createTestMap(level);

        int size = (level - 1) * 5 + 10 - (level % 2);

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Position position = new Position(x, y);
                assertDoesNotThrow(() -> map.hasVillain(position));
            }
        }
    }

    @Test
    void constructor_shouldThrowException_whenLevelIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> createTestMap(0));
    }

    /* ---------- Test helpers ---------- */

    private GameMap createTestMap(int level) {
        VillainDef villainDef = new VillainDef(
                "TestGoblin",
                1,
                10,
                1
        );

        ArtifactDef artifactDef = new ArtifactDef(
                ArtifactType.WEAPON,
                "TestSword",
                5,
                1,
                10
        );

        VillainFactory villainFactory =
                new VillainFactory(List.of(villainDef));
        ArtifactFactory artifactFactory =
                new ArtifactFactory(List.of(artifactDef));

        return new GameMap(level, villainFactory, artifactFactory);
    }
}
