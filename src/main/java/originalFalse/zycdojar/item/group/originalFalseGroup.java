package originalFalse.zycdojar.item.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.item.creativeBlade;

public class originalFalseGroup extends ItemGroup {
    public static final ItemGroup my_group = new originalFalseGroup(ItemGroup.GROUPS.length, "originalfalse");
    public originalFalseGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(itemregister.icon);
    }
}
