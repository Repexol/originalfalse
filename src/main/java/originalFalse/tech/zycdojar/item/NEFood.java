package originalFalse.tech.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class NEFood extends Item {
    private static EffectInstance effectInstance = new EffectInstance(Effects.INSTANT_HEALTH, 3 * 20, 1);
    private static Food food = (new Food.Builder())
            .saturation(10)
            .hunger(20)
            .effect((()->{
                return effectInstance;
            }),1)
            .build();
    public NEFood() {
        super(new Properties().group(ItemGroup.FOOD).food(food));
        setRegistryName("wither_apple");
    }

    @Override
    public void onUse(World worldIn, LivingEntity playerEntity, ItemStack stack, int count) {
        if(!worldIn.isRemote){
            if(playerEntity.isSneaking()){
                playerEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 9999, 100));
            }else {
                WitherEntity witherEntity = new WitherEntity(EntityType.WITHER, worldIn);
                witherEntity.setLocationAndAngles(playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 10.0F);
                worldIn.addEntity(witherEntity);
            }
        }
        super.onUse(worldIn, playerEntity, stack, count);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("祝你好运"));
    }
}
