package originalFalse.zycdojar.api.wrapper;

import originalFalse.zycdojar.item.block.ManaCore;
import originalFalse.zycdojar.item.wand;

import java.util.HashSet;
import java.util.Set;

public class SpellManager {
    public static void registerSpellHandle(spellHandle handle){
        wand.handles.add(handle);
    }
    public static void registerSpellList(String spellName){
        ManaCore.spells.add(spellName);
    }
}
