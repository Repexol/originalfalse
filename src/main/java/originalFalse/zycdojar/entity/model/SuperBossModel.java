package originalFalse.zycdojar.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import originalFalse.zycdojar.entity.SuperBoss;

public class SuperBossModel extends EntityModel<SuperBoss> {
    private final ModelRenderer body;
    @Override
    public void setRotationAngles(SuperBoss entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    public SuperBossModel(){
        textureHeight=64;
        textureWidth=64;
        this.body=new ModelRenderer(this);
        body.setRotationPoint(8.0f,24.0f,-8.0f);
        body.setTextureOffset(0,0).addBox(-16.0F, -16.0F, 0.0F, 16.0F, 10.0F, 16.0F, 0.0F, false);
    }
}
