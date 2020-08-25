package originalFalse.tech.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.main;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;
import originalFalse.zycdojar.event.registyevent.itemregister;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 自然之盾
 */
@Mod.EventBusSubscriber
public class NEShied extends Item {
    public NEShied(Properties properties) {
        super(properties);
        setRegistryName("ne_shield");
    }

    /**
     * 受伤的时候小号耐久抵挡
     * @param event
     */
    @SubscribeEvent
    public static void onAttact(LivingDamageEvent event){
        if(event.getEntity() instanceof ServerPlayerEntity){
            ServerPlayerEntity player= (ServerPlayerEntity) event.getEntity();
            if(player.getHeldItem(Hand.OFF_HAND).getItem().equals(main.neshied)){
                player.getHeldItem(Hand.OFF_HAND).setDamage(player.getHeldItem(Hand.OFF_HAND).getDamage()+5);
                event.setCanceled(true);
            }
        }
    }

    /**
     * 尝试消耗自然之息回复耐久
     * @param stack
     * @param worldIn
     * @param entityIn
     * @param itemSlot
     * @param isSelected
     */
    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!worldIn.isRemote){
            if(entityIn instanceof PlayerEntity){
                PlayerEntity player= (PlayerEntity) entityIn;
                if(NESystem.getNE(player)>=100){
                    if(stack.getDamage()>=10){
                        if(NESystem.removeNE(player,1)){
                            stack.setDamage(stack.getDamage()-10);
                        }
                    }
                }
            }
        }
    }
    /*

    @SubscribeEvent
    public static void onTick(TickEvent event){
        PlayerEntity player= (PlayerEntity) entityIn;
        if(NESystem.getNE(player)>=100){
            if(stack.getDamage()>=10){
                if(NESystem.removeNE(player,1)){
                    stack.setDamage(stack.getDamage()-10);
                }
            }
        }
    }*/

    /**
     * 右键强制回复耐久
     * @param worldIn
     * @param playerIn
     * @param handIn
     * @return
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        PlayerEntity player= playerIn;
        ItemStack stack=player.getHeldItem(handIn);
            if(stack.getDamage()>=10){
                if(NESystem.removeNE(player,1)){
                    stack.setDamage(stack.getDamage()-10);
                }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    /**
     * 添加注释
     * @param stack
     * @param worldIn
     * @param tooltip
     * @param flagIn
     */
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("originalfalse.tech.text.neshied"));
        tooltip.add(new TranslationTextComponent("originalfalse.tech.text.naijiu",(stack.getMaxDamage()-stack.getDamage())+"/"+stack.getMaxDamage()));
    }
}
