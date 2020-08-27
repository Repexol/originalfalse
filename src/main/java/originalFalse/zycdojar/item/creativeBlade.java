package originalFalse.zycdojar.item;


import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import originalFalse.zycdojar.event.adminTool;
import originalFalse.zycdojar.event.registyevent.itemregister;

import originalFalse.zycdojar.item.tier.supertier;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Properties;

@Mod.EventBusSubscriber(modid = "originalfalse")
public class creativeBlade extends PickaxeItem {
    public creativeBlade(Properties properties) {
        super(new supertier(), (int) (5-new supertier().getAttackDamage()),8f,properties);
        setRegistryName("admin_tool");
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("originalfalse.text.sq"));
        tooltip.add(new TranslationTextComponent("originalfalse.text.onBlade","本剑属于Zycddj"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(playerIn.getHeldItem(Hand.OFF_HAND).equals(itemregister.icon))
        if(adminTool.isAdmin(playerIn)) {

        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof creativeBlade){
            return true;
        }
        return false;
    }
}
