package com.pourist.swingy.model.villain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class VillainFactoryTest {

    @TempDir
    Path tempDir;

    private Path createVillainFile(String content) throws IOException {
        Path file = tempDir.resolve("villains.txt");
        Files.writeString(file, content);
        return file;
    }

    @Test
    void shouldReturnVillain_whenHeroLevelIsInRange() throws IOException {
        Path file = createVillainFile("""
                NAME,MIN_LEVEL,MAX_LEVEL,POWER_MODIFIER
                Goblin,1,3,1
                Orc,4,6,3
                """);

        VillainFactory factory = new VillainFactory(file);

        Villain villain = factory.getRandomVillaintForLevel(2);

        assertNotNull(villain);
        assertEquals("Goblin", villain.getName());
    }

    @Test
    void shouldReturnNull_whenNoVillainMatchesLevel() throws IOException {
        Path file = createVillainFile("""
                NAME,MIN_LEVEL,MAX_LEVEL,POWER_MODIFIER
                Orc,5,7,3
                """);

        VillainFactory factory = new VillainFactory(file);

        Villain villain = factory.getRandomVillaintForLevel(1);

        assertNull(villain);
    }

    @Test
    void shouldThrowException_whenFileLineIsInvalid() throws IOException {
        Path file = createVillainFile("""
                NAME,MIN_LEVEL,MAX_LEVEL,POWER_MODIFIER
                Goblin,1,3
                """);

        assertThrows(IllegalStateException.class,
                () -> new VillainFactory(file)
        );
    }

    @Test
    void shouldThrowException_whenNoVillainsAreLoaded() throws IOException {
        Path file = createVillainFile("""
                NAME,MIN_LEVEL,MAX_LEVEL,POWER_MODIFIER
                """);

        assertThrows(IllegalStateException.class,
                () -> new VillainFactory(file)
        );
    }
}
