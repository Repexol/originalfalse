package originalFalse.zycdojar.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;
import originalFalse.zycdojar.api.wrapper.LevelSystem;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class control {
    /**
     * R键查看玩家数据
     * @param event
     */
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientSetup(InputEvent.KeyInputEvent event) {
        //r的代号是82
        if(event.getKey()==82){
            LevelSystem.send(Minecraft.getInstance().world,Minecraft.getInstance().player);
        }
    }
}
