package originalFalse.zycdojar.item;

import net.minecraft.client.MinecraftGame;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.MinecraftVersion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.api.wrapper.spellHandle;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;

import java.util.*;

/**
 * 魔杖
 */
public class wand extends Item {
    public static Set<spellHandle> handles=new HashSet<>();
    public static Map<String,String> chooseSpell=new HashMap<>();
    public wand(Properties properties) {
        super(properties);
        setRegistryName("wand");
    }

    /**
     * 右键
     * @param worldIn
     * @param playerIn
     * @param handIn
     * @return
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote){
            //查询玩家选择的魔咒
            String spell=chooseSpell.get(playerIn.getUniqueID().toString());
            if(spell==null){
                spell="null";
            }
            if(playerIn.isSneaking()){
                //如果玩家是蹲着的，那么随机选择魔咒
                spell=randomChooseSpell(playerIn,worldIn);
                chooseSpell.put(playerIn.getUniqueID().toString(),spell);
            }else {
                //玩家最后一次攻击的怪物
                LivingEntity entity=playerIn.getLastAttackedEntity();
                //如果没有
                //那么目标就是玩家自己
                if(entity==null){
                    entity=playerIn;
                }
                //尝试在已知魔咒里找
                if(spell.equals("null")){
                    playerIn.sendMessage(new TranslationTextComponent("originalfalse.text.pchangespell"));
                }else if(spell.equals("test")){
                    playerIn.sendMessage(new StringTextComponent("测试法术"));
                }else if(spell.equals("wither")){
                    if(LevelSystem.removeMana(playerIn,40,worldIn)) {
                        entity.addPotionEffect(new EffectInstance(Effects.WITHER, 15, 4));
                    }
                }else if(spell.equals("attack")){
                    if(LevelSystem.removeMana(playerIn,20,worldIn)) {
                        playerIn.attackTargetEntityWithCurrentItem(entity);
                    }
                }
                else{
                    //找不到就到handle里找
                    boolean b=false;
                    for(spellHandle handle:handles){
                        if(handle.spell(spell, entity,(ServerPlayerEntity) playerIn)){
                            b=true;
                            break;
                        }
                    }
                    //实在找不到那么就报错
                    if(!b){
                        playerIn.sendMessage(new StringTextComponent("错误：未可用法术；你是否删除了附属/附属bug？"));
                    }
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    /**
     * 随机选择魔咒
     * @param entity
     * @param worldIn
     * @return
     */
    private static String randomChooseSpell(PlayerEntity entity,World worldIn){
        worldSaveData data=worldSaveData.get(worldIn);
        //魔咒列表
        if(data.getSpell(entity).isEmpty()){
            return "null";
        }
        while (true) {
            for (String spell : data.getSpell(entity)) {
                if (Math.abs(new Random().nextInt())%10<=1){
                    entity.sendMessage(new TranslationTextComponent("originalfalse.text.changespell",spell));
                    return spell;
                }
            }
            if (Math.abs(new Random().nextInt())%10<=1){
                return "null";
            }
        }
    }
}
