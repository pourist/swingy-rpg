package com.pourist.swingy.persistence;

import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.hero.HeroClass;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HeroRowMapperTest {

    private final HeroRowMapper mapper = new HeroRowMapper();

    @Test
    void map_shouldCreateHero_withoutArtifacts() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getString("name")).thenReturn("Arthur");
        when(rs.getString("hero_class")).thenReturn("WARRIOR");
        when(rs.getInt("experience")).thenReturn(100);

        when(rs.getString("weapon_name")).thenReturn(null);
        when(rs.getString("armor_name")).thenReturn(null);
        when(rs.getString("helm_name")).thenReturn(null);

        Hero hero = mapper.map(rs);

        assertNotNull(hero);
        assertEquals("Arthur", hero.getName());
        assertEquals(HeroClass.WARRIOR, hero.getHeroClass());
        assertEquals(100, hero.getExperience());
        assertNull(hero.getWeapon());
        assertNull(hero.getArmor());
        assertNull(hero.getHelm());
    }

    @Test
    void map_shouldCreateHero_withAllArtifacts() throws Exception {
        ResultSet rs = mock(ResultSet.class);

        when(rs.getString("name")).thenReturn("Lancelot");
        when(rs.getString("hero_class")).thenReturn("MAGE");
        when(rs.getInt("experience")).thenReturn(500);

        when(rs.getString("weapon_name")).thenReturn("Sword");
        when(rs.getInt("weapon_bonus")).thenReturn(10);
        when(rs.wasNull()).thenReturn(false);

        when(rs.getString("armor_name")).thenReturn("Armor");
        when(rs.getInt("armor_bonus")).thenReturn(8);

        when(rs.getString("helm_name")).thenReturn("Helm");
        when(rs.getInt("helm_bonus")).thenReturn(5);

        Hero hero = mapper.map(rs);

        assertNotNull(hero);
        assertEquals("Lancelot", hero.getName());
        assertEquals(HeroClass.MAGE, hero.getHeroClass());
        assertEquals(500, hero.getExperience());

        assertNotNull(hero.getWeapon());
        assertNotNull(hero.getArmor());
        assertNotNull(hero.getHelm());
    }
}
