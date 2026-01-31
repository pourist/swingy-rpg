package com.pourist.swingy.persistence;

import com.pourist.swingy.model.artifact.Armor;
import com.pourist.swingy.model.artifact.Helm;
import com.pourist.swingy.model.artifact.Weapon;
import com.pourist.swingy.model.hero.Hero;
import com.pourist.swingy.model.hero.HeroClass;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HeroRowMapper {

    public Hero map(ResultSet rs) throws SQLException {

        Hero.Builder builder = new Hero.Builder()
                .withName(rs.getString("name"))
                .withHeroClass(HeroClass.valueOf(rs.getString("hero_class")))
                .withExperience(rs.getInt("experience"));

        // Weapon
        String weaponName = rs.getString("weapon_name");
        if (weaponName != null) {
            int weaponBonus = rs.getInt("weapon_bonus");
            if (!rs.wasNull()) {
                builder.withWeapon(new Weapon(weaponName, weaponBonus));
            }
        }

        // Armor
        String armorName = rs.getString("armor_name");
        if (armorName != null) {
            int armorBonus = rs.getInt("armor_bonus");
            if (!rs.wasNull()) {
                builder.withArmor(new Armor(armorName, armorBonus));
            }
        }

        // Helm
        String helmName = rs.getString("helm_name");
        if (helmName != null) {
            int helmBonus = rs.getInt("helm_bonus");
            if (!rs.wasNull()) {
                builder.withHelm(new Helm(helmName, helmBonus));
            }
        }

        return builder.build();
    }
}
