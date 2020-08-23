package originalFalse.data.values;

import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.INBT;

public class doubleData implements data<Double>{
    double data;
    public doubleData(double data){
        this.data=data;
    }
    @Override
    public void readFromNBT(INBT nbt) {
        DoubleNBT inbt= (DoubleNBT) nbt;
        data=inbt.getDouble();
    }

    @Override
    public INBT serialToNbt() {
        return DoubleNBT.valueOf(data);
    }

    @Override
    public Double getData() {
        return data;
    }
}
