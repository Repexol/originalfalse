package originalFalse.nethercraft.zycdojar;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;
import originalFalse.nethercraft.zycdojar.block.builder;
import originalFalse.nethercraft.zycdojar.block.builderTile;
import originalFalse.nethercraft.zycdojar.gui.containter;
import originalFalse.nethercraft.zycdojar.gui.screen;
import originalFalse.nethercraft.zycdojar.items.*;
import originalFalse.nethercraft.zycdojar.netherCraftGroup.group;
import originalFalse.tech.zycdojar.block.wuzhongshenyouyishi;
import originalFalse.tech.zycdojar.item.group.theGroup;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class main {
    public static ContainerType<containter> containterContainerType;
    //锻造台
    public static Block builder;
    public static Item builderI;
    //虚空工具
    public static Item sword;
    public static Item pickaxe;
    public static Item shovel;
    public static Item axe;
    public static Item hoe;
    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(containterContainerType, (containter screenContainer, PlayerInventory inv, ITextComponent titleIn) -> {
            return new screen(screenContainer, inv, titleIn);
        });
    }
    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event){
    }
    @SubscribeEvent
    public static void onContainerRegist(RegistryEvent.Register<ContainerType<?>> event){
        IForgeRegistry<ContainerType<?>> registry=event.getRegistry();
        containterContainerType=IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) -> {
            return new containter(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld());
        });
        containterContainerType.setRegistryName("ccc");
        registry.register(containterContainerType);
    }
    @SubscribeEvent
    public static void regItem(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry=event.getRegistry();
        Item.Properties properties=new Item.Properties();
        properties.group(group.gmy_group);
        properties.maxStackSize(64);
        Item.Properties properties2=new Item.Properties();
        properties2.group(group.gmy_group);
        properties2.maxDamage(666);
        pickaxe=new netherite_pickaxe(properties2);
        builderI=new BlockItem(builder,properties).setRegistryName(builder.getRegistryName());
        sword=new netherite_sword(properties2);
        axe=new netherite_axe(properties2);
        shovel=new netherite_shovel(properties2);
        hoe=new neterite_hoe(properties2);
        registry.register(hoe);
        registry.register(shovel);
        registry.register(axe);
        registry.register(sword);
        registry.register(pickaxe);
        registry.register(builderI);
    }
    @SubscribeEvent
    public static void regBlock(final RegistryEvent.Register<Block> event){
        IForgeRegistry<Block> registry=event.getRegistry();
        builder=new builder();
        registry.register(builder);
    }
    @SubscribeEvent
    public static void regTile(final RegistryEvent.Register<TileEntityType<?>> event){
        IForgeRegistry<TileEntityType<?>> registry=event.getRegistry();
        registry.register(builderTile.type);
    }
}
