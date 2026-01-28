package com.pourist.swingy.model.map;

import com.pourist.swingy.model.artifact.ArtifactDef;
import com.pourist.swingy.persistence.ArtifactDefLoader;
import com.pourist.swingy.model.artifact.ArtifactFactory;
import com.pourist.swingy.model.villain.VillainDef;
import com.pourist.swingy.persistence.VillainDefLoader;
import com.pourist.swingy.model.villain.VillainFactory;

import java.util.List;

public class MapFactory {
    public static GameMap createMap(int  heroLevel) {
        VillainDefLoader villainLoader = new VillainDefLoader();
        List<VillainDef> villainDefs =
                villainLoader.loadVillains("data/villains.txt");

        ArtifactDefLoader artifactLoader = new ArtifactDefLoader();
        List<ArtifactDef> artifactDefs =
                artifactLoader.loadArtifacts("data/artifacts.txt");

        VillainFactory villainFactory = new VillainFactory(villainDefs);
        ArtifactFactory artifactFactory = new ArtifactFactory(artifactDefs);

        return new GameMap(heroLevel, villainFactory, artifactFactory);
    }
}
