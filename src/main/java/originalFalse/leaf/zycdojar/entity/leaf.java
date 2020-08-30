package originalFalse.leaf.zycdojar.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import originalFalse.zycdojar.event.registyevent.itemregister;

import javax.annotation.Nullable;

public class leaf extends MonsterEntity implements IRangedAttackMob{

    private static final DataParameter<String> owner= EntityDataManager.createKey(leaf.class, DataSerializers.STRING);
    public leaf(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        //this.goalSelector.addGoal(0,new goal(this));
        this.goalSelector.addGoal(0,new goal(this));
        this.goalSelector.addGoal(5,new RangedAttackGoal(this,5,2,5));
        goalSelector.addGoal(3,new RangedBowAttackGoal<leaf>(this,5,2,64));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<CreeperEntity>(this,CreeperEntity.class,10,3,3));
        //goalSelector.addGoal(3,new FollowOwnerGoal(this,5,5,5,true));
        HurtByTargetGoal hurtByTargetGoal=new HurtByTargetGoal(this);
        hurtByTargetGoal.setCallsForHelp(leaf.class);
        goalSelector.addGoal(5,hurtByTargetGoal);
        goalSelector.addGoal(0,new OpenDoorGoal(this,true));
        goalSelector.addGoal(4,new MoveTowardsTargetGoal(this,5,1));
        goalSelector.addGoal(5,new FindWaterGoal(this));
        goalSelector.addGoal(1,new LookAtGoal(this,PlayerEntity.class,100));
        goalSelector.addGoal(4,new NearestAttackableTargetGoal<PlayerEntity>(this,PlayerEntity.class,true));
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
    protected void dropLoot(DamageSource damageSourceIn, boolean p_213354_2_) {
        ItemStack stack=new ItemStack(itemregister.spellBook);
        stack.getOrCreateTag().put("spellbook", StringNBT.valueOf("killCommand"));
        entityDropItem(stack,10);
        super.dropLoot(damageSourceIn, p_213354_2_);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        if(distanceFactor<=1){
            target.attackEntityAsMob(this);
        }
    }
}
