package originalFalse.tech.zycdojar;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.zycdojar.api.wrapper.messageHandle;

public class messageH implements messageHandle {
    /**
     * 在右键原始小刀的时候显示
     * @param player
     */
    @Override
    public void send(ServerPlayerEntity player) {
        player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.message", NESystem.getNE(player)));
    }
}
