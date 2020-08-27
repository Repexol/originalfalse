package originalFalse.tech.zycdojar.item.blockItem;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import originalFalse.tech.zycdojar.main;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 没啥好说的
 */
public class nitunengfadianji extends BlockItem {
    public nitunengfadianji(Properties builder) {
        super(main.nitunengfadianji, builder);
        setRegistryName("dirt_gent");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("泥土是自然产生的"));
    }
}
