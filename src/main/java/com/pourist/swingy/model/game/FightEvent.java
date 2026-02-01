package com.pourist.swingy.model.game;

public record FightEvent(
        String attacker,
        String defender,
        int damage,
        int attackerHp,
        int defenderHp,
        boolean defenderDied,
        boolean fightEnded
) {}

