package originalFalse.zycdojar.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.zycdojar.event.registyevent.itemregister;

@Mod.EventBusSubscriber()
public class RestoneIngot {
    /**
     * 催化剂抵抗伤害
     * @param event
     */
    @SubscribeEvent
    public static void onEno(LivingDamageEvent event){
        if(event.getEntity() instanceof PlayerEntity) {
            PlayerEntity entity = (PlayerEntity) event.getEntity();
            if (entity.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.chj) ){
                entity.getHeldItem(Hand.MAIN_HAND).setCount(entity.getHeldItem(Hand.MAIN_HAND).getCount()-1);
                event.setCanceled(true);
            }
        }
    }
}
