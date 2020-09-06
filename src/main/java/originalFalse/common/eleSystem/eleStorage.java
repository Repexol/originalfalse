package originalFalse.common.eleSystem;

import net.minecraft.tileentity.TileEntityType;

public abstract class eleStorage extends eleRes implements eleProv{

    public eleStorage(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    protected void onTick() {

    }

    @Override
    public boolean canGive(int thatEle) {
        return ele>0;
    }
}
