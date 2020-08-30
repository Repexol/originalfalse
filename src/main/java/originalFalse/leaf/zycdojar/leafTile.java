package originalFalse.leaf.zycdojar;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.MathHelper;
import originalFalse.leaf.zycdojar.entity.leaf;
import originalFalse.nuclearCraft.block.nuclearTile;

public class leafTile extends TileEntity implements ITickableTileEntity {
    public static TileEntityType<leafTile> type=
            (TileEntityType<leafTile>) TileEntityType.Builder.create(()->{return new leafTile();}, main.leafSpawner).build(null).setRegistryName(main.leafSpawner.getRegistryName());
    public leafTile() {
        super(type);
    }

    @Override
    public void tick() {
        if(!world.isRemote) {
            PlayerEntity playerEntity = world.getClosestPlayer(getPos().getX(), getPos().getY(), getPos().getZ(), 10, true);
            if (playerEntity != null) {
                leaf Leaf = new leaf(main.leaft, world);
                Leaf.setLocationAndAngles(playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 10.0F);
                world.addEntity(Leaf);
                world.setBlockState(getPos(), Blocks.AIR.getDefaultState());
            }
        }
    }
}
