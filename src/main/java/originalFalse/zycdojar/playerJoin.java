package originalFalse.zycdojar;

import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.zycdojar.api.wrapper.LevelSystem;

@Mod.EventBusSubscriber
public class playerJoin {
    @SubscribeEvent
    public static void onJoin(EntityJoinWorldEvent event){
        if(!event.getWorld().isRemote){
            if(event.getEntity() instanceof PlayerEntity) {
                LevelSystem.markSync();
                event.getEntity().sendMessage(new StringTextComponent("欢迎来到Zycdojar-奇妙幻想的世界"));
            }
        }
    }
}
