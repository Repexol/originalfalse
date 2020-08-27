package originalFalse.nethercraft.zycdojar.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import originalFalse.zycdojar.item.tier.stdTier;

public class netherite_sword extends SwordItem {
    public netherite_sword(Properties builder) {
        super(new stdTier(), (int) (50-new stdTier().getAttackDamage()), 1.6f, builder);
        setRegistryName("neterite_sword");
    }
}
