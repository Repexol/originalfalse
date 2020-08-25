package originalFalse.zycdojar.item.block.bs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import originalFalse.zycdojar.api.wrapper.craftHandle;
import originalFalse.zycdojar.event.registyevent.itemregister;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 召唤台合成
 */
public class craft {
    public static Set<craftHandle> craftHandles=new HashSet<>();
    public static ItemStack craft(HashMap<Item,Integer> map){
        if(!(map.get(Items.OAK_LOG) ==null)){
            if(map.get(Items.OAK_LOG)==5){
                return new ItemStack(Items.DIRT,40);
            }
            if(map.get(Items.OAK_LOG)==1){
                if(!(map.get(itemregister.RedIci) ==null)){
                    if(map.get(itemregister.RedIci)==2){
                        if(!(map.get(Items.DRAGON_EGG) ==null)){
                            if(map.get(Items.DRAGON_EGG)==2){
                                return new ItemStack(itemregister.Furen);
                            }
                        }
                    }
                }
            }
        }
        //如果找不到合成就在handle里找
        for(craftHandle handle:craftHandles){
            ItemStack stack=handle.craft(map);
            if(stack!=null){
                return stack;
            }
        }
        return null;
    }
}
