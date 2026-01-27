package com.pourist.swingy.model.villain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class VillainFactory {

    private final List<VillainDef> villains;
    private final Random random = new Random();

    public VillainFactory(List<VillainDef> villains) {
        if (villains == null || villains.isEmpty())
            throw new IllegalStateException("No villains found.");
        this.villains = villains;
    }

    public Villain getRandomVillainForLevel(int heroLevel) {
        List<VillainDef> eligible = new ArrayList<>();

        for (VillainDef def : villains) {
            if (heroLevel >= def.minLevel && heroLevel <= def.maxLevel) {
                eligible.add(def);
            }
        }

        if (eligible.isEmpty())
            return null;

        int index = random.nextInt(eligible.size());
        return eligible.get(index).createVillain();
    }
}
