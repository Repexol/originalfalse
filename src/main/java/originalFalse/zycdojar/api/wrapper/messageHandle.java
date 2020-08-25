package originalFalse.zycdojar.api.wrapper;

import net.minecraft.entity.player.ServerPlayerEntity;

/**
 * 最废物的api，用于在右键原始小刀的时候
 * 显示玩家数据
 */
public interface messageHandle {
    public void send(ServerPlayerEntity player);
}
