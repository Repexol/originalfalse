package originalFalse.data.values;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;

public class boolData implements data<Boolean>{
    boolean data;
    public boolData(boolean data){
        this.data=data;
    }
    @Override
    public void readFromNBT(INBT nbt) {
        IntNBT nbt1= (IntNBT) nbt;
        if(nbt1.getInt()==0){
            data=false;
        }else {
            data=true;
        }
    }

    @Override
    public INBT serialToNbt() {
        if(data) {
            return IntNBT.valueOf(1);
        }else {
            return IntNBT.valueOf(0);
        }
    }

    @Override
    public Boolean getData() {
        return data;
    }
}
