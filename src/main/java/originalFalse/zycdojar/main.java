package originalFalse.zycdojar;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.api.wrapper.RequestHandle;
import originalFalse.zycdojar.api.wrapper.RequestManager;
import originalFalse.zycdojar.api.wrapper.client;
import originalFalse.zycdojar.item.block.ManaCore;

import java.util.Random;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("originalfalse")
public class main
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public main() {
        ManaCore.initialSpells();
        LevelSystem.initial();
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        //如果服务器发送的数据中魔力部分未能初始化，那么就要求服务器初始化
        RequestManager.registerHandle(new RequestHandle() {
            @Override
            public boolean handle(JsonObject object) {
                return false;
            }
            //在客户端执行
            @Override
            public JsonObject getState() {
                JsonObject object=new JsonObject();
                if(client.mana==0){
                    object.addProperty("reloadMana",true);
                    object.addProperty("uuid", Minecraft.getInstance().player.getUniqueID().toString());
                }else {
                    object.addProperty("reloadMana",false);
                }
                return object;
            }
            //会在服务器执行
            @Override
            public void handleState(JsonObject object) {
                if(object.get("reloadMana").getAsBoolean()){
                    LevelSystem.initMana(object.get("uuid").getAsString());
                }
            }
        },"levelReturn");
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        for(int i=0;i<=10000;i++){
            if(Math.abs(new Random(i).nextInt()) % 160==159){
                LOGGER.info(i+" is The God's seed");
            }
        }
        LOGGER.info("");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("originalfalse", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
