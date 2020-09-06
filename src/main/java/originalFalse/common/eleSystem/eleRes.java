package originalFalse.common.eleSystem;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class eleRes extends eleMeter implements ITickableTileEntity {
    public eleRes(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void tick() {
        if(ele<getEleMax()) {
            int[][] abroad = {{0, 1, 0}, {0, 0, 1}, {1, 0, 0}, {0, -1, 0}, {0, 0, -1}, {-1, 0, 0}};
            for (int[] pos : abroad) {
                BlockPos post = this.pos.add(pos[0], pos[1], pos[2]);
                if (world.getTileEntity(post) instanceof eleMeter)
                    if (world.getTileEntity(post) instanceof eleProv) {
                        eleMeter meterTile = (eleMeter) world.getTileEntity(post);
                        eleProv provTile = (eleProv) world.getTileEntity(post);
                        if (provTile.canGive(ele)) {
                            meterTile.ele--;
                            ele++;
                            markDirty();
                            meterTile.markDirty();
                        }
                    }
            }
        }
        onTick();
    }
}
