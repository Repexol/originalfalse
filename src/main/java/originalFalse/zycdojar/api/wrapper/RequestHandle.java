package originalFalse.zycdojar.api.wrapper;

import com.google.gson.JsonObject;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface RequestHandle {
    public boolean handle(JsonObject object);
    @OnlyIn(Dist.CLIENT)
    public JsonObject getState();
    public void handleState(JsonObject object);
}
