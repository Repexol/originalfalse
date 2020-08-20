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
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class RedstoneIngot extends Item {
    public RedstoneIngot(Properties properties) {
        super(properties);
        setRegistryName("chj");
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("������ȡ�ϳɳɹ�"));
        tooltip.add(new TranslationTextComponent("��˵���Եֿ��˺�Ӵ(��kill��ɱ����s)"));
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RedstoneIngot){
            return true;
        }
        return false;
    }
}
