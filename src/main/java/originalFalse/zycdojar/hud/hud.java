package originalFalse.zycdojar.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.fonts.Font;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import originalFalse.zycdojar.api.wrapper.client;

import java.awt.*;

public class hud extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation("neutrino", "textures/gui/hud.png");

    public hud() {
        this.width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.minecraft = Minecraft.getInstance();
    }

    public void render() {
        //RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawString(Minecraft.getInstance().fontRenderer, "等级:"+ client.level, width/4, height/4+10, 0x33a1c9);
        this.drawString(Minecraft.getInstance().fontRenderer, "经验:"+ client.exp, width/4, height/4, 0x33a1c9);
        this.drawString(Minecraft.getInstance().fontRenderer, "魔力:"+ client.mana+"/"+(20 + (client.level * 10)), width/4, height/4-10, 0x33a1c9);
        this.drawString(Minecraft.getInstance().fontRenderer, "自然之息:"+ client.ne, width/4, height/4-20, 0x33a1c9);
        //blit(width / 2 - 16, height / 2 - 64, 0, 0, 32, 32, 32, 32);
    }
}
