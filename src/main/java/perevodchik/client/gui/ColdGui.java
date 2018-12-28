package perevodchik.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import perevodchik.Main;
import perevodchik.capability.cold.ColdProvider;
import perevodchik.capability.cold.ICold;

public class ColdGui extends GuiScreen {
    ResourceLocation bar = new ResourceLocation(Main.MODID, "textures/gui/bar.png");
    private final int w = 9;
    private final int h = 9;

    public void draw() {
        Minecraft mc = Minecraft.getMinecraft();
        ICold cold = Minecraft.getMinecraft().player.getCapability(ColdProvider.cold, null);
        mc.renderEngine.bindTexture(bar);

        for (int c = 0; c < cold.getCold(); c++) {
            drawTexturedModalRect(140 + (8 * c), 226, 1, 1, w, h);
        }
    }
}
