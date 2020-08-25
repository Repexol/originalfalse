package originalFalse.zycdojar.item.block;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.HashMap;
import java.util.Map;

public class bsTile extends TileEntity {
    //物品列表
    public HashMap<Item, Integer> items=new HashMap<>();
    //物品数量
    public int count;
    public bsTile() {
        super(TileEntityTypeRegistry.bsT);
    }
}
