package originalFalse.common.eleSystem;

import net.minecraft.tileentity.TileEntityType;

public abstract class eleGenerator extends eleMeter implements eleProv{
    public eleGenerator(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public boolean canGive(int thatEle) {
        return ele>0;
    }
}
