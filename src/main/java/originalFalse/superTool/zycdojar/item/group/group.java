package originalFalse.superTool.zycdojar.item.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import originalFalse.superTool.zycdojar.item.designChart;
import originalFalse.superTool.zycdojar.main;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.item.group.originalFalseGroup;

public class group extends ItemGroup{
    public static final ItemGroup gmy_group = new group(ItemGroup.GROUPS.length, "weapon_addon");
    public group(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(main.icon);
    }
}
