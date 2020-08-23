package originalFalse.zycdojar.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
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
    public static KeyBinding key=new KeyBinding("key.levelsystem",
            KeyConflictContext.IN_GAME,
            KeyModifier.CONTROL,
            InputMappings.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.category.levelsystem");
    @SubscribeEvent
    public static void onKeyboard(InputEvent.KeyInputEvent event){
        if(key.isPressed()){
            ClientPlayerEntity player= Minecraft.getInstance().player;
            LevelSystem.send(player.getEntityWorld(),player);
        }
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(key);
    }
}
