package originalFalse.zycdojar.item.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
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
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.registyevent.itemregister;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ManaCore extends Block {
    public static Set<String> spells=new HashSet<>();
    public ManaCore() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(-1,5).notSolid());
        setRegistryName("manacore");
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            if(player.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.blade)){
                if(LevelSystem.removeMana(player,500,worldIn)) {
                    ItemStack stack = new ItemStack(itemregister.icon, 1);
                    player.setHeldItem(Hand.MAIN_HAND, stack);
                    player.sendMessage(new TranslationTextComponent("originalfalse.text.manaing"));
                }
                //worldIn.createExplosion(player,4,4,4,4, Explosion.Mode.DESTROY);
            }if(player.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.paper)){
                String spellName=randomSpell();
                ItemStack item=new ItemStack(itemregister.spellBook,1);
                item.getOrCreateTag().put("spellbook", StringNBT.valueOf(spellName));
                if(Math.abs(new Random().nextInt())%4==1) {
                    player.setHeldItem(Hand.MAIN_HAND, item);
                    player.sendMessage(new TranslationTextComponent("originalfalse.text.getBook", spellName));
                }else{
                    player.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.AIR));
                    player.sendMessage(new TranslationTextComponent("originalfalse.text.noneBook"));
                }
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
