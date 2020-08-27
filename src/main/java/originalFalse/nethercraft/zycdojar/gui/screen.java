package originalFalse.nethercraft.zycdojar.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import originalFalse.tech.zycdojar.main;

public class screen extends ContainerScreen<containter> {
    private ResourceLocation background = new ResourceLocation("originalfalse", "textures/gui/container.png");
    private int textureWidth = 176;
    private int textureHeight = 166;

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        renderBackground();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        renderHoveredToolTip(p_render_1_,p_render_2_);
    }

    public screen(containter screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize=textureWidth;
        this.ySize=textureHeight;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text;
        if(getContainer().tile.inventory.getStackInSlot(1).getItem().equals(main.voidMeterial)){
            if(getContainer().tile.ticker!=0){
                text = new TranslationTextComponent("originalfalse.text.nethercraft.ok",getContainer().tile.ticker+"").getString();
            }else {
                text = new TranslationTextComponent("originalfalse.text.nethercraft.wellthen").getString();
            }
        }else{
            text=new TranslationTextComponent("originalfalse.text.nethercraft.first").getString();
        }
        this.drawString(this.font, text, 62, 20, 0xeb0505);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.renderBackground();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
    }
}
