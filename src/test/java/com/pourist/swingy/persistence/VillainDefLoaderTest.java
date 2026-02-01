package com.pourist.swingy.persistence;

import com.pourist.swingy.model.villain.VillainDef;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VillainDefLoaderTest {

    private final VillainDefLoader loader = new VillainDefLoader();

    @Test
    void loadVillains_shouldLoadValidVillains() {
        List<VillainDef> villains =
                loader.loadVillains("data/villains.txt");

        assertNotNull(villains);
        assertFalse(villains.isEmpty());
    }

    @Test
    void loadVillains_shouldThrowException_whenResourceDoesNotExist() {
        assertThrows(IllegalStateException.class,
                () -> loader.loadVillains("data/does_not_exist.txt"));
    }

    @Test
    void loadVillains_shouldThrowException_whenFileIsEmptyOrOnlyHeader() {
        assertThrows(IllegalStateException.class,
                () -> loader.loadVillains("data/empty_villains.txt"));
    }

    @Test
    void loadVillains_shouldThrowException_whenLineIsInvalid() {
        assertThrows(IllegalStateException.class,
                () -> loader.loadVillains("data/invalid_villains.txt"));
    }
}
