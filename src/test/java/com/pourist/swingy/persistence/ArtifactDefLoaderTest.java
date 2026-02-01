package com.pourist.swingy.persistence;

import com.pourist.swingy.model.artifact.ArtifactDef;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactDefLoaderTest {

    private final ArtifactDefLoader loader = new ArtifactDefLoader();

    @Test
    void loadArtifacts_shouldLoadValidArtifacts() {
        List<ArtifactDef> artifacts =
                loader.loadArtifacts("data/artifacts.txt");

        assertNotNull(artifacts);
        assertFalse(artifacts.isEmpty());
    }

    @Test
    void loadArtifacts_shouldThrowException_whenResourceDoesNotExist() {
        assertThrows(IllegalStateException.class,
                () -> loader.loadArtifacts("data/does_not_exist.txt"));
    }

    @Test
    void loadArtifacts_shouldThrowException_whenFileIsEmptyOrOnlyHeader() {
        assertThrows(IllegalStateException.class,
                () -> loader.loadArtifacts("data/empty_artifacts.txt"));
    }

    @Test
    void loadArtifacts_shouldThrowException_whenLineIsInvalid() {
        assertThrows(IllegalStateException.class,
                () -> loader.loadArtifacts("data/invalid_artifacts.txt"));
    }
}
