package originalFalse.tech.zycdojar.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.block.tile.lightingTile;
import originalFalse.tech.zycdojar.block.tile.nituFadianjiTile;
import originalFalse.tech.zycdojar.main;

import javax.annotation.Nullable;

public class lighting extends Block {
    public lighting(){
        super(CLASS.pps);
        setRegistryName("lighting");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new lightingTile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote) {
            lightingTile tile = (lightingTile) worldIn.getTileEntity(pos);
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
                    LightningBoltEntity entity=new LightningBoltEntity(worldIn,pos.getX(),pos.getY()+1,pos.getZ(),false);
                    worldIn.addEntity(entity);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
