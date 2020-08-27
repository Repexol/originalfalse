package originalFalse.leaf.zycdojar.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class leaf extends MonsterEntity implements IRangedAttackMob {

    private static final DataParameter<String> owner= EntityDataManager.createKey(leaf.class, DataSerializers.STRING);
    public leaf(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        //this.goalSelector.addGoal(0,new goal(this));
        this.goalSelector.addGoal(0,new goal(this));
        this.goalSelector.addGoal(4,new RangedAttackGoal(this,5,2,5));
        goalSelector.addGoal(3,new RangedBowAttackGoal<leaf>(this,5,2,64));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<CreeperEntity>(this,CreeperEntity.class,10,3,3));
        //goalSelector.addGoal(3,new FollowOwnerGoal(this,5,5,5,true));
        goalSelector.addGoal(5,new HurtByTargetGoal(this));
        goalSelector.addGoal(0,new OpenDoorGoal(this,true));
        goalSelector.addGoal(4,new MoveTowardsTargetGoal(this,5,1));
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

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        if(distanceFactor<=1){
            if(target.getName().getString().equals("Dev")){
                this.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) target),5);
            }
            target.attackEntityAsMob(this);
        }
    }
}
