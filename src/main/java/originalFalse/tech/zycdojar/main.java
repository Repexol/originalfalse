package originalFalse.tech.zycdojar;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import originalFalse.tech.zycdojar.block.*;
import originalFalse.tech.zycdojar.block.tile.dengjiajiaohuanyiTile;
import originalFalse.tech.zycdojar.block.tile.lightingTile;
import originalFalse.tech.zycdojar.block.tile.nituFadianjiTile;
import originalFalse.tech.zycdojar.block.tile.teleporterTile;
import originalFalse.tech.zycdojar.item.NEShied;
import originalFalse.tech.zycdojar.item.group.theGroup;
import originalFalse.tech.zycdojar.item.pearl;
import originalFalse.tech.zycdojar.item.voidMeterial;
import originalFalse.tech.zycdojar.item.voidShied;
import originalFalse.zycdojar.api.wrapper.MessageManager;
import originalFalse.zycdojar.api.wrapper.SpellManager;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class main {
    public static Block dengjiajiaohuanyi;
    public static Item dengjiajiaohuanyiI;
    public static Item pearl;
    public static Block lighting;
    public static Item lightingI;
    public static Item nitunengfadianjiI;
    public static Item voidMeterial;
    public static Block nitunengfadianji;
    public static Block xukongjinshudabaifadianji;
    public static Block teleporter;
    public static Item teleporterI;
    public static Item skjsdbfdj;
    public static Item neshied;
    public static Item voidshied;
    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {
        SpellManager.registerSpellHandle(new spell());
        SpellManager.registerSpellList("AngerOfNatural");
        MessageManager.registerMessage(new messageH());
    }
    @SubscribeEvent
    public static void regItem(final RegistryEvent.Register<Item> event){
        Item.Properties properties=new Item.Properties();
        properties.group(theGroup.gmy_group);
        properties.maxStackSize(64);
        Item.Properties properties2=new Item.Properties();
        properties2.group(theGroup.gmy_group);
        properties2.maxDamage(666);

        lightingI=new BlockItem(lighting,properties).setRegistryName("lighting");
        neshied=new NEShied(properties2);
        Set<Item> items=new HashSet<>();

        items.add(neshied);
        items.add(lightingI);
        voidshied=new voidShied(properties2);
        items.add(voidshied);
        voidMeterial=new voidMeterial(properties);
        if(dengjiajiaohuanyi==null) {
            dengjiajiaohuanyi = new dengjiajiaohuanyi();
            dengjiajiaohuanyiI = new BlockItem(dengjiajiaohuanyi, properties).setRegistryName(dengjiajiaohuanyi.getRegistryName());
        }
        items.add(dengjiajiaohuanyiI);
        skjsdbfdj=new BlockItem(xukongjinshudabaifadianji,properties).setRegistryName(xukongjinshudabaifadianji.getRegistryName());
        items.add(skjsdbfdj);
        items.add(voidMeterial);
        pearl=new pearl(properties);
        teleporterI=new BlockItem(teleporter,properties).setRegistryName("teleporter");
        items.add(teleporterI);
        nitunengfadianjiI=new originalFalse.tech.zycdojar.item.blockItem.nitunengfadianji(properties);
        items.add(nitunengfadianjiI);
        items.add(pearl);
        for(Item i:items){
            event.getRegistry().register(i);
        }
    }
    @SubscribeEvent
    public static void regBlock(final RegistryEvent.Register<Block> event){
        Item.Properties properties=new Item.Properties();
        properties.group(theGroup.gmy_group);
        properties.maxStackSize(64);
        Item.Properties properties2=new Item.Properties();
        properties2.group(theGroup.gmy_group);
        properties2.maxDamage(666);
        Set<Block> items=new HashSet<>();
        if(dengjiajiaohuanyi==null) {
            dengjiajiaohuanyi = new dengjiajiaohuanyi();
            dengjiajiaohuanyiI = new BlockItem(dengjiajiaohuanyi, properties).setRegistryName(dengjiajiaohuanyi.getRegistryName());
        }
        items.add(dengjiajiaohuanyi);
        teleporter=new teleporter();
        xukongjinshudabaifadianji=new xukongjinshudabaifadianji();
        items.add(xukongjinshudabaifadianji);
        items.add(teleporter);
        lighting=new lighting();
        items.add(lighting);
        nitunengfadianji=new nitunengfadianji();
        items.add(nitunengfadianji);
        for(Block i:items){
            event.getRegistry().register(i);
        }

    }
    @SubscribeEvent
    public static void regTile(final RegistryEvent.Register<TileEntityType<?>> event){
        Set<TileEntityType<?>> tileEntityTypes=new HashSet<>();
        tileEntityTypes.add(nituFadianjiTile.type);
        tileEntityTypes.add(dengjiajiaohuanyiTile.type);
        tileEntityTypes.add(teleporterTile.type);
        tileEntityTypes.add(lightingTile.type);
        tileEntityTypes.add(originalFalse.tech.zycdojar.block.tile.xukongjinshudabaifadianji.type);
        for(TileEntityType<?> type:tileEntityTypes){
            event.getRegistry().register(type);
        }
    }
}
