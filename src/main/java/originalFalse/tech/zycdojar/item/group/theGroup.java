package originalFalse.tech.zycdojar.item.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import originalFalse.zycdojar.item.group.originalFalseGroup;

public class theGroup extends ItemGroup {
    public static final ItemGroup gmy_group = new theGroup(ItemGroup.GROUPS.length, "tech_addon");
    public theGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.ENDER_PEARL,1);
    }
}
