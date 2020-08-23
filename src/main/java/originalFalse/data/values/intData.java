package originalFalse.data.values;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;

public class intData implements data<Integer>{
    int data;
    public intData(int data){
        this.data=data;
    }

    @Override
    public void readFromNBT(INBT nbt) {
        IntNBT nbt1= (IntNBT) nbt;
        data=nbt1.getInt();
    }

    @Override
    public INBT serialToNbt() {
        return IntNBT.valueOf(data);
    }

    @Override
    public Integer getData() {
        return data;
    }
}
