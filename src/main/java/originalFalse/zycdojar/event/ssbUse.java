package originalFalse.zycdojar.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.item.block.getSilverSpellsBox;
import zycdojar.Empty;

import java.util.HashSet;

@Empty
@Mod.EventBusSubscriber
public class ssbUse {
    /*
    @SubscribeEvent
    public static void onkillX(PlayerInteractEvent.RightClickBlock event){
        if((event.getWorld().getBlockState(event.getPos()).getBlock() instanceof getSilverSpellsBox)){
            getSilverSpellsBox box= (getSilverSpellsBox) event.getWorld().getBlockState(event.getPos()).getBlock();
            if(event.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem().equals(Items.AIR)){
                int a=1;
                event.getPlayer().sendMessage(new TranslationTextComponent("originalfalse.text.items"));
                for(ItemStack stack:getSilverSpellsBox.yssNew.get(event.getPos())){
                    event.getPlayer().sendMessage(new StringTextComponent(a+". "+stack.getItem().getName().getString()+"*"+stack.getCount()));
                    a++;
                }
            }else{
                if(getSilverSpellsBox.yssNew.get(event.getPos())==null){
                    getSilverSpellsBox.yssNew.put(event.getPos(),new HashSet<>());
                }
                getSilverSpellsBox.yssNew.get(event.getPos()).add(event.getPlayer().getHeldItem(Hand.MAIN_HAND));
                event.getPlayer().getHeldItem(Hand.MAIN_HAND).setCount(event.getPlayer().getHeldItem(Hand.MAIN_HAND).getCount()-1);
            }
        }
    }*/
}
