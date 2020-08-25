package originalFalse.zycdojar.item.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.item.block.bs.craft;

import javax.annotation.Nullable;
import java.util.HashMap;


public class BossSpawner extends Block {
    public BossSpawner() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(20,5).notSolid());
        setRegistryName("super_boss_spawner");
    }

    /**
     * 召唤台被右键
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
        if(!worldIn.isRemote){
            bsTile tileEntity= (bsTile) worldIn.getTileEntity(pos);
            if(player.isSneaking()){
                //潜行右键清空
                tileEntity.items=new HashMap<>();
                tileEntity.count=0;
            }else {
                if (player.getHeldItem(Hand.MAIN_HAND).getItem().equals(Items.AIR)) {
                    //空手右键查看物品
                    player.sendMessage(new TranslationTextComponent("originalfalse.text.items"));
                    for (Item i : tileEntity.items.keySet()) {
                        player.sendMessage(new StringTextComponent(tileEntity.items.get(i) + "*" + i.getName().getString()));
                    }
                } else {
                    if (tileEntity.count < 5) {
                        //物品少于5那么就塞进去
                        if (tileEntity.items.get(player.getHeldItem(Hand.MAIN_HAND).getItem()) == null) {
                            tileEntity.items.put(player.getHeldItem(Hand.MAIN_HAND).getItem(), 1);
                        } else {
                            tileEntity.items.put(player.getHeldItem(Hand.MAIN_HAND).getItem(), tileEntity.items.get(player.getHeldItem(Hand.MAIN_HAND).getItem()) + 1);
                        }
                        tileEntity.count += 1;
                        player.getHeldItem(Hand.MAIN_HAND).setCount(player.getHeldItem(Hand.MAIN_HAND).getCount() - 1);
                    }
                }
                if (tileEntity.count == 5) {
                    //试图合成
                    ItemStack x = craft.craft(tileEntity.items);
                    if (!(x == null)) {
                        player.addItemStackToInventory( x);
                        tileEntity.items = new HashMap<>();
                        tileEntity.count = 0;
                        LevelSystem.grantExp(player, 5, worldIn);
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new bsTile();
    }
}
