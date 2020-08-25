package originalFalse.tech.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * 自然之息宝珠
 */
public class pearl extends Item {
    public pearl(Properties properties) {
        super(properties);
        setRegistryName("tech_pearl");
    }

    /**
     * 右键设置主人
     * @param worldIn
     * @param playerIn
     * @param handIn
     * @return
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote) {
            playerIn.getHeldItem(handIn).getOrCreateTag().put("owner", StringNBT.valueOf(playerIn.getUniqueID().toString()));
            playerIn.getHeldItem(handIn).getOrCreateTag().put("ownername", StringNBT.valueOf(playerIn.getName().getString()));
            playerIn.sendMessage(new TranslationTextComponent("originalfalse.tech.text.success"));

        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    /**
     * 添加注释
     * @param stack
     * @param worldIn
     * @param tooltip
     * @param flagIn
     */
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("originalfalse.tech.text.owner",
                stack.getOrCreateTag().getString("ownername")));
    }
}
