package originalFalse.zycdojar.item.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import originalFalse.tech.zycdojar.main;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.registyevent.itemregister;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 魔力核心
 */
public class ManaCore extends Block {
    public static Set<String> spells=new HashSet<>();
    public ManaCore() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(-1,5).notSolid());
        setRegistryName("manacore");
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
            //注魔神剑
            if(player.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.blade)){
                if(LevelSystem.removeMana(player,500,worldIn)) {
                    ItemStack stack = new ItemStack(itemregister.icon, 1);
                    player.setHeldItem(Hand.MAIN_HAND, stack);
                    player.sendMessage(new TranslationTextComponent("originalfalse.text.manaing"));
                }
                //worldIn.createExplosion(player,4,4,4,4, Explosion.Mode.DESTROY);
            }else if(player.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.paper)){
                //获取咒语书
                String spellName=randomSpell();
                ItemStack item=new ItemStack(itemregister.spellBook,1);
                item.getOrCreateTag().put("spellbook", StringNBT.valueOf(spellName));
                if(Math.abs(new Random().nextInt())%4==1) {
                    player.setHeldItem(Hand.MAIN_HAND, item);
                    player.sendMessage(new TranslationTextComponent("originalfalse.text.getBook", spellName));
                }else{
                    //屁都没获取
                    player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.AIR));
                    player.sendMessage(new TranslationTextComponent("originalfalse.text.noneBook"));
                }
            }else if(player.getHeldItem(handIn).getItem().equals(itemregister.superShield)){
                //普通盾升级自然之盾
                if(LevelSystem.removeMana(player,233,worldIn))
                player.setHeldItem(handIn,new ItemStack(main.neshied,1));
            }else if(player.getHeldItem(handIn).getItem().equals(Items.ENDER_EYE)){
                //末影之眼升级自然之息宝珠
                if(LevelSystem.removeMana(player,233,worldIn))
                player.setHeldItem(handIn,new ItemStack(main.pearl,1));
            }else if(player.getHeldItem(handIn).getItem().equals(itemregister.ManaCorei)){
                //魔力核心升级自然之息注入器
                if(LevelSystem.removeMana(player,233,worldIn))
                    player.setHeldItem(handIn,new ItemStack(main.dengjiajiaohuanyiI,1));
            }else if(player.getHeldItem(handIn).getItem().equals(Items.DIRT)){
                //泥土升级泥土能发电机
                if(LevelSystem.removeMana(player,233,worldIn))
                    player.setHeldItem(handIn,new ItemStack(main.nitunengfadianjiI,1));
            }else if(player.getHeldItem(handIn).getItem().equals(Items.FLINT)){
                //打火石升级雷霆仪
                if(LevelSystem.removeMana(player,233,worldIn))
                    player.setHeldItem(handIn,new ItemStack(main.lightingI,1));
            }
        }
        return ActionResultType.SUCCESS;
    }
    public static String randomSpell(){

        while (true)
        for(String t:spells){
            if (Math.abs(new Random().nextInt())%10<=1){
                return t;
            }
        }
    }
    public static void initialSpells(){
        spells.add("wither");
        spells.add("attack");
    }
}
