package originalFalse.superTool.zycdojar.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import originalFalse.superTool.zycdojar.item.tile.AnvilTile;
import originalFalse.superTool.zycdojar.main;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.item.block.bsTile;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 制图台
 */
public class Anvil extends Block {

    public Anvil() {
        super(Properties.create(Material.ANVIL).hardnessAndResistance(10,Integer.MAX_VALUE).notSolid());
        setRegistryName("anvil");
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new AnvilTile();
    }

    /**
     * 掉落
     * @param state
     * @param builder
     * @return
     */
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> a=new ArrayList<>();
        a.add(new ItemStack(main.anvili,1));
        return a;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        //判断服务端
        if(!worldIn.isRemote) {
            AnvilTile tile = (AnvilTile) worldIn.getTileEntity(pos);
            //获取方块nbt（内部容器）
            CompoundNBT nbt = tile.getTileData();
            if (player.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.paper)) {
                //设计图纸
                ItemStack stack=new ItemStack(main.icon,1);
                //随机种子
                designChart.initialDC(stack);
                //player.sendMessage(new StringTextComponent(IntNBT.valueOf(new Random().nextInt())+""));
                player.setHeldItem(Hand.MAIN_HAND,stack);
            }else if(player.getHeldItem(Hand.MAIN_HAND).getItem().equals(main.icon)){
                //把图纸右键，获取成品
                if(tile.get(player.getHeldItem(Hand.MAIN_HAND))){
                    ItemStack sword=new ItemStack(main.sword,1);
                    //设置种子
                    sword.getOrCreateTag().put("seed",IntNBT.valueOf(player.getHeldItem(Hand.MAIN_HAND).getTag().getInt("designSeed")));
                    //添加到背包
                    player.addItemStackToInventory(sword);
                    //消耗图纸
                    player.getHeldItem(Hand.MAIN_HAND).setCount(player.getHeldItem(Hand.MAIN_HAND).getCount()-1);
                }
            }else if(player.isSneaking()){
                //潜行清空
                tile.clear();
                player.sendMessage(new TranslationTextComponent("originalfalse.weapon.text.clear"));
            }else {
                if(player.getHeldItem(Hand.MAIN_HAND).getItem().equals(Items.AIR)){
                    //空手查看
                    player.sendMessage(new TranslationTextComponent("originalfalse.text.items"));
                    for(Item i:tile.getMap().keySet()){
                        player.sendMessage(new StringTextComponent(i.getName().getString()+"*"+tile.getMap().getOrDefault(i,0)));
                    }
                }else
                if(tile.put(player.getHeldItem(Hand.MAIN_HAND).getItem(),1)){
                    //塞入物品（只有限定的几样）
                    player.getHeldItem(Hand.MAIN_HAND).setCount(player.getHeldItem(Hand.MAIN_HAND).getCount()-1);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}
