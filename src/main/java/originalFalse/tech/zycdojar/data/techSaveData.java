package originalFalse.tech.zycdojar.data;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.*;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;

import java.util.HashMap;
import java.util.Map;

/**
 * @deprecated
 * worldSavedData
 * 详见https://neutrino.v2mcdev.com/worldsaveddata/example.html
 * nat就是自然之息
 * 不推荐用这里的方法
 * 推荐用NESystem的方法
 */
public class techSaveData extends WorldSavedData {
    private final Map<String,Integer> playerNat=new HashMap<>();
    public techSaveData() {
        super("techSaveData");
    }

    /**
     * 获取玩家的自然之息
     * @param player
     * @return
     */
    public int getNat(PlayerEntity player){
        markDirty();
        return playerNat.getOrDefault(player.getUniqueID().toString(),0);
    }
    public int getNat(String uuid){
        markDirty();
        return playerNat.getOrDefault(uuid,0);
    }

    /**
     * 设置玩家的自然之息
     * @param player
     * @param amount
     */
    public void putNat(PlayerEntity player,int amount){
        playerNat.put(player.getUniqueID().toString(),amount);
        markDirty();
    }
    public void putNat(String uuid,int amount){
        playerNat.put(uuid,amount);
        markDirty();
    }

    /**
     * 读写nbt
     * @param nbt
     */
    @Override
    public void read(CompoundNBT nbt) {
        ListNBT list= (ListNBT) nbt.get("playersData");
        if(list!=null){
            for(INBT nbt1:list){
                CompoundNBT playerNBT= (CompoundNBT) nbt1;
                playerNat.put(playerNBT.getString("uuid"),playerNBT.getInt("mana"));
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT nbt=new ListNBT();
        for(String name:playerNat.keySet()){
            CompoundNBT playerData=new CompoundNBT();
            playerData.put("uuid",StringNBT.valueOf(name));
            playerData.put("mana", IntNBT.valueOf(playerNat.get(name)));
            nbt.add(playerData);
        }
        compound.put("playersData",nbt);
        return compound;
    }
    /**
     * 这个东西不能直接new，要getInstance;
     * @param worldIn
     * @return
     */
    public static techSaveData getInstance(World worldIn) {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }

        ServerWorld world = worldIn.getServer().getWorld(DimensionType.OVERWORLD);
        if(worldSaveData.overWorld==null){
            worldSaveData.overWorld=world;
        }
        /***
         *   如果你需要每个纬度都有一个自己的World Saved Data。
         *  用 ServerWorld world = (ServerWorld)world; 代替上面那句。
         */
        DimensionSavedDataManager storage = world.getSavedData();
        return storage.getOrCreate(() -> {
            return new techSaveData();
        }, "techSaveData");
    }

}
