package originalFalse.zycdojar.event.registyevent;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import originalFalse.zycdojar.skybiome.skyBiome;

/**
 * 注册矿物
 */
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class itemregister3 {
    public static skyBiome SkyBiome;
    @SubscribeEvent
    public static void onBiomeRegister(RegistryEvent.Register<Biome> event){
        IForgeRegistry<Biome> registry=event.getRegistry();
        SkyBiome=new skyBiome(new Biome.Builder().category(Biome.Category.PLAINS)
                .surfaceBuilder(SurfaceBuilder.DEFAULT,
                        new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIAMOND_ORE.getDefaultState(), itemregister.Redic.getDefaultState())
                )
                .scale(3f)
                .downfall(0.5f)
                .precipitation(Biome.RainType.SNOW)
                .depth(1f)
                .temperature(0.7f)
                .waterColor(0xff7f00)
                .waterFogColor(0x632ebf)
        );
        registry.register(SkyBiome);
    }
    @SubscribeEvent
    public static void onSetUpEvent(FMLCommonSetupEvent event) {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(SkyBiome, 200));
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(
                            new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                    itemregister.getSSB.getDefaultState(),
                                    3)
                    ).withPlacement(Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(30, 30, 20)))
            );
        }
    }
}
