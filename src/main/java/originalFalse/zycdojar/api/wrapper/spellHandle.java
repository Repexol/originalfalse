package originalFalse.zycdojar.api.wrapper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

/**
 * 咒语处理器
 */
public interface spellHandle {
    /**
     * 如果返回true，那么就说明这个咒语是可用的。
     * 如果返回false，那么就会交给下一个handle处理。
     * @param spell
     * @param target
     * @param player
     * @return
     */
    public boolean spell(String spell, LivingEntity target, ServerPlayerEntity player);
}
