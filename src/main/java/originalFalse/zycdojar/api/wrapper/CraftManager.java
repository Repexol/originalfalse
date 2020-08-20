package originalFalse.zycdojar.api.wrapper;

import originalFalse.zycdojar.item.block.ManaCore;
import originalFalse.zycdojar.item.block.bs.craft;
import originalFalse.zycdojar.item.wand;

public class CraftManager {
    public static void registerOriginalCraft(craftHandle handle){
        originalFalse.zycdojar.item.block.gssb.crafting.craft.craftHandles.add(handle);
    }
    public static void registerSpawnerCraft(craftHandle handle){
        craft.craftHandles.add(handle);
    }
}
