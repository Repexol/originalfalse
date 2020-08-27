package originalFalse.nethercraft.zycdojar.items;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import originalFalse.zycdojar.item.tier.stdTier;

public class netherite_axe extends AxeItem {
    public netherite_axe(Properties builder) {
        super(new stdTier(), 0-new stdTier().getAttackDamage(), 1.88f, builder);
        setRegistryName("neterite_axe");
    }
}
