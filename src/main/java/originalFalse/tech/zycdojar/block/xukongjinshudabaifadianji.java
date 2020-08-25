package originalFalse.tech.zycdojar.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.block.tile.nituFadianjiTile;
import originalFalse.tech.zycdojar.main;

import javax.annotation.Nullable;

public class xukongjinshudabaifadianji extends Block {
    public xukongjinshudabaifadianji() {
        super(CLASS.pps);
        setRegistryName("xu_kong_jin_shu_da_bai_fa_dian_ji");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new originalFalse.tech.zycdojar.block.tile.xukongjinshudabaifadianji();
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote) {
            originalFalse.tech.zycdojar.block.tile.xukongjinshudabaifadianji tile = (originalFalse.tech.zycdojar.block.tile.xukongjinshudabaifadianji) worldIn.getTileEntity(pos);
            //单纯的是放入自然之息宝珠
            //发电部分在tileEntity里
            if (player.getHeldItem(handIn).getItem().equals(main.pearl)) {
                //如果没有主人
                if (player.getHeldItem(handIn).getTag().getString("owner").equals("")) {
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.noOwner"));
                } else {
                    tile.getTileData().put("player", StringNBT.valueOf(player.getHeldItem(handIn).getTag().getString("owner")));
                    player.getHeldItem(handIn).setCount(player.getHeldItem(handIn).getCount() - 1);
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
