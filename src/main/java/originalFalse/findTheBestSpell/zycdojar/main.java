package originalFalse.findTheBestSpell.zycdojar;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class main {
    public static ModDimensions dimensions;
    @SubscribeEvent
    public static void onWorldRegister(RegistryEvent.Register<ModDimension> event){
        IForgeRegistry<ModDimension> registry=event.getRegistry();
        dimensions=new ModDimensions();
        registry.register(dimensions);
    }
    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event){
    }
}
