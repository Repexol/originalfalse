package originalFalse.zycdojar.api.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import org.jfree.data.json.impl.JSONObject;
import originalFalse.zycdojar.api.wrapper.RequestHandle;
import originalFalse.zycdojar.api.wrapper.RequestManager;

import java.util.function.Supplier;
import java.util.logging.Logger;

/*
请求包（客户端向服务端发送）
 */
public class requestContext {
    private JsonObject request;
    private JsonObject state;
    private static final Logger LOGGER = Logger.getLogger("requestContext");

    public requestContext(PacketBuffer buffer) {
        JsonParser parser=new JsonParser();
        JsonObject object=parser.parse(buffer.readString()).getAsJsonObject();

        request=object.getAsJsonObject("request");
        state=object.getAsJsonObject("state");
    }
    public requestContext(){
        request=new JsonObject();
        request.addProperty("statePack",true);
        state=new JsonObject();
        //自定义api
        for(String name: RequestManager.handleMap.keySet()){
            state.add(name,RequestManager.handleMap.get(name).getState());
        }
    }
    public requestContext(JsonObject request) {
        this.request=request;
        state=new JsonObject();
        for(String name: RequestManager.handleMap.keySet()){
            state.add(name,RequestManager.handleMap.get(name).getState());
        }
    }


    public void toBytes(PacketBuffer buf) {
        JsonObject object=new JsonObject();
        object.add("request",request);
        object.add("state",state);
        buf.writeString(object.toString());
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            boolean flag=false;
            try{
                if(request.get("statePack").getAsBoolean()){
                    flag=true;
                }
            }catch (Exception e1){
            }
            for(String name:RequestManager.handleMap.keySet()){
                RequestHandle handle=RequestManager.handleMap.get(name);
                if(!flag){
                    if(handle.handle(request)){
                        flag=true;
                    }
                }
                handle.handleState(state.getAsJsonObject(name));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
