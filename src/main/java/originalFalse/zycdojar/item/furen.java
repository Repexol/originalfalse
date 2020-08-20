package originalFalse.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class furen extends Item{
    public furen(Properties properties) {
        super(properties);
        setRegistryName("furen");
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("把红铁砸成结晶，会丧失掉8成。"));
        tooltip.add(new StringTextComponent("两块结晶融合，摁，这硬度不错。"));
        tooltip.add(new StringTextComponent("注入两条龙蛋，真好，可是拿不动。"));
        tooltip.add(new StringTextComponent("我想：也许应该裹上一圈木板。"));
    }
}
