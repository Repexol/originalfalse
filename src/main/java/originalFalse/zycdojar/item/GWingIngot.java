package originalFalse.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
@Mod.EventBusSubscriber
public class GWingIngot extends Item {
    public GWingIngot(Properties properties) {
        super(properties);
        setRegistryName("gwi");
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("originalfalse.text.craftres"));
        tooltip.add(new TranslationTextComponent("originalfalse.text.GWI"));
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GWingIngot){
            return true;
        }
        return false;
    }
}
