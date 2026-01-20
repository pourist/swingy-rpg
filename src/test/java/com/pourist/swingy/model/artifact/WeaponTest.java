package com.pourist.swingy.model.artifact;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    void shouldCreateWeapon_whenAllRequiredFieldsAreProvided() {
        var weapon = new Weapon("Ak47", 12);

        assertNotNull(weapon);
        assertEquals("Ak47", weapon.getName());
        assertEquals(12, weapon.getAttackBonus());
    }

    @Test
    void    shouldThrowException_whenNameIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () ->
                        new Weapon("", 12)
        );
    }

    @Test
    void    shouldThrowException_whenAttackBonusIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () ->
                        new Weapon("Ak47", -1)
        );
    }

    @Test
    void shouldBeEqual_whenNameAndBonusAreSame() {
        var w1 = new Weapon("Ak47", 12);
        var w2 = new Weapon("Ak47", 12);

        assertEquals(w1, w2);
    }

}