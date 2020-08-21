package originalFalse.leaf.zycdojar.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.World;

public class leaf extends MobEntity {
    protected leaf(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.goalSelector.addGoal(0,new goal(this));
    }
}
