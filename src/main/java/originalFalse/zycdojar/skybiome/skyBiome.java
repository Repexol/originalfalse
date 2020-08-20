package originalFalse.zycdojar.skybiome;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import originalFalse.zycdojar.event.registyevent.itemregister;

public class skyBiome extends Biome {
    public skyBiome(Builder biomeBuilder) {
        super(biomeBuilder);
        setRegistryName("sky_biome");
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                itemregister.getSSB.getDefaultState(), 9)).
                        withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(
                                20, 32, 32, 80))));
        addSpawn(EntityClassification.MONSTER,new SpawnListEntry(EntityType.WITHER,30,5,10));
    }
}
