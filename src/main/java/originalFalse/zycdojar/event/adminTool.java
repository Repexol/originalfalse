package originalFalse.zycdojar.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import originalFalse.zycdojar.event.dataCenter.worldSaveData;
import originalFalse.zycdojar.event.registyevent.itemregister;
import originalFalse.zycdojar.main;

@Mod.EventBusSubscriber()
public class adminTool {
    @Deprecated
    public static boolean isAdmin(PlayerEntity entity){
        String name=entity.getName().getString();
        if(name.startsWith("Player")||name.equalsIgnoreCase("Dev")){
            return true;
        }
        return false;
    }

    /**
     * 右键升级
     * @param event
     */
    @SubscribeEvent
    public static void onkillX(PlayerInteractEvent.RightClickBlock event){
        if(!event.getWorld().isRemote)
        if(isAdmin(event.getPlayer())){
            if(event.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.icon)) {
                worldSaveData data = worldSaveData.get(event.getWorld());
                data.setLevel(event.getPlayer(), data.getLevel(event.getPlayer()) + 1);
                event.getPlayer().sendMessage(new StringTextComponent("你的等级现在是" + data.getLevel(event.getPlayer())));
            }
        }
    }

    /**
     * 防御
     * @param event
     */
    @SubscribeEvent
    public static void onEno(LivingDamageEvent event){
        if(event.getEntity() instanceof PlayerEntity){
            PlayerEntity entity= (PlayerEntity) event.getEntity();
            if(entity.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.icon)||entity.getHeldItem(Hand.OFF_HAND).getItem().equals(itemregister.icon))
            if(isAdmin(entity)){
                event.setCanceled(true);
            }
        }
    }

    /**
     * 无限伤害
     * @param event
     */
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onKill(AttackEntityEvent event){
        if(event.getPlayer() instanceof ServerPlayerEntity) {
            main.LOGGER.info("ExecuteEvent");
            String attacker =
                    event.getPlayer().getName().getString();
            PlayerEntity entity = event.getPlayer();
            //entity.sendMessage(new StringTextComponent("TestMessage(UserName:"+attacker+")"));
            if (entity.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.icon)) {

                if(entity.isSneaking()){
                    event.getTarget().onKillCommand();
                    event.getPlayer().sendMessage(new StringTextComponent("已造成无限伤害"));
                }else {
                    event.getTarget().attackEntityFrom(DamageSource.OUT_OF_WORLD,Float.MAX_VALUE);
                }
                /*if (attacker.equalsIgnoreCase("Zycddj") || attacker.equalsIgnoreCase("Dev")) {
                    entity.sendMessage(new StringTextComponent("断刃:已清除！"));
                    event.getTarget().onKillCommand();
                } else if (event.getPlayer().getHeldItem(Hand.MAIN_HAND).getDamage() == 1) {
                    event.getTarget().onKillCommand();
                    event.setCanceled(true);
                }*//*else{
                    entity.sendMessage(new StringTextComponent("这把剑不属于你！"));
                    event.setCanceled(true);
                    event.getTarget().setMotion(event.getTarget().getPosX(),event.getTarget().getPosY()+20,event.getTarget().getPosZ());
                }*/
            }
        }
    }
}
