package originalFalse.tech.zycdojar.client;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import originalFalse.tech.zycdojar.block.tile.mawoderendoushidashabi;
import originalFalse.tech.zycdojar.client.block.wuzhongshenyouyishi;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class main {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        ClientRegistry.bindTileEntityRenderer(mawoderendoushidashabi.type, (tileEntityRendererDispatcher -> {
            return new wuzhongshenyouyishi(tileEntityRendererDispatcher);
        }));
    }
}
