package perevodchik.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import perevodchik.Main;
import perevodchik.capability.skill.IStat;
import perevodchik.capability.skill.StatProvider;

public class PlayerStatsGui extends GuiScreen {
    private static final ResourceLocation MAIN = new ResourceLocation(Main.MODID + ":textures/gui/quest_book.png");
    private EntityPlayer player;
    private IStat stats;

    public PlayerStatsGui(EntityPlayer player) {
        this.player = player;
        this.stats = player.getCapability(StatProvider.skill, null);
    }

    public void initGui() {
        this.buttonList.clear();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(MAIN);
        int x = (this.width - 180) / 2;
        int y = 2;
        int guiWidth = 188;
        int guiHeight = 233;
        drawTexturedModalRect(x, y, 0, 0, guiWidth, guiHeight);

        {
            drawString(this.fontRenderer, String.valueOf("Лук: " + stats.getBowSkill()), 200, 25, 231322);
            drawString(this.fontRenderer, String.valueOf("Паличка: " + stats.getScepterSkill()), 200, 35, 231322);
            drawString(this.fontRenderer, String.valueOf("Меч: " + stats.getSwordSkill()), 200, 45, 231322);
            drawString(this.fontRenderer, String.valueOf("Щит: " + stats.getShieldSkill()), 200, 55, 231322);
            drawString(this.fontRenderer, String.valueOf("Фермер: " + stats.getFarmerSkill()), 200, 65, 231322);
            drawString(this.fontRenderer, String.valueOf("Рудокоп: " + stats.getMinerSkill()), 200, 75, 231322);
        }

        /*this.mc.getRenderItem().renderItemAndEffectIntoGUI(questList.get(currentQuest).getReward(), 160, 25);
        drawString(this.fontRenderer, String.valueOf(questList.get(currentQuest).getReward().stackSize) + "шт.", 200, 30, 231322);

        this.mc.getRenderItem().renderItemAndEffectIntoGUI(item, 160, 50);
        drawString(this.fontRenderer, String.valueOf(questList.get(currentQuest).getTargetAmount()) + "шт.", 200, 55, 231322);
        this.mc.getRenderItem().renderItemAndEffectIntoGUI(questList.get(currentQuest).getWanted(), 160, 50);
        drawString(this.fontRenderer, String.valueOf(questList.get(currentQuest).getWanted().stackSize) + "шт.", 200, 55, 231322);*/
    }

    public void updateScreen()
    {
        super.updateScreen();
    }

    protected void actionPerformed(GuiButton button){
        if(button.id == 0) {
        }
    }
}
