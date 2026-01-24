package com.pourist.swingy.model.artifact;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    void shouldCreateWeapon_whenAllRequiredFieldsAreProvided() {
        var weapon = new Weapon("Ak47", 12);

        assertNotNull(weapon);
        assertEquals("Ak47", weapon.getName());
        assertEquals(12, weapon.getBonusValue());
        assertEquals(ArtifactType.WEAPON, weapon.getType());
    }

    @Test
    void shouldThrowException_whenAttackBonusIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weapon("Ak47", -1)
        );
    }
}
