package originalFalse.tech.zycdojar.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import originalFalse.data.gotoExcaption;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.block.tile.dengjiajiaohuanyiTile;
import originalFalse.tech.zycdojar.block.tile.lightingTile;
import originalFalse.tech.zycdojar.main;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.skybiome.skyBiome;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 自然之息注入仪
 */
public class dengjiajiaohuanyi extends Block {
    public dengjiajiaohuanyi() {
        super(CLASS.pps);
        setRegistryName("deng_jia_jiao_huan_yi");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new dengjiajiaohuanyiTile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote) {
            dengjiajiaohuanyiTile tile = (dengjiajiaohuanyiTile) worldIn.getTileEntity(pos);
            if(tile.check(player)){
                //塞入自然之息宝珠
            if (player.getHeldItem(handIn).getItem().equals(main.pearl)) {
                if (player.getHeldItem(handIn).getTag().getString("owner").equals("")) {
                    //如果宝珠没有设置主人
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.noOwner"));
                } else {
                    //设置成功
                    tile.getTileData().put("player", StringNBT.valueOf(player.getHeldItem(handIn).getTag().getString("owner")));
                    player.getHeldItem(handIn).setCount(player.getHeldItem(handIn).getCount() - 1);
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));
                }
            } else {
                //如果机器没有主人
                if(!tile.getTileData().getString("player").equals("")) {
                    ItemStack stack=player.getHeldItem(handIn);
                    //魔杖右键取得结果
                    if(stack.getItem().equals(itemregister.wand)){

                            Map<Item,Integer> map=new HashMap<>();
                            for(ItemStack stack1:tile.stacks){
                                map.put(stack1.getItem(),stack1.getCount());
                            }
                            map=tile.items;
                                try {
                                    //煤炭变钻石
                                    if (map.getOrDefault(Items.COAL, 0) == 5) {
                                        Map result = new HashMap();
                                        result.put("mana", 100);
                                        result.put("stack", new ItemStack(Items.DIAMOND, 1));
                                        throw new gotoExcaption(result);
                                    }else if(map.getOrDefault(main.neshied,0)==1){
                                        //自然之盾变虚空之盾
                                        if(map.getOrDefault(main.voidMeterial,0)==2) {
                                            Map result = new HashMap();
                                            result.put("mana", 200);
                                            result.put("stack", new ItemStack(main.voidshied, 1));
                                            throw new gotoExcaption(result);
                                        }
                                    }else if(map.getOrDefault(itemregister.getSSBI,0)==1){
                                        //原初节点变无中生有仪式石
                                            Map result = new HashMap();
                                            result.put("mana", 200);
                                            result.put("stack", new ItemStack(main.wuzhongshenyouyishiI, 1));
                                            throw new gotoExcaption(result);
                                    }else if(map.getOrDefault(Items.ENDER_PEARL,0)==5){
                                        //末影珍珠变传送仪
                                        Map result = new HashMap();
                                        result.put("mana", 200);
                                        result.put("stack", new ItemStack(main.teleporterI, 1));
                                        throw new gotoExcaption(result);
                                    }
                                    else if(map.getOrDefault(Items.DIRT,0)==2){
                                        if(worldIn.getBiome(pos) instanceof skyBiome) {
                                            //彩蛋物品：凋零苹果
                                            Map result = new HashMap();
                                            result.put("mana", 200);
                                            result.put("stack", new ItemStack(main.witherApple, 1));
                                            throw new gotoExcaption(result);
                                        }
                                    }
                                }catch (gotoExcaption excaption){
                                    //通过excaption跳转
                                    if (NESystem.removeNE(player, (Integer) excaption.data.get("mana"))) {
                                        player.addItemStackToInventory((ItemStack) excaption.data.get("stack"));
                                        tile.items=new HashMap<>();
                                        tile.markDirty();
                                        player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));
                                    }

                                }
                        }else if(stack.getItem().equals(Items.AIR)){
                        //查看容器内容
                        Map<Item,Integer> map=new HashMap<>();
                        for(ItemStack stack1:tile.stacks){
                            map.put(stack1.getItem(),stack1.getCount());
                        }
                        map=tile.items;
                        for(Item i:map.keySet()){
                            player.sendMessage(new StringTextComponent(map.get(i)+"*"+i.getName().getString()));
                        }
                        }else {
                        //塞入物品
                        tile.items.put(stack.getItem(),tile.items.getOrDefault(stack.getItem(),0)+1);
                        tile.stacks.add(new ItemStack(stack.getItem(),1));
                        tile.markDirty();
                        stack.setCount(stack.getCount()-1);
                    }

                }else {
                    player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.noOwner"));
                }}
            }
        }
        return ActionResultType.SUCCESS;
    }
}
