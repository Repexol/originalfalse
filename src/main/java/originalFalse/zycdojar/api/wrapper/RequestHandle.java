package originalFalse.zycdojar.api.wrapper;

import com.google.gson.JsonObject;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * 客户端发给服务端的请求拦截api
 */
public interface RequestHandle {
    /**
     * 用于在服务端处理请求
     * 如果这个请求不是这个类发送的，请返回false
     * 应为通常有多个handle，返回true那么就会拦截，下面的handle就收不到。
     * @param object
     * @return 是否处理完毕
     */
    public boolean handle(JsonObject object);

    /**
     * 一个请求包里不仅有请求
     * 还有客户端的状态，这个就是负责状态的
     * @return
     */
    @OnlyIn(Dist.CLIENT)
    public JsonObject getState();

    /**
     * 服务器解析客户端发送的状态
     * @param object
     */
    public void handleState(JsonObject object);
}
