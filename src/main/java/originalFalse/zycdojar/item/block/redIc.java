package originalFalse.zycdojar.item.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import originalFalse.zycdojar.api.wrapper.LevelSystem;

public class redIc extends Block {
    public redIc() {
        super(Properties.create(Material.IRON).hardnessAndResistance(23,1).notSolid());
        setRegistryName("redic");
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote&&handIn==Hand.MAIN_HAND){
            LevelSystem.send(worldIn,player);
        }
        return ActionResultType.SUCCESS;
    }
}
