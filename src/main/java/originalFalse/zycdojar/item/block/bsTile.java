package originalFalse.zycdojar.item.block;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.HashMap;
import java.util.Map;

public class bsTile extends TileEntity {
    public HashMap<Item, Integer> items=new HashMap<>();
    public int count;
    public bsTile() {
        super(TileEntityTypeRegistry.bsT);
    }
}
