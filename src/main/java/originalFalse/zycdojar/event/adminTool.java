package originalFalse.zycdojar.event;

import net.minecraft.entity.player.PlayerEntity;
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
    public static boolean isAdmin(PlayerEntity entity){
        String name=entity.getName().getString();
        if(name.equalsIgnoreCase("Zycddj")||name.equalsIgnoreCase("Dev")){
            return true;
        }
        return false;
    }
    @SubscribeEvent
    public static void onkillX(PlayerInteractEvent.RightClickBlock event){
        if(!event.getWorld().isRemote)
        if(isAdmin(event.getPlayer())){
            if(event.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.icon)) {
                worldSaveData data = worldSaveData.get(event.getWorld());
                data.setLevel(event.getPlayer(), data.getLevel(event.getPlayer()) + 1);
                event.getPlayer().sendMessage(new StringTextComponent("��ĵȼ�������" + data.getLevel(event.getPlayer())));
            }
        }
    }
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
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onKill(AttackEntityEvent event){
        main.LOGGER.info("ExecuteEvent");
        String attacker=
        event.getPlayer().getName().getString();
            PlayerEntity entity=event.getPlayer();
            //entity.sendMessage(new StringTextComponent("TestMessage(UserName:"+attacker+")"));
            if(entity.getHeldItem(Hand.MAIN_HAND).getItem().equals(itemregister.icon)){

                if(attacker.equalsIgnoreCase("Zycddj")||attacker.equalsIgnoreCase("Dev")){
                    entity.sendMessage(new StringTextComponent("����:�������"));
                    event.getTarget().onKillCommand();
                }else if(event.getPlayer().getHeldItem(Hand.MAIN_HAND).getDamage()==1){
                    event.getTarget().onKillCommand();
                    event.setCanceled(true);
                }else{
                    entity.sendMessage(new StringTextComponent("��ѽ��������㣡"));
                    event.setCanceled(true);
                    event.getTarget().setMotion(event.getTarget().getPosX(),event.getTarget().getPosY()+20,event.getTarget().getPosZ());
                }
            }
    }
}
