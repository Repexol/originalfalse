package originalFalse.superTool.zycdojar.spell;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import originalFalse.zycdojar.api.wrapper.spellHandle;

public class handle implements spellHandle {
    @Override
    public boolean spell(String spell, LivingEntity target, ServerPlayerEntity player) {
        if(spell.equals("superTool-Message")){
            player.sendMessage(new StringTextComponent("������չ-��װ�ɹ�"));
            return true;
        }
        return false;
    }
}
