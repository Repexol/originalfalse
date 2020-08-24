package originalFalse.dps.hard.zycdojar;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class fire {
    @SubscribeEvent
    public static void onWalk(PlayerSleepInBedEvent event){
        if(!event.getPlayer().getEntityWorld().isRemote){
            PhantomEntity entity=new PhantomEntity(EntityType.PHANTOM,event.getPlayer().getEntityWorld());
            entity.setPosition(event.getPos().getX(),event.getPos().getY(),event.getPos().getZ());
            event.getPlayer().getEntityWorld().addEntity(entity);
        }
    }
}
