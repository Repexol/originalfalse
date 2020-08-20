package originalFalse.zycdojar.event.registyevent;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import originalFalse.zycdojar.skybiome.skyBiome;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
public class itemregister2 {
    @SubscribeEvent
    public static void biomeRegister(final RegistryEvent.Register<Biome> biomeRegister){
        Biome.Builder builder=new Biome.Builder();
        builder.category(Biome.Category.PLAINS);
        builder.surfaceBuilder(SurfaceBuilder.DEFAULT,
                new SurfaceBuilderConfig(Blocks.OBSIDIAN.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.END_STONE.getDefaultState())
        )
                .scale(3f)
                .downfall(0.5f)
                .precipitation(Biome.RainType.SNOW)
                .depth(1f)
                .temperature(0.7f)
                .waterColor(0x0c0a15)
                .waterFogColor(0x632ebf);
        Set<Biome> biomes=new HashSet<>();
        //biomes.add(new skyBiome(builder));
        for(Biome b:biomes){
            BiomeManager.addBiome(BiomeManager.BiomeType.COOL,new BiomeManager.BiomeEntry(b,1000));
        }
    }
}
