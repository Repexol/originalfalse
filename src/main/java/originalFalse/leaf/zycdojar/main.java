package originalFalse.leaf.zycdojar;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import originalFalse.leaf.zycdojar.entity.leaf;
import originalFalse.leaf.zycdojar.entity.shadow;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class main {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES;
    public static RegistryObject<EntityType<leaf>> leafEntity;
    public static EntityType<leaf> leaft;
    @SubscribeEvent
    public static void onEntityRegistation(RegistryEvent.Register<EntityType<?>> event) {
        leaft=EntityType.Builder.create((EntityType<leaf> entityType, World world) -> {
            return new leaf(entityType, world);
        }, EntityClassification.MISC).size(1f,1.8f).build("leaf");
        leaft.setRegistryName("leaf");
        event.getRegistry().register(leaft);
    }
    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event){
        ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, "originalfalse");
        /*leafEntity = ENTITY_TYPES.register("fall_leaf", () -> {
            return EntityType.Builder.create((EntityType<leaf> entityType, World world) -> {
                return new leaf(entityType, world);
            }, EntityClassification.MISC).size(1.8f, 1F).build("fall_leaf");
        });*/
    }
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event){
        RenderingRegistry.registerEntityRenderingHandler(leaft, (EntityRendererManager manager) -> {
            return new shadow(manager);
        });
    }
}
