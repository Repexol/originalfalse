package originalFalse.data.values;

import net.minecraft.nbt.*;

import java.util.ArrayList;
import java.util.List;

public class listData implements data<List<data<?>>>{
    List<data<?>> data=new ArrayList<>();
    @Override
    public void readFromNBT(INBT nbt) {
        ListNBT nbt1= (ListNBT) nbt;
        for(INBT inbt:nbt1){
            if(inbt instanceof StringNBT) {
                data.add( new stringData(inbt.getString()));
            }else if(inbt instanceof IntNBT){
                data.add(new intData(((IntNBT)inbt).getInt()));
            }else if(inbt instanceof DoubleNBT){
                data.add(new doubleData(((DoubleNBT) inbt).getDouble()));
            }
        }
    }

    @Override
    public INBT serialToNbt() {
        ListNBT nbt=new ListNBT();
        for(originalFalse.data.values.data<?> data1:data){
            nbt.add(data1.serialToNbt());
        }
        return nbt;
    }

    @Override
    public List<originalFalse.data.values.data<?>> getData() {
        return data;
    }
}
