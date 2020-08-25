package originalFalse.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.item.tier.supertier;

import javax.annotation.Nullable;
import java.util.List;
import java.util.logging.Level;

@Mod.EventBusSubscriber
public class superItem extends SwordItem {
    //private int seed=233;
    public superItem(Properties builder) {
        super(new supertier(), (int) (3-new supertier().getAttackDamage()),1.6f,builder);
        setRegistryName("superitem");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote){
            LevelSystem.send(worldIn,playerIn);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
    /*@Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {
        CompoundNBT mainNBT=nbt.getCompound("originalData");
        if(mainNBT.getInt("seed")==0){
            seed=Math.abs(new Random().nextInt());
            mainNBT.put("seed", IntNBT.valueOf(seed));
        }else {
            seed=mainNBT.getInt("seed");
        }
        nbt.put("originalData",mainNBT);
        return super.updateItemStackNBT(nbt);
    }*/


    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("originalfalse.text.sitem1"));
        tooltip.add(new TranslationTextComponent("originalfalse.text.sitem2"));
    }

    /**
     * 当玩家攻击时
     * @param event
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onKill(AttackEntityEvent event){
        PlayerEntity player=event.getPlayer();
        if(player instanceof ServerPlayerEntity)
        if(event.getTarget() instanceof LivingEntity) {
            if(player.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.superItem)) {
                worldSaveData data = worldSaveData.get(event.getEntity().getEntityWorld());
                int deMana;
                //消耗的魔力和伤害比例
                if(((LivingEntity) event.getTarget()).getMaxHealth()>(5 * data.getLevel(player))){
                    deMana=5 * data.getLevel(player);
                }else {
                    deMana=Math.round(((LivingEntity) event.getTarget()).getMaxHealth());
                }
                if(LevelSystem.removeMana(player,deMana,((ServerPlayerEntity) player).getServerWorld())) {
                    LivingEntity target = (LivingEntity) event.getTarget();
                    //伤害
                    //target.setHealth(target.getHealth() - (5 * data.getLevel(player)));
                    target.attackEntityFrom(DamageSource.OUT_OF_WORLD,(5 * data.getLevel(player)));
                }
            }
        }
    }

}
