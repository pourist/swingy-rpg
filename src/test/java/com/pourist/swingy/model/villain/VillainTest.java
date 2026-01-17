package com.pourist.swingy.model.villain;

import com.pourist.swingy.model.artifact.Armor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VillainTest {
    @Test
    void builder_shouldCreateVillain_whenAllRequiredFieldsAreProvided () {
        var armor = new Armor("Ar", 5);

        var villain = new Villain.Builder()
                .withName("Div")
                .withAttack(1)
                .withDefense(19)
                .withHitPoints(100)
                .withEquipment(armor)
                .build();

        assertNotNull(villain);
        assertEquals("Div", villain.getName());
        assertEquals(1, villain.getAttack());
        assertEquals(19, villain.getDefense());
        assertEquals(100, villain.getHitPoints());
        assertSame(armor, villain.getEquipment());
    }

    @Test
    void builder_shouldThrowException_whenNameNotProvided() {
        assertThrows(IllegalStateException.class,
                () ->
                    new Villain.Builder()
                        .withAttack(1)
                        .withDefense(19)
                        .withHitPoints(100)
                            .build()
        );
    }

    @Test
    void builder_shouldThrowException_whenNameIsNull() {
        assertThrows(IllegalStateException.class,
                () ->
                        new Villain.Builder()
                                .withName(null)
                                .withAttack(1)
                                .withDefense(19)
                                .withHitPoints(100)
                                .build()
        );
    }

    @Test
    void builder_shouldThrowException_whenAttackIsOutOfRange() {
        assertThrows(IllegalStateException.class,
                () ->
                        new Villain.Builder()
                                .withName("Ar")
                                .withAttack(0)
                                .withDefense(19)
                                .withHitPoints(100)
                                .build()
        );

        assertThrows(IllegalStateException.class,
                () ->
                        new Villain.Builder()
                                .withName("Ar")
                                .withAttack(101)
                                .withDefense(19)
                                .withHitPoints(100)
                                .build()
        );
    }

    @Test
    void builder_shouldThrowException_whenDefenseIsOutOfRange() {
        assertThrows(IllegalStateException.class,
                () ->
                        new Villain.Builder()
                                .withName("Ar")
                                .withAttack(10)
                                .withDefense(-1)
                                .withHitPoints(100)
                                .build()
        );

        assertThrows(IllegalStateException.class,
                () ->
                        new Villain.Builder()
                                .withName("Ar")
                                .withAttack(10)
                                .withDefense(119)
                                .withHitPoints(100)
                                .build()
        );
    }

    @Test
    void builder_shouldThrowException_whenHitPointsIsOutOfRange() {
        assertThrows(IllegalStateException.class,
                () ->
                        new Villain.Builder()
                                .withName("Ar")
                                .withAttack(10)
                                .withDefense(11)
                                .withHitPoints(2100)
                                .build()
        );

        assertThrows(IllegalStateException.class,
                () ->
                        new Villain.Builder()
                                .withName("Ar")
                                .withAttack(10)
                                .withDefense(11)
                                .withHitPoints(-100)
                                .build()
        );
    }
}