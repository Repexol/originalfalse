package originalFalse.zycdojar.api.wrapper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;

/**
 * 合成api
 */
public interface craftHandle {
    public ItemStack craft(Map<Item,Integer> items);
}
