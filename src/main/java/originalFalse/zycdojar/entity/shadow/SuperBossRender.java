package originalFalse.zycdojar.entity.shadow;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import originalFalse.zycdojar.entity.SuperBoss;
import originalFalse.zycdojar.entity.model.SuperBossModel;

public class SuperBossRender extends MobRenderer<SuperBoss, SuperBossModel> {
    public SuperBossRender(EntityRendererManager renderManagerIn, SuperBossModel entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }
    public SuperBossRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SuperBossModel(), 1F);
    }

    @Override
    public ResourceLocation getEntityTexture(SuperBoss entity) {
        return new ResourceLocation("originalfalse","textures/entity/SuperBoss.png");
    }
}
