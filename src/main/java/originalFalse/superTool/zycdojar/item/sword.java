package originalFalse.superTool.zycdojar.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.superTool.zycdojar.main;
import originalFalse.zycdojar.item.spellStuder;
import originalFalse.zycdojar.item.tier.stdTier;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class sword extends SwordItem {
    /**
     * 调试专用伪命令
     * @param event
     */
    @SubscribeEvent
    public static void onChat(ServerChatEvent event) {
        String[] content = event.getMessage().split(" ");
        if (event.getPlayer().isCreative())
            if (event.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem() instanceof sword||event.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem().equals(main.icon)) {
                if (content[0].equals("seed")) {
                    event.getPlayer().getHeldItem(Hand.MAIN_HAND).getOrCreateTag().put("seed", IntNBT.valueOf(Integer.parseInt(content[1])));
                    event.getPlayer().getHeldItem(Hand.MAIN_HAND).getOrCreateTag().put("designSeed", IntNBT.valueOf(Integer.parseInt(content[1])));
                    event.getPlayer().sendMessage(new StringTextComponent("你已经修改为" + content[1]));
                }
            }
    }
    public sword(Properties builder) {
        super(new stdTier(), Math.round(1-new stdTier().getAttackDamage()), 1.6f, builder);
        setRegistryName("sword");
    }

    /**
     * 在伤害别的实体的时候发挥作用
     * @param event
     */
    @SubscribeEvent
    public static void onKill(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player instanceof ServerPlayerEntity){
            ServerPlayerEntity playerEntity= (ServerPlayerEntity) player;
            if(playerEntity.getHeldItem(Hand.MAIN_HAND).getItem().equals(main.sword)) {
                int seed = playerEntity.getHeldItem(Hand.MAIN_HAND).getOrCreateTag().getInt("seed");
                int damage = Math.abs(new Random(seed).nextInt()) % 160;
                if (event.getTarget() instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity) event.getTarget();
                    entity.attackEntityFrom(DamageSource.causePlayerDamage(playerEntity), damage);
                }
            }
        }
    }

    /**
     * 添加注释
     * @param stack
     * @param worldIn
     * @param tooltip
     * @param flagIn
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        int seed = stack.getOrCreateTag().getInt("seed");
        //伤害算法
        int damage = Math.abs(new Random(seed).nextInt()) % 160;
        tooltip.add(new TranslationTextComponent("originalfalse.weapon.text.damageinfo"));
        tooltip.add(new TranslationTextComponent("originalfalse.weapon.text.damage",damage));
        tooltip.add(new TranslationTextComponent("originalfalse.weapon.text.seed",seed));
    }
}
