package originalFalse.zycdojar.item.block;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class gssbTile extends TileEntity {
    //物品列表
    public Map<Item, Integer> yss=new HashMap<>();
    public gssbTile() {
        super(TileEntityTypeRegistry.gssbET);
    }
}
