package originalFalse.data.values;

import net.minecraft.nbt.*;

import java.util.HashMap;
import java.util.Map;

public class mapData implements data<HashMap<String,data<?>>>{
    private HashMap<String,data<?>> dataMap=new HashMap<>();
    public void put(String key,data<?> value){
        dataMap.put(key,value);
    }
    public data<?> get(String key){
        return dataMap.get(key);
    }


    @Override
    public void readFromNBT(INBT nbt) {
        if(nbt instanceof CompoundNBT){
            CompoundNBT This= (CompoundNBT) nbt;
            for(String key:This.keySet()){
                if(This.get(key) instanceof StringNBT) {
                    dataMap.put(key, new stringData(This.getString(key)));
                }else if(this.get(key) instanceof IntNBT){
                    dataMap.put(key,new intData(This.getInt(key)));
                }else if(this.get(key) instanceof DoubleNBT){
                    dataMap.put(key,new doubleData(This.getDouble(key)));
                }
            }
        }
    }

    @Override
    public INBT serialToNbt() {
        CompoundNBT nbt=new CompoundNBT();
        for(String key:dataMap.keySet()){
            nbt.put(key,dataMap.get(key).serialToNbt());
        }
        return nbt;
    }

    @Override
    public HashMap<String, data<?>> getData() {
        return dataMap;
    }
}
