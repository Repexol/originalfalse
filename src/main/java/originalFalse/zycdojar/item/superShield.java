package originalFalse.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;
import originalFalse.zycdojar.event.registyevent.itemregister;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class superShield extends Item {
    public superShield(Properties properties) {
        super(properties);
        setRegistryName("super_shield");
    }

    /**
     * 玩家受伤时
     * 消耗魔力抵挡
     * @param event
     */
    @SubscribeEvent
    public static void onAttact(LivingDamageEvent event){
        if(event.getEntity() instanceof ServerPlayerEntity){
            ServerPlayerEntity player= (ServerPlayerEntity) event.getEntity();
            if(player.getHeldItem(Hand.OFF_HAND).getItem().equals(itemregister.superShield)){
                worldSaveData data=worldSaveData.getInstance(player.getServerWorld());
                //如果受伤小于伤害上限
                if(event.getAmount()<=data.getLevel(player)/2){
                    //扣除魔力免伤
                    if(LevelSystem.removeMana(player, Math.round(event.getAmount()*2),player.getServerWorld())) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("originalfalse.text.sshied"));
    }
}
