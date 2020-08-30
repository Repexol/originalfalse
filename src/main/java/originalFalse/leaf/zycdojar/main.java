package originalFalse.leaf.zycdojar;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.DamageSource;
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
import originalFalse.tech.zycdojar.item.group.theGroup;
import originalFalse.zycdojar.api.wrapper.SpellManager;
import originalFalse.zycdojar.api.wrapper.spellHandle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class main {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES;
    //public static RegistryObject<EntityType<leaf>> leafEntity;
    public static Item leafSpawnerI;
    public static Block leafSpawner;
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
    public static void onBlockRegistation(RegistryEvent.Register<Block> event) {
        leafSpawner=new leafSpawner();
        event.getRegistry().register(leafSpawner);
    }
    @SubscribeEvent
    public static void onItemRegistation(RegistryEvent.Register<Item> event){
        Item.Properties properties=new Item.Properties();
        properties.group(ItemGroup.MATERIALS);
        properties.maxStackSize(64);
        leafSpawnerI=new BlockItem(leafSpawner,properties).setRegistryName("leaf_spawner");
        event.getRegistry().register(leafSpawnerI);
    }
    @SubscribeEvent
    public static void onTileRegist(RegistryEvent.Register<TileEntityType<?>> event){
        event.getRegistry().register(leafTile.type);
    }
    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event){
        ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, "originalfalse");
        SpellManager.registerSpellHandle(new spellHandle() {
            @Override
            public boolean spell(String spell, LivingEntity target, ServerPlayerEntity player) {
                if(spell.equals("killCommand")){
                    target.attackEntityFrom(DamageSource.IN_WALL,Float.MAX_VALUE);
                    return true;
                }
                return false;
            }
        });
    }
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event){
        RenderingRegistry.registerEntityRenderingHandler(leaft, (EntityRendererManager manager) -> {
            return new shadow(manager);
        });
    }
}
