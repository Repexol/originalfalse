package originalFalse.zycdojar.api.wrapper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public interface spellHandle {
    public boolean spell(String spell, LivingEntity target, ServerPlayerEntity player);
}
