package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import originalFalse.common.eleSystem.eleProv;
import originalFalse.common.eleSystem.eleRes;
import originalFalse.tech.zycdojar.main;

public class wireTile extends eleRes implements eleProv {
    public static TileEntityType<?> type=
            TileEntityType.Builder.create(()->{return new wireTile();}, main.wire).build(null).setRegistryName(main.wire.getRegistryName());
    public wireTile() {
        super(type);
    }

    @Override
    public boolean canGive(int thatEle) {
        if(ele>=0)
            if(thatEle<ele)
                return true;
            else
                return false;
        else
            return false;
    }

    @Override
    protected void onTick() {
    }

    @Override
    protected int getEleMax() {
        return 1000;
    }
}
