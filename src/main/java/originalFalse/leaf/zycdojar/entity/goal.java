package originalFalse.leaf.zycdojar.entity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public class goal extends Goal {
    leaf entity;
    public goal(leaf leaf) {
        entity=leaf;
    }

    @Override
    public boolean shouldExecute() {
        World world=entity.getEntityWorld();
        if(!world.isRemote){
        }
        return true;
    }
}
