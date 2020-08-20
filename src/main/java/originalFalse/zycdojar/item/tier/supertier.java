package originalFalse.zycdojar.item.tier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;
import net.minecraft.item.crafting.Ingredient;

public class supertier implements IItemTier {


    @Override
    public int getMaxUses() {
        return 9999999;
    }

    @Override
    public float getEfficiency() {
        return 20;
    }

    @Override
    public float getAttackDamage() {
        return 2333333;
    }

    @Override
    public int getHarvestLevel() {
        return 8848;
    }

    @Override
    public int getEnchantability() {
        return 42;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return ItemTier.WOOD.getRepairMaterial();
    }
}
