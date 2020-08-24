package originalFalse.zycdojar.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.lwjgl.system.CallbackI;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.zycdojar.api.client.ClientRequest;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.api.wrapper.RequestManager;
import originalFalse.zycdojar.api.wrapper.client;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;
import originalFalse.zycdojar.main;

import java.util.Properties;
import java.util.function.Supplier;

public class levelPack {
    public int level=0;
    public int exp=0;
    public int mana=0;
    public int ne=0;
    public levelPack(PacketBuffer buffer) {
        JsonParser parser=new JsonParser();
        JsonObject object= parser.parse(buffer.readString()).getAsJsonObject();
        level=object.get("level").getAsInt();
        exp=object.get("exp").getAsInt();
        mana=object.get("mana").getAsInt();
        ne=object.get("ne").getAsInt();
        //message = buffer.readString(Short.MAX_VALUE);
    }

    public levelPack(PlayerEntity playerInfo) {
        if(worldSaveData.overWorld!=null) {
            worldSaveData char1Data = worldSaveData.getInstance(worldSaveData.overWorld);
            level = char1Data.getLevel(playerInfo);
            exp = char1Data.getExp(playerInfo);
            mana = LevelSystem.mana.getOrDefault(playerInfo.getUniqueID().toString(),0);
            ne = NESystem.getNE(playerInfo);
        }
    }

    public void toBytes(PacketBuffer buf) {
        JsonObject object=new JsonObject();
        object.addProperty("level",level);
        object.addProperty("exp",exp);
        object.addProperty("mana",mana);
        object.addProperty("ne",ne);
        buf.writeString(object.toString());
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            client.exp=exp;
            client.level=level;
            client.mana=mana;
            client.ne=ne;
            RequestManager.send();
        });
        ctx.get().setPacketHandled(true);
    }
}
