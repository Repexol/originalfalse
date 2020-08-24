package originalFalse.tech.zycdojar.api.wrapper;

import net.minecraft.entity.player.PlayerEntity;
import originalFalse.tech.zycdojar.data.techSaveData;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;

public class NESystem {
    public static int getNE(PlayerEntity player){
        LevelSystem.markSync();
        return techSaveData.getInstance(worldSaveData.overWorld).getNat(player);
    }
    public static int getNE(String player){
        return techSaveData.getInstance(worldSaveData.overWorld).getNat(player);
    }
    public static void grantNE(PlayerEntity playerEntity,int amount){
        techSaveData data=techSaveData.getInstance(worldSaveData.overWorld);
        if(data.getNat(playerEntity)<1000) {
            data.putNat(playerEntity, data.getNat(playerEntity) + amount);
        }
        LevelSystem.markSync();
    }
    public static void grantNE(String uuid,int amount){
        techSaveData data=techSaveData.getInstance(worldSaveData.overWorld);
        if(data.getNat(uuid)<=1000) {
            data.putNat(uuid, data.getNat(uuid) + amount);
        }
        LevelSystem.markSync();
    }
    public static boolean removeNE(PlayerEntity playerEntity,int amount){
        techSaveData data=techSaveData.getInstance(worldSaveData.overWorld);
        if(data.getNat(playerEntity)>=amount) {
            data.putNat(playerEntity, data.getNat(playerEntity) - amount);
            LevelSystem.markSync();
            return true;
        }else {
            return false;
        }
    }
}
