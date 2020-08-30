package originalFalse.nuclearCraft.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;

import javax.annotation.Nullable;
import java.util.List;

public class battery extends Item {
    public static final int MAX_NE=10000;
    public battery(Properties properties) {
        super(properties);
        setRegistryName("battery");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(handIn);
            int playerNe = NESystem.getNE(playerIn);
            int ne = stack.getTag().getInt("ne");
            if (playerIn.isSneaking()) {
                if (playerNe + ne > NESystem.MAX_NE) {
                    playerIn.sendMessage(new TranslationTextComponent("originalfalse.nuclearcraft.text.batteryGrant", (NESystem.MAX_NE - playerNe)));
                    NESystem.grantNE(playerIn, NESystem.MAX_NE - playerNe);
                    stack.getTag().put("ne", IntNBT.valueOf(ne - (NESystem.MAX_NE - playerNe)));
                } else {
                    playerIn.sendMessage(new TranslationTextComponent("originalfalse.nuclearcraft.text.batteryGrant", ne));
                    NESystem.grantNE(playerIn, ne);
                    stack.getTag().put("ne", IntNBT.valueOf(0));
                }
            } else {
                if (playerNe + ne > MAX_NE) {
                    playerIn.sendMessage(new TranslationTextComponent("originalfalse.nuclearcraft.text.batteryRemove", (MAX_NE - ne)));
                    stack.getTag().put("ne", IntNBT.valueOf(MAX_NE));
                    NESystem.removeNE(playerIn, MAX_NE - ne);
                } else {
                    playerIn.sendMessage(new TranslationTextComponent("originalfalse.nuclearcraft.text.batteryRemove", (playerNe)));
                    NESystem.removeNE(playerIn, playerNe);
                    stack.getTag().put("ne", IntNBT.valueOf(ne + playerNe));
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("originalfalse.nuclearcraft.text.battery",""+stack.getTag().getInt("ne")));
    }
}
