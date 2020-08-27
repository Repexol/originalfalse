package originalFalse.nethercraft.zycdojar.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;
import originalFalse.zycdojar.item.tier.stdTier;

public class netherite_pickaxe extends PickaxeItem {
    public netherite_pickaxe(Properties builder) {
        super(new stdTier(), (int) (0-new stdTier().getAttackDamage()), 1.88f, builder);
        setRegistryName("netherite_pickaxe");
    }
}
