package originalFalse.tech.zycdojar.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.block.tile.mawoderendoushidashabi;
import originalFalse.tech.zycdojar.main;

import javax.annotation.Nullable;

public class wuzhongshenyouyishi extends Block {
    public wuzhongshenyouyishi() {
        super(CLASS.pps);
        setRegistryName("wu_zhong_shen_you_yi_shi");;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new mawoderendoushidashabi();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            mawoderendoushidashabi tile= (mawoderendoushidashabi) worldIn.getTileEntity(pos);
            ItemStack stack=player.getHeldItem(handIn);
            if(stack.getItem().equals(main.pearl)){
                player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.dontPeral"));
            }else
                //检查多方块结构
            if(tile.check(player)){
                CompoundNBT data=tile.getTileData();
                //如果玩家手里没有东西那么取走容器内物品
                if(stack.isEmpty()){
                    if(!ItemStack.read(data.getCompound("item")).isEmpty())
                        player.addItemStackToInventory(ItemStack.read(data.getCompound("item")));
                    data.put("item",ItemStack.EMPTY.serializeNBT());
                }
                else
                    //下界之星开始仪式
                if(stack.getItem().equals(Items.NETHER_STAR)){
                    if(NESystem.removeNE(player,1000)){
                        player.addItemStackToInventory(ItemStack.read(data.getCompound("item")));
                        player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));stack.setCount(stack.getCount()-1);
                        /*int structs[][]={{0,-1,0},{-1,-1,-1},{1,-1,1},{-1,-1,1},{1,-1,-1},{0,-1,-1},{-1,-1,0}
                                ,{0,-1,1},{1,-1,0}};
                        for(int[] line:structs){
                            worldIn.setBlockState(new BlockPos(line[0],line[1],line[2]), Blocks.AIR.getDefaultState());
                        }*/
                    }
                }else {
                    //如果里面有东西那么取出来
                    if(!ItemStack.read(data.getCompound("item")).isEmpty())
                        player.addItemStackToInventory(ItemStack.read(data.getCompound("item")));
                    //然后把手里的东西放进去
                    data.put("item",new ItemStack(stack.getItem(),1).serializeNBT());
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));
                    stack.setCount(stack.getCount()-1);
                }

            }else {
                player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.nostruct"));
            }
        }
        return ActionResultType.SUCCESS;
    }
}
