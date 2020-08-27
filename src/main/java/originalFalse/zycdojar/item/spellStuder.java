package originalFalse.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 咒语书
 */
@Mod.EventBusSubscriber
public class spellStuder extends Item {
    /**
     * 调试伪指令
     * @param event
     */
    @SubscribeEvent
    public static void onChat(ServerChatEvent event){
        String[] content=event.getMessage().split(" ");
        if(event.getPlayer().isCreative())
        if(event.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem() instanceof spellStuder){
            if(content[0].equals("set")){
                event.getPlayer().getHeldItem(Hand.MAIN_HAND).getOrCreateTag().put("spellbook", StringNBT.valueOf(content[1]));
                event.getPlayer().sendMessage(new StringTextComponent("你已经修改为"+content[1]));
            }
        }
    }
    public spellStuder(Properties properties) {
        super(properties);
        setRegistryName("spell_book");
    }

    /**
     * 右键学习
     * @param worldIn
     * @param playerIn
     * @param handIn
     * @return
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn,PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote) {
            worldSaveData data = worldSaveData.getInstance(worldIn);
            String spell=playerIn.getHeldItem(Hand.MAIN_HAND).getTag().getString("spellbook");
            //创造拿出的咒语书是没有咒语的，默认是测试咒语
            if(spell==null){
                spell="test";
            }
            if(data.study(playerIn, spell))playerIn.getHeldItem(Hand.MAIN_HAND).setCount(playerIn.getHeldItem(Hand.MAIN_HAND).getCount()-1);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    /**
     * 显示咒语名字
     * @param stack
     * @param worldIn
     * @param tooltip
     * @param flagIn
     */
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        String spell=getShareTag(stack).getString("spellbook");
        if(spell==null){
            spell="test";
        }
        tooltip.add(new TranslationTextComponent("originalfalse.text.book",spell));
    }

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {

        return true;
    }
}
