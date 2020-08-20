package originalFalse.zycdojar.event.registyevent;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.CPlayerTryUseItemOnBlockPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.zycdojar.item.*;
import originalFalse.zycdojar.item.block.*;
import originalFalse.zycdojar.item.group.originalFalseGroup;

import java.util.*;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
public class itemregister {
    public static Item icon;
    public static Block getSSB;
    public static Item getSSBI;
    public static Item chj;
    public static Item gwi;
    public static Item superShield;
    public static Block ManaCore;
    public static Item ManaCorei;
    public static Item paper;
    public static Block Redic;
    public static Item RedIci;
    public static Item Furen;
    public static Item blade;
    public static Item bladeCore;
    public static Item spellBook;
    public static Block bossSpawner;
    public static Item bossSpawnerItem;
    public static Item superItem;
    public static Item wand;
    public static Item bladeroot;
    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        Set<Block> blocks=new HashSet<>();
        getSSB=new getSilverSpellsBox();
        blocks.add(getSSB);
        ManaCore=new ManaCore();
        blocks.add(ManaCore);
        Redic=new redIc();
        blocks.add(Redic);
        bossSpawner=new BossSpawner();
        blocks.add(bossSpawner);
        for(Block block:blocks){
            blockRegistryEvent.getRegistry().register(block);
        }
    }
    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> blockRegistryEvent) {
        Set<TileEntityType<?>> tiles=new HashSet<>();
        tiles.add(TileEntityTypeRegistry.bsT);
        tiles.add(TileEntityTypeRegistry.gssbET);
        for(TileEntityType block:tiles){
            blockRegistryEvent.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        Item.Properties properties = new Item.Properties();
        Item.Properties inv=new Item.Properties();
        inv.maxStackSize(1);
        inv.group(originalFalseGroup.my_group);
        properties.group(originalFalseGroup.my_group);
        properties.maxStackSize(32);//新建一Item的属性类 Item.Properties 是Item下的一个内部类，可以摁住CTRL+左键点进去看看具体有那些属性
        Set<Item> items=new HashSet<>();
        Furen=new furen(properties);
        items.add(new netherstar_sword(properties));
        bladeCore=new bladeCore(properties);
        items.add(bladeCore);
        items.add(Furen);
        RedIci=new BlockItem(Redic,properties).setRegistryName(Redic.getRegistryName()+"i");
        items.add(RedIci);
        bladeroot=new bladeRoot(properties);
        items.add(bladeroot);
        chj=new RedstoneIngot(properties);
        items.add(chj);
        superShield=new superShield(properties);
        items.add(superShield);
        getSSBI=new BlockItem(getSSB,properties).setRegistryName("gssbi");
        items.add(getSSBI);
        paper=new paper(inv);
        items.add(paper);
        superItem=new superItem(properties);
        wand=new wand(properties);
        items.add(wand);
        items.add(superItem);
        blade=new blade(properties);
        spellBook=new spellStuder(properties);
        items.add(spellBook);
        items.add(blade);
        icon=new creativeBlade(properties);
        items.add(icon);
        gwi=new GWingIngot(properties);
        bossSpawnerItem=new BlockItem(bossSpawner,properties).setRegistryName(bossSpawner.getRegistryName()+"i");
        ManaCorei=new BlockItem(ManaCore,properties).setRegistryName(ManaCore.getRegistryName()+"i");
        items.add(ManaCorei);
        items.add(bossSpawnerItem);
        items.add(gwi);
         for(Item i:items){
             itemRegistryEvent.getRegistry().register(i);
         }
    }
}
