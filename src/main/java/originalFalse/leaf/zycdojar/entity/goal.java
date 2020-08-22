package originalFalse.leaf.zycdojar.entity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class goal extends Goal {
    PlayerEntity player;
    leaf entity;
    public goal(leaf leaf) {
        entity=leaf;
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void startExecuting() {
        World world=entity.getEntityWorld();
        BlockPos pos=entity.getPosition();
        if(!world.isRemote){
            if(player==null){
                player=world.getClosestPlayer(pos.getX(),pos.getY(),pos.getZ(),10,false);
                if(player!=null){
                    entity.setCustomName(player.getName());
                }
            }else if(player.isCreative()){
                player=null;
            }else{
                entity.setMoveForward(player.moveForward);

            }
        }
    }
}
