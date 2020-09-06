package originalFalse.common.eleSystem;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class eleMeter extends TileEntity implements ITickableTileEntity{
    public int ele=0;
    public eleMeter(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    protected abstract void onTick();
    @Override
    public void read(CompoundNBT compound) {
        ele=compound.getInt("ele");
        super.read(compound);
    }
    protected abstract int getEleMax();

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("ele",IntNBT.valueOf(ele));
        return super.write(compound);
    }
    @Override
    public void tick() {
        onTick();
    }
}
