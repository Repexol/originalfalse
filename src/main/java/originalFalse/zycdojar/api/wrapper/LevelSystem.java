package originalFalse.zycdojar.api.wrapper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 对于worldSaveData的包装
 * 等级系统
 */
@Mod.EventBusSubscriber
public class LevelSystem {
    /**
     * 由于魔力系统不会初始化（所以这样会检测魔力系统是否初始化）
     * @param UUID
     */
    public static void initMana(String UUID){
        if(mana.get(UUID)==null) {
            mana.put(UUID, 20 + (getLevel(UUID, worldSaveData.overWorld) * 10));
            markSync();
        }
    }

    /**
     * 获取玩家的等级
     * @param entity
     * @param world
     * @return
     */
    public static int getLevel(PlayerEntity entity, World world){
        worldSaveData data=worldSaveData.getInstance(world);
        return data.getLevel(entity);
    }
    public static int getLevel(String entity, World world){
        worldSaveData data=worldSaveData.getInstance(world);
        return data.getLevel(entity);
    }
    //每5tick检测是否需要把服务端的数据传到客户端
    private static boolean markSync=false;
    private static int ticks=0;

    /**
     * 回复魔力和同步数据
     * @param event
     */
    @SubscribeEvent
    public static void tickWrapper(TickEvent event){
        if(worldSaveData.overWorld==null){
        }else {
            if((Math.abs(new Random().nextInt())%100)>=98)
            tick(worldSaveData.overWorld);
            if(ticks==5) {
                if(markSync) {
                    sync();
                    markSync=false;
                }
                ticks=0;
            }else {
                ticks++;
            }
        }
    }

    /**
     * 标记为需要同步
     */
    public static void markSync(){
        markSync=true;
    }

    /**
     * 当场同步（慎用，会造成卡顿）
     */
    public static void sudoSync(){
        sync();
    }
    private static void sync(){
        for (ServerPlayerEntity playerEntity : worldSaveData.overWorld.getServer().getPlayerList().getPlayers()) {
            LevelSender.sendData(playerEntity);
        }
    }
    /**
     * 升级经验
     */
    private static final Map<Integer,Integer> upLevel=new HashMap<>();
    //private static Map<Integer,String> levelAlies=new HashMap<>();
    /**
     * 魔力
     * 这里的key指的是uuid而非名字
     */
    public static Map<String,Integer> mana=new HashMap<>();

    /**
     * 初始化升级经验列表
     */
    public static void initial(){
        upLevel.put(0,10);
        upLevel.put(1,15);
        upLevel.put(2,30);
        upLevel.put(3,40);
        upLevel.put(4,50);
        upLevel.put(5,60);
        upLevel.put(6,70);
        upLevel.put(7,100);
        upLevel.put(8,125);
        upLevel.put(9,250);
        upLevel.put(10,375);
        upLevel.put(11,376);
        upLevel.put(12,377);
    }

    /**
     * 给与经验并查看
     * @param entity
     * @param exp
     * @param worldIn
     */
    public static void grantExp(PlayerEntity entity, int exp, World worldIn){
        worldSaveData data=worldSaveData.getInstance(worldIn);
        data.setExp(entity,data.getExp(entity)+exp);
        if(upLevel.get(data.getLevel(entity))!=null){
            if(data.getExp(entity)>=upLevel.get(data.getLevel(entity))){
                data.setExp(entity,data.getExp(entity)-upLevel.get(data.getLevel(entity)));
                data.setLevel(entity,data.getLevel(entity)+1);
                for(PlayerEntity player:worldIn.getPlayers()){
                    player.sendMessage(new TranslationTextComponent("originalfalse.text.level",entity.getName().getString(),data.getLevel(entity)));
                }
                markSync();
            }
        }
    }

    /**
     * 消耗魔力
     * 如果消耗的魔力>拥有的魔力
     * 那么消耗失败
     * @param entity
     * @param mana
     * @param worldIn
     * @return 是否消耗成功
     */
    public static boolean removeMana(PlayerEntity entity,int mana,World worldIn){
        worldSaveData data=worldSaveData.get(worldIn);
        if(LevelSystem.mana.get(entity.getUniqueID().toString())==null){
            LevelSystem.mana.put(entity.getUniqueID().toString(),20+(data.getLevel(entity)*10));
        }
        if(LevelSystem.mana.get(entity.getUniqueID().toString())>=mana){
            LevelSystem.mana.put(entity.getUniqueID().toString(),LevelSystem.mana.get(entity.getUniqueID().toString())-mana);
            LevelSystem.grantExp(entity, mana/5,worldIn);
            markSync();
            return true;
        }else {
            entity.setHealth(entity.getHealth()-5);
            entity.sendMessage(new TranslationTextComponent("originalfalse.text.yourMoLiIsTouZhiIng"));
            LevelSystem.mana.put(entity.getUniqueID().toString(),0);
            return false;
        }
    }

    /**
     * 回复魔力
     * @param worldIn
     */
    public static void tick(World worldIn){
        worldSaveData data=worldSaveData.get(worldIn);
        for(String a:mana.keySet()){
            if(!(mana.get(a)>=20+(data.level.get(a)*10)))
            mana.put(a,mana.get(a)+1);
        }
    }

    /**
     * 发送玩家状态
     * 如果客户端使用，那么就只要LevelSystem.send(Minecraft.getInstance().player.getEntityWorld(),Minecraft.getInstance().player0;
     * 服务端的话，就LevelSystem.send(worldSaveData.overWorld,玩家);
     * 如果在event里提供了worldIn，那么就直接worldIn了
     * 会自动识别
     * 客户端会调用缓存（数据在服务端）而服务端会查询数据
     * @param worldIn
     * @param player
     */
    public static void send(World worldIn,PlayerEntity player){
        if(!worldIn.isRemote) {
            //服务端
            worldSaveData data = worldSaveData.get(worldIn);
            player.sendMessage(new TranslationTextComponent("originalfalse.text.levelshow", "" + data.getLevel(player)));
            player.sendMessage(new TranslationTextComponent("originalfalse.text.expshow", "" + data.getExp(player)));
            if (LevelSystem.mana.get(player.getUniqueID().toString()) == null) {
                LevelSystem.mana.put(player.getUniqueID().toString(), 20 + (data.getLevel(player) * 10));
                markSync();
            }
            player.sendMessage(new TranslationTextComponent("originalfalse.text.manashow", "" + LevelSystem.mana.get(player.getUniqueID().toString()), 20 + (data.getLevel(player) * 10) + ""));
            for (messageHandle handle : worldSaveData.messageHandles) {
                handle.send((ServerPlayerEntity) player);
            }
        }else {
            //客户端
            player.sendMessage(new TranslationTextComponent("originalfalse.text.levelshow", "" + client.level));
            player.sendMessage(new TranslationTextComponent("originalfalse.text.expshow", "" + client.exp));
            player.sendMessage(new TranslationTextComponent("originalfalse.text.manashow", "" + client.mana, 20 + (client.level * 10) + ""));
            player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.message", client.ne));

        }
    }
}
