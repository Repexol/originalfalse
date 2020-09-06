package originalFalse.tech.zycdojar.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import originalFalse.tech.zycdojar.block.tile.createEleTile;
import originalFalse.tech.zycdojar.block.tile.wireTile;

import javax.annotation.Nullable;

public class createEle extends Block {
    public createEle() {
        super(CLASS.pps);
        setRegistryName("cele");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new createEleTile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            createEleTile tile= (createEleTile) worldIn.getTileEntity(pos);
            player.sendMessage(new StringTextComponent("电力："+tile.ele));
        }
        return ActionResultType.SUCCESS;
    }
}
