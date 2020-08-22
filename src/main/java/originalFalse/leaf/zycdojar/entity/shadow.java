package originalFalse.leaf.zycdojar.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public class shadow extends MobRenderer<leaf, PlayerModel<leaf>> {
    public shadow(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PlayerModel<leaf>(1,false), 1.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(leaf entity) {
        //Minecraft.getInstance().player.get
        return new ResourceLocation("originalfalse", "textures/entity/leaf.png");
    }

    public shadow(EntityRendererManager renderManagerIn, PlayerModel<leaf> entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }
}
