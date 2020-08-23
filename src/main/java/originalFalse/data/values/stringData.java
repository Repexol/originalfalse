package originalFalse.data.values;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;

public class stringData implements data<String>{
    String message;

    public stringData(String message){
        this.message=message;
    }
    @Override
    public void readFromNBT(INBT nbt) {
        message=nbt.getString();
    }

    @Override
    public INBT serialToNbt() {
        return StringNBT.valueOf(message);
    }

    @Override
    public String getData() {
        return message;
    }
}
