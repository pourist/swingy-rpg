package com.pourist.swingy.persistence;

import com.pourist.swingy.model.hero.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHeroRepository implements HeroRepository {

    private final Connection connection;

    public DatabaseHeroRepository() {
        this.connection = DatabaseManager.getConnection();
    }

    @Override
    public List<Hero> loadAll() {
        List<Hero> heroes = new ArrayList<>();
        HeroRowMapper mapper = new HeroRowMapper();

        String sql = "SELECT * FROM heroes";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                heroes.add(mapper.map(rs));
            }
            return heroes;

        } catch (SQLException e) {
            throw new IllegalStateException("Failed to load heroes", e);
        }
    }

    @Override
    public void save(Hero hero) {

        String sql = """
            INSERT INTO heroes (
                name, hero_class, experience,
                weapon_name, weapon_bonus,
                armor_name, armor_bonus,
                helm_name, helm_bonus
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            ON CONFLICT(name) DO UPDATE SET
                hero_class   = excluded.hero_class,
                experience   = excluded.experience,
                weapon_name  = excluded.weapon_name,
                weapon_bonus = excluded.weapon_bonus,
                armor_name   = excluded.armor_name,
                armor_bonus  = excluded.armor_bonus,
                helm_name    = excluded.helm_name,
                helm_bonus   = excluded.helm_bonus;
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Core hero data
            stmt.setString(1, hero.getName());
            stmt.setString(2, hero.getHeroClass().name());
            stmt.setInt(3, hero.getExperience());

            // Weapon
            if (hero.getWeapon() != null) {
                stmt.setString(4, hero.getWeapon().getName());
                stmt.setInt(5, hero.getWeapon().getBonusValue());
            } else {
                stmt.setNull(4, Types.VARCHAR);
                stmt.setNull(5, Types.INTEGER);
            }

            // Armor
            if (hero.getArmor() != null) {
                stmt.setString(6, hero.getArmor().getName());
                stmt.setInt(7, hero.getArmor().getBonusValue());
            } else {
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.INTEGER);
            }

            // Helm
            if (hero.getHelm() != null) {
                stmt.setString(8, hero.getHelm().getName());
                stmt.setInt(9, hero.getHelm().getBonusValue());
            } else {
                stmt.setNull(8, Types.VARCHAR);
                stmt.setNull(9, Types.INTEGER);
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to save hero: " + hero.getName(), e
            );
        }
    }

    @Override
    public void remove(Hero hero) {
        String sql = "DELETE FROM heroes WHERE name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hero.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to remove hero: " + hero.getName(), e
            );
        }
    }
}
