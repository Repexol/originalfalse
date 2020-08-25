package originalFalse.zycdojar.api.wrapper;

import com.google.gson.JsonObject;
import originalFalse.zycdojar.api.client.ClientRequest;
import originalFalse.zycdojar.api.client.requestContext;

import java.util.HashMap;
import java.util.Map;

public class RequestManager {
    public static Map<String,RequestHandle> handleMap=new HashMap<>();

    /**
     * 注册客户端请求handle
     * @param handle
     * @param registyName
     */
    public static void registerHandle(RequestHandle handle,String registyName){
        handleMap.put(registyName,handle);
    }

    /**
     * 发送带有请求的请求包
     * @param message
     */
    public static void send(JsonObject message){
        requestContext context=new requestContext(message);
        ClientRequest.send(context);
    }

    /**
     * 发送空的请求包
     * （只包含状态，不包含请求的请求包）
     */
    public static void send(){
        ClientRequest.send(new requestContext());
    }
}
