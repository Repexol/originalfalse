package originalFalse.findTheBestSpell.zycdojar;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import originalFalse.zycdojar.event.registyevent.itemregister3;

import java.util.*;

public class BiomeProvider extends net.minecraft.world.biome.provider.BiomeProvider {
    public static List<Biome> biomes=new ArrayList<>(Arrays.asList(Biomes.PLAINS,Biomes.OCEAN, itemregister3.SkyBiome));
    public Random random;
    protected BiomeProvider() {
        super(new HashSet<>(biomes));
        random=new Random();
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return biomes.get(random.nextInt(3));
    }
}
