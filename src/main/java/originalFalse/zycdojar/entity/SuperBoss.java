package originalFalse.zycdojar.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import originalFalse.zycdojar.entity.goal.SuperBossGoal;

public class SuperBoss extends MonsterEntity {
    protected SuperBoss(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.goalSelector.addGoal(0,new SuperBossGoal(this));
    }

}
