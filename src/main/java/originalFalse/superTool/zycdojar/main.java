package originalFalse.superTool.zycdojar;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import originalFalse.superTool.zycdojar.craft.craft;
import originalFalse.superTool.zycdojar.event.tileRegister;
import originalFalse.superTool.zycdojar.item.Anvil;
import originalFalse.superTool.zycdojar.item.designChart;
import originalFalse.superTool.zycdojar.item.group.group;
import originalFalse.superTool.zycdojar.item.sword;
import originalFalse.superTool.zycdojar.spell.handle;
import originalFalse.zycdojar.api.wrapper.CraftManager;
import originalFalse.zycdojar.api.wrapper.SpellManager;
import originalFalse.zycdojar.item.block.TileEntityTypeRegistry;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class main {
    //设计图
    public static Item icon;
    //制图台
    public static Block anvil;
    //制图台的物品形式
    public static Item anvili;
    //自定义剑
    public static Item sword;
    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event){
        //自定义api（测试咒语）
        SpellManager.registerSpellHandle(new handle());
        //自定api（处理制图台合成）
        CraftManager.registerSpawnerCraft(new craft());
    }
    @SubscribeEvent
    public static void itemreg(final RegistryEvent.Register<Item> event){
        Item.Properties properties=new Item.Properties();
        properties.maxStackSize(32);
        properties.group(group.gmy_group);
        Set<Item> itemSet=new HashSet<>();
        anvili=new BlockItem(anvil,properties).setRegistryName(anvil.getRegistryName());
        icon=new designChart(properties);
        itemSet.add(icon);
        sword=new sword(properties);
        itemSet.add(sword);
        itemSet.add(anvili);
        for(Item i:itemSet){
            event.getRegistry().register(i);
        }
    }
    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> blockRegistryEvent) {
        Set<TileEntityType<?>> tiles=new HashSet<>();
        tiles.add(tileRegister.Anvil);
        for(TileEntityType block:tiles){
            blockRegistryEvent.getRegistry().register(block);
        }
    }
    @SubscribeEvent
    public static void blockreg(final RegistryEvent.Register<Block> event){
        Item.Properties properties=new Item.Properties();
        properties.maxStackSize(32);
        properties.group(group.gmy_group);
        Set<Block> itemSet=new HashSet<>();
        anvil=new Anvil();
        itemSet.add(anvil);
        for(Block i:itemSet){
            event.getRegistry().register(i);
        }
    }
}
