package originalFalse.nethercraft.zycdojar.netherCraftGroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import originalFalse.nethercraft.zycdojar.main;

public class group extends ItemGroup{
    public static final ItemGroup gmy_group = new group(ItemGroup.GROUPS.length, "nethercraft_addon");
    public group(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(main.builderI,1);
    }
}
