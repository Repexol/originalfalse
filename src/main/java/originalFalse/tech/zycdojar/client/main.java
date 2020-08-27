package originalFalse.tech.zycdojar.client;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import originalFalse.tech.zycdojar.block.tile.dengjiajiaohuanyiTile;
import originalFalse.tech.zycdojar.block.tile.mawoderendoushidashabi;
import originalFalse.tech.zycdojar.client.block.dengjiajiaohuanyi;
import originalFalse.tech.zycdojar.client.block.wuzhongshenyouyishi;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class main {
    /**
     * 客户端渲染设置
     * 详情https://neutrino.v2mcdev.com/specialrender/ter.html
     * @param event
     */
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        ClientRegistry.bindTileEntityRenderer(mawoderendoushidashabi.type, (tileEntityRendererDispatcher -> {
            return new wuzhongshenyouyishi(tileEntityRendererDispatcher);
        }));
        ClientRegistry.bindTileEntityRenderer(dengjiajiaohuanyiTile.type,(tileEntityRendererDispatcher -> {
            return new dengjiajiaohuanyi(tileEntityRendererDispatcher);
        }));
    }
}
