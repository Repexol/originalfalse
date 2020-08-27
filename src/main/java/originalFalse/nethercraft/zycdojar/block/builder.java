package originalFalse.nethercraft.zycdojar.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import originalFalse.tech.zycdojar.block.CLASS;

import javax.annotation.Nullable;

public class builder extends Block {
    public builder() {
        super(CLASS.pps);
        setRegistryName("builder");
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new builderTile();
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote&&handIn==Hand.MAIN_HAND){
            builderTile tile= (builderTile) worldIn.getTileEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity)player,tile,(packetBuffer -> {
                packetBuffer.writeBlockPos(tile.getPos());
            }));
        }
        return ActionResultType.SUCCESS;
    }
}
