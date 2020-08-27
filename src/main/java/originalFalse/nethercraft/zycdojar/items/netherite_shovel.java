package originalFalse.nethercraft.zycdojar.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;
import originalFalse.zycdojar.item.tier.stdTier;

public class netherite_shovel extends ShovelItem {
    public netherite_shovel(Properties builder) {
        super(new stdTier(), 0-new stdTier().getAttackDamage(), 1.88f, builder);
        setRegistryName("neterite_shovel");
    }
}
