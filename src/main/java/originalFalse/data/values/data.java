package originalFalse.data.values;

import net.minecraft.nbt.INBT;

public interface data<T> {
    public void readFromNBT(INBT nbt);
    public INBT serialToNbt();
    public T getData();
}
