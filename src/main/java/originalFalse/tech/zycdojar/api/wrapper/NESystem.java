package originalFalse.tech.zycdojar.api.wrapper;

import net.minecraft.entity.player.PlayerEntity;
import originalFalse.tech.zycdojar.data.techSaveData;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;

/**
 * 基本上是对techSaveData进行包装
 */
public class NESystem {
    /**
     * 获取玩家自然之息值
     * @param player
     * @return 自然之息值
     */
    public static int getNE(PlayerEntity player){
        LevelSystem.markSync();
        return techSaveData.getInstance(worldSaveData.overWorld).getNat(player);
    }

    /**
     * 同public static int getNE(PlayerEntity player);
     * @param player's uuid
     * @return 自然之息值
     */
    public static int getNE(String player){
        return techSaveData.getInstance(worldSaveData.overWorld).getNat(player);
    }

    /**
     * 给与自然之息
     * @param playerEntity
     * @param amount
     */
    public static void grantNE(PlayerEntity playerEntity,int amount){
        techSaveData data=techSaveData.getInstance(worldSaveData.overWorld);
        if(data.getNat(playerEntity)<1000) {
            data.putNat(playerEntity, data.getNat(playerEntity) + amount);
        }
        LevelSystem.markSync();
    }

    /**
     * 同public static void grantNE(PlayerEntity playerEntity,int amount);
     * @param uuid
     * @param amount
     */
    public static void grantNE(String uuid,int amount){
        techSaveData data=techSaveData.getInstance(worldSaveData.overWorld);
        if(data.getNat(uuid)<1000000) {
            data.putNat(uuid, data.getNat(uuid) + amount);
        }
        LevelSystem.markSync();
    }

    /**
     * 扣除自然之息
     * 如果要扣除的自然之息比原有的自然之息少，那么扣除失败
     * @param playerEntity
     * @param amount
     * @return 成功/失败
     */
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
