package originalFalse.tech.zycdojar.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import originalFalse.tech.zycdojar.main;

public class staff_piece extends Item {
    public staff_piece(Properties properties) {
        super(properties);
        setRegistryName("staff_piece");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(handIn);
            stack.setCount(stack.getCount()-1);
            playerIn.addItemStackToInventory(new ItemStack(main.staff,1));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
