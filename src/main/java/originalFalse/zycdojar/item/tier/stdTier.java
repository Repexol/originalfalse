package originalFalse.zycdojar.item.tier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class stdTier implements IItemTier {
    @Override
    public int getMaxUses() {
        return 520;
    }

    @Override
    public float getEfficiency() {
        return 20;
    }

    @Override
    public float getAttackDamage() {
        return 7;
    }

    @Override
    public int getHarvestLevel() {
        return 8;
    }

    @Override
    public int getEnchantability() {
        return 43;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return null;
    }
}
