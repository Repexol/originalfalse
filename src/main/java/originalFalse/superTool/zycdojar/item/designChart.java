package originalFalse.superTool.zycdojar.item;

//import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import originalFalse.superTool.zycdojar.main;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class designChart extends Item {
    public designChart(Properties properties) {
        super(properties);
        setRegistryName("design_chart");
    }
    public static Map<String,Integer> getNeed(ItemStack itemStack){
        if(itemStack.getItem().equals(main.icon)){
            int seed=itemStack.getOrCreateTag().getInt("designSeed");
            Map<String, Integer> out=new HashMap<>();
            out.put("iron",Math.abs(new Random(seed+123).nextInt()%32));
            out.put("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons",Math.abs(new Random(seed+233).nextInt()%7));
            out.put("RTX_please",Math.abs(new Random(seed+666).nextInt()%32));
            out.put("Author.Zycdojar(qq.3321019091)",Math.abs(new Random(seed*1314).nextInt()%32));
            return out;
        }else {
            return null;
        }
    }
    public static void initialDC(ItemStack itemStack){
        if(itemStack.getItem().equals(main.icon)){
            itemStack.getOrCreateTag().put("designSeed", IntNBT.valueOf(Math.abs(new Random().nextInt())%32767));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        Map<String,Integer> need=getNeed(stack);
        tooltip.add(new TranslationTextComponent("originalfalse.weapon.text.items"));
        tooltip.add(new StringTextComponent(need.getOrDefault("iron",0)+ Items.IRON_INGOT.getName().getString()));
        tooltip.add(new StringTextComponent(need.getOrDefault("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons",0)+ Items.NETHER_STAR.getName().getString()));
        tooltip.add(new StringTextComponent(need.getOrDefault("RTX_please",0)+ Items.DIAMOND.getName().getString()));
        tooltip.add(new StringTextComponent(need.getOrDefault("Author.Zycdojar(qq.3321019091)",0)+ Items.DIRT.getName().getString()));
        tooltip.add(new TranslationTextComponent("originalfalse.weapon.text.seed",stack.getOrCreateTag().getInt("designSeed")));
    }

}
