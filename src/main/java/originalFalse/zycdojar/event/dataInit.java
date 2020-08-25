package originalFalse.zycdojar.event;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;

import java.util.HashMap;

@Mod.EventBusSubscriber
public class dataInit {
    @SubscribeEvent
    public static void onStop(FMLServerStoppedEvent event){
        //删除主世界数据和魔力数据
        worldSaveData.overWorld=null;
        LevelSystem.mana=new HashMap<>();
    }
    @SubscribeEvent
    public static void onStart(FMLServerStartedEvent event){
        //初始化主世界数据
        worldSaveData.getInstance(event.getServer().getWorld(DimensionType.OVERWORLD));
    }
}
