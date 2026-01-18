package com.pourist.swingy.model.hero;

import com.pourist.swingy.model.artifact.Armor;
import com.pourist.swingy.model.artifact.Artifact;
import com.pourist.swingy.model.artifact.Helm;
import com.pourist.swingy.model.artifact.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    @Test
    void    builder_shouldCreateHero_whenAllRequiredFieldsAreProvided() {
        var helm = new Helm("He", 5);
        var weapon = new Weapon("We", 5);
        var armor = new Armor("Ar", 5);

        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.WARRIOR)
                .withExperience(1200)
                .withArmor(armor)
                .withHelm(helm)
                .withWeapon(weapon)
                .build();

        assertNotNull(hero);
        assertEquals("Raul", hero.getName());
        assertEquals(HeroClass.WARRIOR, hero.getHeroClass());
        assertEquals(1200, hero.getExperience());
        assertEquals(2, hero.getLevel());
        assertSame(helm, hero.getHelm());
        assertSame(weapon, hero.getWeapon());
        assertSame(armor, hero.getArmor());
    }

    @Test
    void    builder_shouldCreateHero_whenMinimumRequiredFieldsAreProvided() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.ROGUE)
                .build();

        assertNotNull(hero);
        assertEquals("Raul", hero.getName());
        assertEquals(HeroClass.ROGUE, hero.getHeroClass());
        assertEquals(0, hero.getExperience());
        assertEquals(1, hero.getLevel());
        assertNull(hero.getHelm());
        assertNull(hero.getWeapon());
        assertNull(hero.getArmor());
    }

    @Test
    void    builder_shouldSetLevelBasedOnExperience_whenExperienceCrossesMultipleLevelThresholds() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.ROGUE)
                .withExperience(8060)
                .build();

        assertEquals(5, hero.getLevel());
    }

    @Test
    void    builder_shouldThrowException_whenExperienceIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () ->
                        new Hero.Builder()
                                .withName("Arthur")
                                .withHeroClass(HeroClass.ROGUE)
                                .withExperience(-1)
                                .build()
        );
    }

    @Test
    void    equipHero_shouldThrowException_whenArtifactTypeIsUnknown() {

        class UnknownArtifact extends Artifact {
            public UnknownArtifact() {
                super("Fake");
            }
        }

        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.ROGUE)
                .build();
        Artifact unknownArtifact = new UnknownArtifact();
        assertThrows(IllegalArgumentException.class,
                () ->
                        hero.equip(unknownArtifact)
        );
    }

    @Test
    void addExperience_shouldIncreaseExperience_whenPositiveValueProvided() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.WARRIOR)
                .build();

        hero.addExperience(500);

        assertEquals(500, hero.getExperience());
        assertEquals(1, hero.getLevel());
    }

    @Test
    void addExperience_shouldNotChangeExperience_whenZeroProvided() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.WARRIOR)
                .build();

        hero.addExperience(0);

        assertEquals(0, hero.getExperience());
        assertEquals(1, hero.getLevel());
    }

    @Test
    void addExperience_shouldThrowException_whenNegativeValueProvided() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.WARRIOR)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> hero.addExperience(-10)
        );
    }

    @Test
    void addExperience_shouldIncreaseLevel_whenExperienceReachesNextLevel() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.WARRIOR)
                .build();

        hero.addExperience(1000);

        assertEquals(1000, hero.getExperience());
        assertEquals(2, hero.getLevel());
    }

    @Test
    void addExperience_shouldIncreaseMultipleLevels_whenExperienceExceedsSeveralThresholds() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.MAGE)
                .build();

        hero.addExperience(5000);

        assertEquals(5000, hero.getExperience());
        assertEquals(4, hero.getLevel());
    }

    @Test
    void getAttack_shouldIncreaseWithLevel() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.WARRIOR)
                .build();

        int baseAttack = hero.getAttack();

        hero.addExperience(3000);
        int leveledAttack = hero.getAttack();

        assertTrue(leveledAttack > baseAttack);
    }

    @Test
    void getDefense_shouldIncreaseWithLevel() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.WARRIOR)
                .build();

        int baseDefense = hero.getDefense();

        hero.addExperience(3000);
        int leveledDefense = hero.getDefense();

        assertTrue(leveledDefense > baseDefense);
    }

    @Test
    void getHitPoints_shouldIncreaseWithLevel() {
        var hero = new Hero.Builder()
                .withName("Raul")
                .withHeroClass(HeroClass.WARRIOR)
                .build();

        int baseHp = hero.getHitPoints();

        hero.addExperience(3000);
        int leveledHp = hero.getHitPoints();

        assertTrue(leveledHp > baseHp);
    }

}