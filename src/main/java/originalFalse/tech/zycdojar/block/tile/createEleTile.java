package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.tileentity.TileEntityType;
import originalFalse.common.eleSystem.eleMeter;
import originalFalse.common.eleSystem.eleProv;
import originalFalse.common.eleSystem.eleRes;
import originalFalse.tech.zycdojar.main;

public class createEleTile extends eleMeter implements eleProv {
    public static TileEntityType<?> type=
            TileEntityType.Builder.create(()->{return new createEleTile();}, main.cele).build(null).setRegistryName(main.cele.getRegistryName());
    public createEleTile() {
        super(type);
    }

    @Override
    public boolean canGive(int thatEle) {
        if(thatEle<ele)
            return true;
        else
            return false;
    }

    @Override
    protected void onTick() {
        ele=10000;
    }

    @Override
    protected int getEleMax() {
        return 10000;
    }
}
