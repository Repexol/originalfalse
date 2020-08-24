package originalFalse.zycdojar.event.dataCenter;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import originalFalse.zycdojar.api.wrapper.messageHandle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class worldSaveData extends WorldSavedData {
    public Map<String,Integer> level=new HashMap<>();
    public Map<String,Set<String>> spell=new HashMap<>();
    public static World overWorld=null;
    public static Set<messageHandle> messageHandles=new HashSet<>();
    public Map<String,Integer> exp=new HashMap<>();
    public static String name="originalfalseworldsavedata";
    public worldSaveData() {
        super(name);
    }
    private void initSpell(PlayerEntity player){
        if(spell.get(player.getUniqueID().toString())==null) {
            spell.put(player.getUniqueID().toString(), new HashSet<>());
            markDirty();
        }
    }
    public int getLevel(PlayerEntity player){
        markDirty();
        String uuid=player.getUniqueID().toString();
        if(level.get(uuid)==null){
            setLevel(player,0);
        }
        return level.get(uuid);
    }
    public int getLevel(String player){
        markDirty();
        String uuid=player;
        if(level.get(uuid)==null){
            setLevel(player,0);
        }
        return level.get(uuid);
    }
    public void setLevel(PlayerEntity entity,int level){
        this.level.put(entity.getUniqueID().toString(),level);
        markDirty();
    }
    public void setLevel(String entity,int level){
        this.level.put(entity,level);
        markDirty();
    }
    public int getExp(PlayerEntity player){
        markDirty();
        String uuid=player.getUniqueID().toString();
        if(exp.get(uuid)==null){
            setExp(player,0);
        }
        return exp.get(uuid);
    }
    public boolean study(PlayerEntity player,String spell){
        initSpell(player);
        if(!this.spell.get(player.getUniqueID().toString()).contains(spell))
        this.spell.get(player.getUniqueID().toString()).add(spell);
        else
            return false;
        player.sendMessage(new TranslationTextComponent("originalfalse.text.studyspell",spell));
        markDirty();
        return true;
    }
    public Set<String> getSpell(PlayerEntity player){
        initSpell(player);
        markDirty();
        return this.spell.get(player.getUniqueID().toString());
    }
    public void setExp(PlayerEntity entity,int level){
        this.exp.put(entity.getUniqueID().toString(),level);
        markDirty();
    }
    public static worldSaveData getInstance(World worldIn){
        return get(worldIn);
    }
    public static worldSaveData get(World worldIn) {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
        ServerWorld world = worldIn.getServer().getWorld(DimensionType.OVERWORLD);
        if(worldSaveData.overWorld==null){
            overWorld=world;
        }
        DimensionSavedDataManager storage = world.getSavedData();
        return storage.getOrCreate(() -> {
            return new worldSaveData();
        },name);
    }
    @Override
    public void read(CompoundNBT nbt) {
        ListNBT list= (ListNBT) nbt.get("playerLevels");
        if(list!=null){
            for(INBT value:list){
                CompoundNBT tag= (CompoundNBT) value;
                ListNBT List= (ListNBT) tag.get("spell");
                Set<String> spells=new HashSet<>();
                if(List!=null){
                for(INBT curse:List){
                    StringNBT nbt1= (StringNBT) curse;
                    spells.add(nbt1.getString());
                }
                }
                spell.put(tag.getString("uuid"),spells);
                level.put(tag.getString("uuid"), tag.getInt("level"));
                exp.put(tag.getString("uuid"),tag.getInt("exp"));
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT levelNBT=new ListNBT();
        for(String uuid:level.keySet()){
            CompoundNBT nbt=new CompoundNBT();
            ListNBT spells=new ListNBT();
            if(spell.get(uuid)!=null) {
                for (String curse : spell.get(uuid)) {
                    spells.add(StringNBT.valueOf(curse));
                }
            }
            nbt.put("spell",spells);
            if(exp.get(uuid)==null) {
                nbt.put("level", IntNBT.valueOf(0));
            }else{
                nbt.put("level", IntNBT.valueOf(level.get(uuid)));
            }
            //nbt.put("level",IntNBT.valueOf(level.get(uuid)));
            nbt.put("uuid",StringNBT.valueOf(uuid));
            if(exp.get(uuid)==null) {
                nbt.put("exp", IntNBT.valueOf(0));
            }else{
                nbt.put("exp", IntNBT.valueOf(exp.get(uuid)));
            }
            levelNBT.add(nbt);
        }
        compound.put("playerLevels",levelNBT);
        return compound;
    }
}
