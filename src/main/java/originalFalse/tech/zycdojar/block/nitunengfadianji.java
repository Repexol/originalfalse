package originalFalse.tech.zycdojar.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
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
import org.lwjgl.system.CallbackI;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.block.tile.nituFadianjiTile;
import originalFalse.tech.zycdojar.main;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * 泥土能发电机
 */
public class nitunengfadianji extends Block {
    public nitunengfadianji() {
        super(CLASS.pps);
        setRegistryName("dirt_gent");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new nituFadianjiTile();
    }

    /**
     * 当方块被右键
     * @param state
     * @param worldIn
     * @param pos
     * @param player
     * @param handIn
     * @param hit
     * @return
     */
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote) {

            nituFadianjiTile tile = (nituFadianjiTile) worldIn.getTileEntity(pos);
            //塞入珍珠
            if (player.getHeldItem(handIn).getItem().equals(main.pearl)) {
                //如果珍珠没有主人
                if (player.getHeldItem(handIn).getTag().getString("owner").equals("")) {
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.noOwner"));
                } else {
                    tile.getTileData().put("player", StringNBT.valueOf(player.getHeldItem(handIn).getTag().getString("owner")));
                    player.getHeldItem(handIn).setCount(player.getHeldItem(handIn).getCount() - 1);
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));
                }
            } else if (player.getHeldItem(handIn).getItem().equals(Items.DIRT)) {
                if(!tile.getTileData().getString("player").equals("")) {
                    //泥土发电
                    //超过100电量不予发电
                    if(NESystem.getNE(tile.getTileData().getString("player"))<100) {
                        NESystem.grantNE(tile.getTileData().getString("player"), 1);
                        player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));
                    }else {
                        player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.tooMuchPower"));
                    }
                    player.getHeldItem(handIn).setCount(player.getHeldItem(handIn).getCount() - 1);
                }else {
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.pleaseSetOwner"));
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
