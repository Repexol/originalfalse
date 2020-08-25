package originalFalse.tech.zycdojar.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Items;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.ChunkLoader;
import net.minecraft.world.dimension.DimensionType;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.block.tile.teleporterTile;
import originalFalse.tech.zycdojar.main;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * 传送仪
 */
public class teleporter extends Block {
    public teleporter() {
        super(CLASS.pps);
        setRegistryName("teleporter");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new teleporterTile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            teleporterTile tile= (teleporterTile) worldIn.getTileEntity(pos);
            if (player.getHeldItem(handIn).getItem().equals(main.pearl)) {
                if (player.getHeldItem(handIn).getTag().getString("owner").equals("")) {
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.noOwner"));
                } else {
                    tile.getTileData().put("player", StringNBT.valueOf(player.getHeldItem(handIn).getTag().getString("owner")));
                    player.getHeldItem(handIn).setCount(player.getHeldItem(handIn).getCount() - 1);
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));
                }
            } else {
                if(!tile.getTileData().getString("player").equals("")) {
                    //查找传送仪主人
                    PlayerEntity TargetPlayer=worldIn.getPlayerByUuid(UUID.fromString(tile.getTileData().getString("player")));
                    if(TargetPlayer!=null) {
                        //消耗电量跨纬度
                        if(!TargetPlayer.getEntityWorld().getDimension().getType().equals(worldIn.getDimension().getType())){
                            if(NESystem.removeNE(TargetPlayer,100))
                            TargetPlayer.changeDimension(worldIn.getDimension().getType());
                        }
                        //消耗电量传送
                        if(NESystem.removeNE(TargetPlayer,10)) {
                            //TargetPlayer.setPosition(pos.getX(),pos.getY()+1,pos.getZ());
                            TargetPlayer.attemptTeleport(pos.getX()-0.5, pos.getY() + 2, pos.getZ()-0.5, true);
                            TargetPlayer.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));
                            TargetPlayer.sendMessage(new StringTextComponent("你已经传送至" + pos.getX() + " " + pos.getY() + 2 + " " + pos.getZ()));
                            player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));
                        }
                    }else {
                        //玩家离线
                        player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.offline"));
                    }
                }else {
                    //没有主人
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.pleaseSetOwner"));
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
