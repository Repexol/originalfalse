package originalFalse.zycdojar.api;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import originalFalse.zycdojar.api.client.ClientRequest;
import originalFalse.zycdojar.api.wrapper.LevelSender;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class messageRegist {

        @SubscribeEvent
        public static void onCommonSetup(FMLCommonSetupEvent event) {
            LevelSender.registerMessage();
            ClientRequest.registerMessage();
        }
}
