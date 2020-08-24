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

@Mod.EventBusSubscriber
public class LevelSystem {
    public static void initMana(String UUID){
        mana.putIfAbsent(UUID,20 + (getLevel(UUID,worldSaveData.overWorld) * 10));
        markSync();
    }
    public static int getLevel(PlayerEntity entity, World world){
        worldSaveData data=worldSaveData.getInstance(world);
        return data.getLevel(entity);
    }
    public static int getLevel(String entity, World world){
        worldSaveData data=worldSaveData.getInstance(world);
        return data.getLevel(entity);
    }
    private static boolean markSync=false;
    private static int ticks=0;
    @SubscribeEvent
    public static void tickWrapper(TickEvent event){
        if(worldSaveData.overWorld==null){
        }else {
            if((Math.abs(new Random().nextInt())%100)>=98)
            tick(worldSaveData.overWorld);
            if(ticks==5) {
                if(markSync) {
                    for (ServerPlayerEntity playerEntity : worldSaveData.overWorld.getServer().getPlayerList().getPlayers()) {
                        LevelSender.sendData(playerEntity);
                    }
                    markSync=false;
                }
                ticks=0;
            }else {
                ticks++;
            }
        }
    }
    public static void markSync(){
        markSync=true;
    }
    private static final Map<Integer,Integer> upLevel=new HashMap<>();
    //private static Map<Integer,String> levelAlies=new HashMap<>();
    public static Map<String,Integer> mana=new HashMap<>();
    public static void initial(){
        upLevel.put(0,10);
        upLevel.put(1,15);
        upLevel.put(2,30);
        upLevel.put(3,40);
        upLevel.put(4,50);
        upLevel.put(5,60);
        upLevel.put(6,70);
    }
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
    public static void tick(World worldIn){
        worldSaveData data=worldSaveData.get(worldIn);
        for(String a:mana.keySet()){
            if(!(mana.get(a)>=20+(data.level.get(a)*10)))
            mana.put(a,mana.get(a)+1);
        }
    }
    public static void send(World worldIn,PlayerEntity player){
        if(player instanceof ServerPlayerEntity&&(!worldIn.isRemote)) {
            worldSaveData data = worldSaveData.get(worldIn);
            player.sendMessage(new TranslationTextComponent("originalfalse.text.levelshow", "" + data.getLevel(player)));
            player.sendMessage(new TranslationTextComponent("originalfalse.text.expshow", "" + data.getExp(player)));
            if (LevelSystem.mana.get(player.getUniqueID().toString()) == null) {
                LevelSystem.mana.put(player.getUniqueID().toString(), 20 + (data.getLevel(player) * 10));
            }
            player.sendMessage(new TranslationTextComponent("originalfalse.text.manashow", "" + LevelSystem.mana.get(player.getUniqueID().toString()), 20 + (data.getLevel(player) * 10) + ""));
            for (messageHandle handle : worldSaveData.messageHandles) {
                handle.send((ServerPlayerEntity) player);
            }
            markSync();
        }else {
            player.sendMessage(new TranslationTextComponent("originalfalse.text.levelshow", "" + client.level));
            player.sendMessage(new TranslationTextComponent("originalfalse.text.expshow", "" + client.exp));
            player.sendMessage(new TranslationTextComponent("originalfalse.text.manashow", "" + client.mana, 20 + (client.level * 10) + ""));
            player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.message", client.ne));

        }
    }
}
