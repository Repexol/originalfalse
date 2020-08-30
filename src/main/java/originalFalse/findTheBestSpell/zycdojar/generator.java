package originalFalse.findTheBestSpell.zycdojar;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;

public class generator extends ChunkGenerator<GenerationSettings> {
    public generator(IWorld worldIn, BiomeProvider biomeProviderIn) {
        super(worldIn, biomeProviderIn, createDefault());
    }

    public static GenerationSettings createDefault() {
        GenerationSettings config = new GenerationSettings();
        config.setDefaultBlock(Blocks.IRON_ORE.getDefaultState());
        config.setDefaultFluid(Blocks.WATER.getDefaultState());
        return config;
    }
    @Override
    public void generateSurface(WorldGenRegion region, IChunk chunk) {
        BlockState bedrock=Blocks.BEDROCK.getDefaultState();
        BlockState ironOre=Blocks.STONE.getDefaultState();
        ChunkPos pos=chunk.getPos();
        BlockPos.Mutable bPos=new BlockPos.Mutable();

        int x;
        int z;

        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                chunk.setBlockState(bPos.setPos(x, 0, z), bedrock, false);
            }
        }

        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                int realx = pos.x * 16 + x;
                int realz = pos.z * 16 + z;
                int height = (int) (65 + Math.sin(realx / 20.0f) * 10 + Math.cos(realz / 20.0f) * 10);
                for (int y = 1; y < height; y++) {
                    chunk.setBlockState(bPos.setPos(x, y, z), ironOre, false);
                }
            }
        }
    }

    @Override
    public int getGroundHeight() {
        return world.getSeaLevel()+1;
    }

    @Override
    public void makeBase(IWorld worldIn, IChunk chunkIn) {

    }

    @Override
    public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type heightmapType) {
        return 0;
    }
}
