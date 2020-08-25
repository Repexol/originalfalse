package originalFalse.zycdojar.api.wrapper;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * 客户端可调用的数据缓存
 * 每次同步数据的时候都会更新
 */
@OnlyIn(Dist.CLIENT)
public class client {
    public static int level=0;
    public static int exp=0;
    public static int mana=0;
    public static int ne=0;
}
