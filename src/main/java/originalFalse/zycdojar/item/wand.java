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

public class wand extends Item {
    public static Set<spellHandle> handles=new HashSet<>();
    public static Map<String,String> chooseSpell=new HashMap<>();
    public wand(Properties properties) {
        super(properties);
        setRegistryName("wand");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote){
            String spell=chooseSpell.get(playerIn.getUniqueID().toString());
            if(spell==null){
                spell="null";
            }
            if(playerIn.isSneaking()){
                spell=randomChooseSpell(playerIn,worldIn);
                chooseSpell.put(playerIn.getUniqueID().toString(),spell);
            }else {
                LivingEntity entity=playerIn.getLastAttackedEntity();
                if(entity==null){
                    entity=playerIn;
                }
                if(spell.equals("null")){
                    playerIn.sendMessage(new TranslationTextComponent("originalfalse.text.pchangespell"));
                }else if(spell.equals("test")){
                    playerIn.sendMessage(new StringTextComponent("≤‚ ‘∑® ı"));
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
                    boolean b=false;
                    for(spellHandle handle:handles){
                        if(handle.spell(spell, entity,(ServerPlayerEntity) playerIn)){
                            b=true;
                            break;
                        }
                    }
                    if(!b){
                        playerIn.sendMessage(new StringTextComponent("¥ÌŒÛ£∫Œ¥ø…”√∑® ı£ªƒ„ «∑Ò…æ≥˝¡À∏Ω Ù/∏Ω Ùbug£ø"));
                    }
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
    private static String randomChooseSpell(PlayerEntity entity,World worldIn){
        worldSaveData data=worldSaveData.get(worldIn);
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
