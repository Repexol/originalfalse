package originalFalse.zycdojar.api.wrapper;

import com.google.gson.JsonObject;
import originalFalse.zycdojar.api.client.ClientRequest;
import originalFalse.zycdojar.api.client.requestContext;

import java.util.HashMap;
import java.util.Map;

public class RequestManager {
    public static Map<String,RequestHandle> handleMap=new HashMap<>();
    public static void registerHandle(RequestHandle handle,String registyName){
        handleMap.put(registyName,handle);
    }
    public static void send(JsonObject message){
        requestContext context=new requestContext(message);
        ClientRequest.send(context);
    }
    public static void send(){
        ClientRequest.send(new requestContext());
    }
}
