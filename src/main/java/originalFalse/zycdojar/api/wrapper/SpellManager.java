package originalFalse.zycdojar.api.wrapper;

import originalFalse.zycdojar.item.block.ManaCore;
import originalFalse.zycdojar.item.wand;

import java.util.HashSet;
import java.util.Set;

public class SpellManager {
    /**
     * 注册咒语处理器
     * @param handle
     */
    public static void registerSpellHandle(spellHandle handle){
        wand.handles.add(handle);
    }

    /**
     * 让咒语可以在生存模式获取
     * @param spellName
     */
    public static void registerSpellList(String spellName){
        ManaCore.spells.add(spellName);
    }
}
