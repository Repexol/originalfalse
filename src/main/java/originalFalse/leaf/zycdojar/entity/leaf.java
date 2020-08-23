package originalFalse.leaf.zycdojar.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class leaf extends MonsterEntity {
    public leaf(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        //this.goalSelector.addGoal(0,new goal(this));
        this.goalSelector.addGoal(0,new goal(this));
    }

    @Override
    public void onKillEntity(LivingEntity entityLivingIn) {
        if(entityLivingIn instanceof ServerPlayerEntity){
            ServerPlayerEntity entity= (ServerPlayerEntity) entityLivingIn;
            entity.sendMessage(new StringTextComponent("那么我们这期就到这里了"));
            entity.sendMessage(new StringTextComponent("我是叶子"));
            entity.sendMessage(new StringTextComponent("我们下期不见不散"));
        }
        super.onKillEntity(entityLivingIn);
    }
}
