package originalFalse.zycdojar.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import originalFalse.zycdojar.item.tier.stdTier;

public class blade extends SwordItem {
    public blade(Properties builder) {
        super(new stdTier(), 0,8f, builder);
        setRegistryName("blade");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof blade){
            return true;
        }
        return false;
    }
}
