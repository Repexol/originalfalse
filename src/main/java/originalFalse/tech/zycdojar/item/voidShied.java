package originalFalse.tech.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.tech.zycdojar.main;
import originalFalse.zycdojar.api.wrapper.LevelSystem;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;
import originalFalse.zycdojar.event.registyevent.itemregister;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class voidShied extends Item {
    public voidShied(Properties properties) {
        super(properties);
        setRegistryName("void_shied");
    }
    @SubscribeEvent
    public static void onAttact(LivingDamageEvent event){
        if(event.getEntity() instanceof ServerPlayerEntity){
            ServerPlayerEntity player= (ServerPlayerEntity) event.getEntity();
            if(player.getHeldItem(Hand.OFF_HAND).getItem().equals(main.voidshied)){
                //worldSaveData data=worldSaveData.getInstance(player.getServerWorld());
                        event.setCanceled(true);
            }
        }
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("originalfalse.tech.text.vshied"));
    }
}
