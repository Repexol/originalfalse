package originalFalse.zycdojar.entity.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import originalFalse.zycdojar.entity.SuperBoss;

public class SuperBossGoal extends Goal {
    private SuperBoss boss;
    public SuperBossGoal(SuperBoss boss){
        this.boss=boss;
    }
    @Override
    public boolean shouldExecute() {
        World world=this.boss.getEntityWorld();
        if(!world.isRemote){
            BlockPos pos=this.boss.getPosition();
            PlayerEntity player=world.getClosestPlayer(pos.getX(),pos.getY(),pos.getZ(),10,false);
            if(player!=null){
                player.addPotionEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE,1,100));
            }
        }
        return true;
    }
}
