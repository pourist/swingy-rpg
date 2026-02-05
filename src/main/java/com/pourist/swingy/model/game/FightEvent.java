package com.pourist.swingy.model.game;

import com.pourist.swingy.model.artifact.Artifact;

public record FightEvent(
        String attacker,
        String defender,
        int damage,
        int attackerHp,
        int defenderHp,
        boolean defenderDied,
        boolean fightEnded,
        Artifact dropedArtifact
) {
    public Artifact droppedArtifact() {
        return dropedArtifact;
    }
}

