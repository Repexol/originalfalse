package originalFalse.leaf.zycdojar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import originalFalse.tech.zycdojar.block.CLASS;

import javax.annotation.Nullable;

public class leafSpawner extends Block {
    public leafSpawner() {
        super(Block.Properties.create(Material.IRON).notSolid().hardnessAndResistance(-1,Float.MAX_VALUE));
        setRegistryName("leaf_spawner");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new leafTile();
    }
}
