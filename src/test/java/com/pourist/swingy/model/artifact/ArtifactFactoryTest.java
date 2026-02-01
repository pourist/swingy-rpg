package com.pourist.swingy.model.artifact;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactFactoryTest {

    @Test
    void constructor_shouldThrowException_whenArtifactListIsNull() {
        assertThrows(IllegalStateException.class,
                () -> new ArtifactFactory(null));
    }

    @Test
    void constructor_shouldThrowException_whenArtifactListIsEmpty() {
        assertThrows(IllegalStateException.class,
                () -> new ArtifactFactory(List.of()));
    }

    @Test
    void getRandomArtifactForLevel_shouldReturnNull_whenNoArtifactMatchesLevel() {
        ArtifactDef def = new ArtifactDef(
                ArtifactType.WEAPON,
                "Sword",
                5,
                5,   // minLevel
                10   // maxLevel
        );

        ArtifactFactory factory = new ArtifactFactory(List.of(def));

        Artifact artifact = factory.getRandomArtifactForLevel(1);

        assertNull(artifact);
    }

    @Test
    void getRandomArtifactForLevel_shouldReturnArtifact_whenLevelIsEligible() {
        ArtifactDef def = new ArtifactDef(
                ArtifactType.WEAPON,
                "Sword",
                5,
                1,
                10
        );

        ArtifactFactory factory = new ArtifactFactory(List.of(def));

        Artifact artifact = factory.getRandomArtifactForLevel(5);

        assertNotNull(artifact);
        assertEquals(ArtifactType.WEAPON, artifact.getType());
        assertEquals("Sword", artifact.getName());
        assertEquals(5, artifact.getBonusValue());
    }
}
