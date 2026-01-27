package com.pourist.swingy.model.artifact;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactFactoryTest {

    @TempDir
    Path tempDir;

    private Path createArtifactFile(String content) throws IOException {
        Path file = tempDir.resolve("data/artifacts.txt");
        Files.writeString(file, content);
        return file;
    }

    @Test
    void shouldLoadArtifactsAndReturnArtifactForValidLevel() throws IOException {
        Path file = createArtifactFile("""
                TYPE,NAME,BONUS,MIN_LEVEL,MAX_LEVEL
                WEAPON,Sword,5,1,3
                ARMOR,Armor,4,1,3
                HELM,Helm,3,1,3
                """);

        ArtifactFactory factory = new ArtifactFactory(file);

        Artifact artifact = factory.getRandomArtifactForLevel(2);

        assertNotNull(artifact);
        assertTrue(artifact.getBonusValue() > 0);
    }

    @Test
    void shouldReturnNullWhenNoArtifactMatchesLevel() throws IOException {
        Path file = createArtifactFile("""
                TYPE,NAME,BONUS,MIN_LEVEL,MAX_LEVEL
                WEAPON,Sword,5,5,10
                """);

        ArtifactFactory factory = new ArtifactFactory(file);

        Artifact artifact = factory.getRandomArtifactForLevel(1);
        assertNull(artifact);
    }

    @Test
    void shouldThrowExceptionWhenFileIsInvalid() throws IOException {
        Path file = createArtifactFile("""
                TYPE,NAME,BONUS,MIN_LEVEL,MAX_LEVEL
                WEAPON,Sword,0,1,3
                """);

        assertThrows(IllegalStateException.class,
                () -> new ArtifactFactory(file)
        );
    }

    @Test
    void shouldThrowExceptionWhenFileIsEmpty() throws IOException {
        Path file = createArtifactFile("""
                TYPE,NAME,BONUS,MIN_LEVEL,MAX_LEVEL
                """);

        assertThrows(IllegalStateException.class,
                () -> new ArtifactFactory(file)
        );
    }
}
