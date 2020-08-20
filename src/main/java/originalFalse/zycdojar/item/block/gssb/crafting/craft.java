package originalFalse.zycdojar.item.block.gssb.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.lwjgl.system.CallbackI;
import originalFalse.zycdojar.api.wrapper.craftHandle;
import originalFalse.zycdojar.event.registyevent.itemregister;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class craft {
    public static Set<craftHandle> craftHandles=new HashSet<>();
    public static ItemStack craft(Map<Item,Integer> map){
        if(!(map.get(Items.IRON_INGOT) ==null)) {
            if(map.get(Items.IRON_INGOT)==1){
                if(!(map.get(Items.REDSTONE) ==null))
                if (map.get(Items.REDSTONE)==1){
                    return new ItemStack(itemregister.gwi,1);
                }
                if(!(map.get(itemregister.gwi) ==null))
                if(map.get(itemregister.gwi)==5){
                    if(!(map.get(Items.NETHER_STAR) ==null))
                    if(map.get(Items.NETHER_STAR)==1)
                    return new ItemStack(itemregister.ManaCorei,1);
                }
            }
        }
        if(!(map.get(Items.NETHER_STAR) ==null))
        if(map.get(Items.NETHER_STAR)==2){
            if(!(map.get(itemregister.gwi) ==null)) {
                if (map.get(itemregister.gwi) == 3) {
                    return new ItemStack(itemregister.bladeroot, 1);
                }
                if (map.get(itemregister.gwi) == 10) {
                    return new ItemStack(itemregister.getSSBI, 1);
                }
            }
        }
        for(craftHandle handle:craftHandles){
            ItemStack stack=handle.craft(map);
            if(stack!=null){
                return stack;
            }
        }
        return null;
    }
}
