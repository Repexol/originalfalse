package originalFalse.tech.zycdojar;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class events {
    /**
     * 玩家死亡的时候显示awa
     * @param event
     */
    @SubscribeEvent
    public static void onDead(PlayerEvent.PlayerRespawnEvent event){
        if(event.getPlayer() instanceof ServerPlayerEntity){
            ServerPlayerEntity player= (ServerPlayerEntity) event.getPlayer();
            ServerWorld world=player.getServerWorld();
            player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.deadWither2"));
        }
    }
}
