package originalFalse.tech.zycdojar;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.zycdojar.api.wrapper.spellHandle;

public class spell implements spellHandle {
    /**
     * ����ִ��
     * �Զ����api
     * @param spell
     * @param target
     * @param player
     * @return
     */
    @Override
    public boolean spell(String spell, LivingEntity target, ServerPlayerEntity player) {
        //��������
        if(spell.equals("tech-test")){
            player.sendMessage(new StringTextComponent("��ɹ���װ��of�Ƽ���չ"));
            return true;
        }else if(spell.equals("AngerOfNatural")){
            //��Ȼ֮ŭ
            if(NESystem.removeNE(player,10)) {
                LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(target.getEntityWorld(), target.getPosX(), target.getPosY(), target.getPosZ(), false);
                lightningBoltEntity.setFire(999);
                target.removePotionEffect(Effects.FIRE_RESISTANCE);
                target.getEntityWorld().addEntity(lightningBoltEntity);
            }else {
                player.sendMessage(new TranslationTextComponent("originalfalse.tech.text.noNE"));
            }
            return true;
        }else if(spell.equals("scan")){
            //ɨ�裨δ��ɣ�
            RayTraceResult result=player.getServerWorld().rayTraceBlocks(new RayTraceContext(player.getPositionVec().add(0,1,0),player.getLookVec(), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE,player));
            Vec3d obj=result.getHitVec();
            BlockPos pos=new BlockPos(obj.getX(),obj.getY(),obj.getZ());
            player.sendMessage(new StringTextComponent(player.getServerWorld().getBlockState(pos).getBlock().getNameTextComponent().getString()));
            return true;
        }
        return false;
    }
}
