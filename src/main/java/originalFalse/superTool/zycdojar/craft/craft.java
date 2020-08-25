package originalFalse.superTool.zycdojar.craft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import originalFalse.superTool.zycdojar.main;
import originalFalse.zycdojar.api.wrapper.craftHandle;

import java.util.Map;

public class craft implements craftHandle {
    /**
     * 处理制图台的输出
     * @param items
     * @return 输出的物品，null代表需要在下一个handle里处理
     */
    @Override
    public ItemStack craft(Map<Item, Integer> items) {
        if(items.get(Items.IRON_INGOT)!=null)
            if(items.get(Items.IRON_INGOT)==4)
                if(items.get(Items.DIAMOND)!=null)
                    if(items.get(Items.DIAMOND)==1)
                        return new ItemStack(main.anvili,1);
        return null;
    }
}
