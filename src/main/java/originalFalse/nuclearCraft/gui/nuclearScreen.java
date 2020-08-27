package originalFalse.nuclearCraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class nuclearScreen extends ContainerScreen<nuclearui> {
    private ResourceLocation background = new ResourceLocation("originalfalse", "textures/gui/container.png");
    private int textureWidth = 176;
    private int textureHeight = 166;
    public nuclearScreen(nuclearui screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize=textureWidth;
        this.ySize=textureHeight;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.drawString(this.font, "左侧放燃料，中间放珍珠，右侧放稳定器", 22, 0, 0xeb0505);
        this.drawString(this.font, "温度:"+getContainer().tile.hot+"单燃料消耗:"+(getContainer().tile.ticker/40)+"%", 42, 10, 0x33a1c9);
        this.drawString(this.font, "产能:"+getContainer().tile.engry+"理论产能:"+getContainer().tile.realengry, 42, 20, 0x33a1c9);
        this.drawString(this.font, "稳定值:"+getContainer().tile.stable, 82, 30, 0x33a1c9);
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
