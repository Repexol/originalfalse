package originalFalse.nuclearCraft;


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
import originalFalse.nuclearCraft.block.nuclear;
import originalFalse.nuclearCraft.block.nuclearTile;
import originalFalse.nuclearCraft.block.warm;
import originalFalse.nuclearCraft.gui.nuclearScreen;
import originalFalse.nuclearCraft.gui.nuclearui;
import originalFalse.nuclearCraft.item.battery;
import originalFalse.nuclearCraft.item.nuclearDefender;
import originalFalse.tech.zycdojar.item.group.theGroup;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class main {
    public static nuclear nuclear;
    public static Item nuclearI;
    public static originalFalse.nuclearCraft.block.fan fan;
    public static Item fanI;
    public static battery Battery;
    public static Item ncdef;
    public static warm Warm;
    public static Item warmi;
    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event){
    }
    @SubscribeEvent
    public static void client(FMLClientSetupEvent event){
        ScreenManager.registerFactory(nuclearui.containerType,(nuclearui ui,PlayerInventory inv, ITextComponent titleIn)->{
            return new nuclearScreen(ui,inv,titleIn);
        });
    }
    @SubscribeEvent
    public static void onContainerRegist(RegistryEvent.Register<ContainerType<?>> event){
        IForgeRegistry<ContainerType<?>> registry=event.getRegistry();
        nuclearui.containerType=IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) -> {
            return new nuclearui(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld());
        });
        nuclearui.containerType.setRegistryName("nui");
        registry.register(nuclearui.containerType);
    }
    @SubscribeEvent
    public static void regItem(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry=event.getRegistry();
        Item.Properties properties=new Item.Properties();
        properties.group(theGroup.gmy_group);
        properties.maxStackSize(64);
        Item.Properties properties2=new Item.Properties();
        properties2.group(theGroup.gmy_group);
        properties2.maxDamage(666);
        Battery=new battery(properties2);
        warmi=new BlockItem(Warm,properties).setRegistryName(Warm.getRegistryName());
        ncdef=new nuclearDefender(properties2);
        fanI=new BlockItem(fan,properties).setRegistryName(fan.getRegistryName());
        nuclearI=new BlockItem(nuclear,properties).setRegistryName(nuclear.getRegistryName());
        registry.register(nuclearI);
        registry.register(warmi);
        registry.register(Battery);
        registry.register(ncdef);
        registry.register(fanI);
    }
    @SubscribeEvent
    public static void regBlock(final RegistryEvent.Register<Block> event){
        IForgeRegistry<Block> registry=event.getRegistry();
        nuclear=new nuclear();
        fan=new originalFalse.nuclearCraft.block.fan();
        Warm=new warm();
        registry.register(Warm);
        registry.register(fan);
        registry.register(nuclear);
    }
    @SubscribeEvent
    public static void regTile(final RegistryEvent.Register<TileEntityType<?>> event){
        IForgeRegistry<TileEntityType<?>> registry=event.getRegistry();
        registry.register(nuclearTile.type);
    }
}
