package com.pourist.swingy.model.villain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VillainFactoryTest {

    @Test
    void constructor_shouldThrowException_whenVillainListIsNull() {
        assertThrows(IllegalStateException.class,
                () -> new VillainFactory(null));
    }

    @Test
    void constructor_shouldThrowException_whenVillainListIsEmpty() {
        assertThrows(IllegalStateException.class,
                () -> new VillainFactory(List.of()));
    }

    @Test
    void getRandomVillainForLevel_shouldReturnNull_whenNoVillainMatchesLevel() {
        VillainDef def = new VillainDef(
                "Goblin",
                5,   // minLevel
                10,  // maxLevel
                1
        );

        VillainFactory factory = new VillainFactory(List.of(def));

        Villain villain = factory.getRandomVillainForLevel(1);

        assertNull(villain);
    }

    @Test
    void getRandomVillainForLevel_shouldReturnVillain_whenLevelIsEligible() {
        VillainDef def = new VillainDef(
                "Goblin",
                1,
                10,
                1
        );

        VillainFactory factory = new VillainFactory(List.of(def));

        Villain villain = factory.getRandomVillainForLevel(5);

        assertNotNull(villain);
        assertEquals("Goblin", villain.getName());
        assertTrue(villain.getAttack() > 0);
        assertTrue(villain.getDefense() > 0);
        assertTrue(villain.getMaxHitPoints() > 0);
    }
}
