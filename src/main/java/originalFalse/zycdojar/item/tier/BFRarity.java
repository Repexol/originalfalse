package originalFalse.zycdojar.item.tier;

import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;

public class BFRarity {

    public static final Rarity ORDINARY = Rarity.create("ORDINARY", TextFormatting.WHITE);
    public static final Rarity FINE = Rarity.create("FINE", TextFormatting.DARK_GREEN);
    public static final Rarity EXCELLENT = Rarity.create("EXCELLENT", TextFormatting.DARK_BLUE);
    public static final Rarity EPIC = Rarity.create("EPIC", TextFormatting.LIGHT_PURPLE);
    public static final Rarity SPLENDID = Rarity.create("SPLENDID", TextFormatting.GOLD);
    public static final Rarity LEGENDARY = Rarity.create("LEGENDARY", TextFormatting.RED);

    public static Rarity create(String name, TextFormatting color) {
        return Rarity.create(name, color);
    }
}