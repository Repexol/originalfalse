package originalFalse.zycdojar.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.item.tier.stdTier;

public class netherstar_sword extends PickaxeItem {
    public netherstar_sword(  Properties builder) {
        super(new stdTier(), (int) (1-new stdTier().getAttackDamage()), (float) 0.5, builder);
        setRegistryName("neitherstar_sword");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote){
            if(playerIn.isSneaking()){
                playerIn.setHeldItem(handIn,new ItemStack(Items.AIR));
                playerIn.addItemStackToInventory(new ItemStack(itemregister.bladeCore));
                playerIn.addItemStackToInventory(new ItemStack(Items.STICK,2));
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
