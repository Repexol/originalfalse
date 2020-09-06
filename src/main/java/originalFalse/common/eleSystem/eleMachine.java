package originalFalse.common.eleSystem;

import net.minecraft.tileentity.TileEntityType;

public abstract class eleMachine extends eleRes{
    public eleMachine(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    protected boolean takeEle(int take){
        if(ele>=take){
            ele-=take;
            markDirty();
            return true;
        }else {
            return false;
        }
    }
}
