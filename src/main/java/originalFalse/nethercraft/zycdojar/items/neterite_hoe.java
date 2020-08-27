package originalFalse.nethercraft.zycdojar.items;

import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import originalFalse.zycdojar.item.tier.stdTier;

public class neterite_hoe extends HoeItem {
    public neterite_hoe(Properties builder) {
        super(new stdTier(),0.1f,builder);
        setRegistryName("neterite_hoe");
    }
}
