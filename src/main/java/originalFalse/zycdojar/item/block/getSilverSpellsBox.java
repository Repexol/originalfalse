package originalFalse.zycdojar.item.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.world.storage.loot.LootContext;
import originalFalse.tech.zycdojar.main;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.item.block.gssb.crafting.craft;

import javax.annotation.Nullable;
import java.util.*;

public class getSilverSpellsBox extends Block {
    public Set<ItemStack> yss=new HashSet<>();
    public static Map<BlockPos,Set<ItemStack>> yssNew=new HashMap<>();
    public getSilverSpellsBox() {
        super(Properties.create(Material.IRON).hardnessAndResistance(-1,1).notSolid());
        setRegistryName("gssb");
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new gssbTile();
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
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            gssbTile tile= (gssbTile) worldIn.getTileEntity(pos);
            if(player.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.chj)){
                //催化剂提取结果
                //player.sendMessage(new TranslationTextComponent("originalfalse.text.todo"));
                if(craft.craft(tile.yss)!=null){
                    player.addItemStackToInventory(craft.craft(tile.yss));
                    player.getHeldItem(Hand.MAIN_HAND).setCount(player.getHeldItem(Hand.MAIN_HAND).getCount()-1);
                    LevelSystem.grantExp(player,10,worldIn);
                    tile.yss=new HashMap<>();
                }
            }else if(player.getHeldItem(Hand.MAIN_HAND).getItem().equals(Items.AIR)){
                //空手查看容器内容
                player.sendMessage(new TranslationTextComponent("originalfalse.text.items"));
                for(Item i:tile.yss.keySet()){
                    player.sendMessage(new StringTextComponent(tile.yss.get(i)+"*"+i.getName().getString()));
                }
            }else{
                //如果容器内没有玩家手里的东西那么就直接put
                if(tile.yss.get(player.getHeldItem(Hand.MAIN_HAND).getItem())==null) {
                    tile.yss.put(player.getHeldItem(Hand.MAIN_HAND).getItem(), player.getHeldItem(Hand.MAIN_HAND).getCount());
                }else{
                    //如果有那么就加上
                    tile.yss.put(player.getHeldItem(Hand.MAIN_HAND).getItem(), tile.yss.get(player.getHeldItem(Hand.MAIN_HAND).getItem())+player.getHeldItem(Hand.MAIN_HAND).getCount());
                }
                //player.sendMessage(new StringTextComponent(player.getHeldItem(Hand.MAIN_HAND).getCount()+"*"+player.getHeldItem(Hand.MAIN_HAND).getItem()));
                player.getHeldItem(Hand.MAIN_HAND).setCount(0);
            }
        }
        return ActionResultType.SUCCESS;
    }

    //放置时玩家当场暴毙
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(placer instanceof PlayerEntity){
            PlayerEntity player= (PlayerEntity) placer;
            if(!player.isCreative()){
                player.sendMessage(new TranslationTextComponent("originalfalse.text.placegssb"));
                player.onKillCommand();
            }
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> list=new ArrayList<>();
        list.add(new ItemStack(main.voidMeterial));
        return list;
    }
}
