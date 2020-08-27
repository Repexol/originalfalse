package originalFalse.leaf.zycdojar.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

public class shadow extends MobRenderer<leaf, PlayerModel<leaf>> {
    public shadow(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PlayerModel<leaf>(1,true), 1.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(leaf entity) {
        //Minecraft.getInstance().player.get
        ResourceLocation resourceLocation = DefaultPlayerSkin.getDefaultSkin(Minecraft.getInstance().player.getUniqueID());
        if (resourceLocation != null)
            return resourceLocation;
        return new ResourceLocation("originalfalse", "textures/entity/leaf.png");
    }

    public shadow(EntityRendererManager renderManagerIn, PlayerModel<leaf> entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }
}
