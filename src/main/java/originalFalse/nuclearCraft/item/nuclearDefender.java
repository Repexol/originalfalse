package originalFalse.nuclearCraft.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class nuclearDefender extends Item {
    public nuclearDefender(Properties properties) {
        super(properties);
        setRegistryName("nc_def");
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(entityIn instanceof PlayerEntity)
            if(!worldIn.isRemote){
                PlayerEntity player= (PlayerEntity) entityIn;
                player.removePotionEffect(Effects.WITHER);
            }
    }
}
