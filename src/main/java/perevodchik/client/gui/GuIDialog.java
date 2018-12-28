package perevodchik.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import perevodchik.Main;
import perevodchik.entity.villager.EntityGuildMaster;

@SideOnly(Side.CLIENT)
public class GuIDialog extends GuiScreen {
    private static final ResourceLocation MENU = new ResourceLocation(Main.MODID + ":textures/gui/quest_book.png");

    private World w;
    private EntityPlayer player;
    private EntityGuildMaster villager;

    private dialogButton questButton;
    private dialogButton merchantButton;

    public GuIDialog(EntityPlayer player, EntityGuildMaster villager, World w) {
        this.player = player;
        this.villager = villager;
        this.w = w;
    }

    public void initGui() {
        this.buttonList.clear();
        this.questButton = this.addButton(new GuIDialog.dialogButton(0, (this.width/2)-30, 50, 0));
        this.merchantButton = this.addButton(new GuIDialog.dialogButton(1, (this.width/2)-30, 100, 1));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (this.width - 180) / 2;
        int y = 2;
        this.mc.getTextureManager().bindTexture(MENU);
        int guiWidth = 188;
        int guiHeight = 233;
        drawTexturedModalRect(x, y, 0, 0, guiWidth, guiHeight);
        questButton.drawButton(this.mc, mouseX, mouseY);
        merchantButton.drawButton(this.mc, mouseX, mouseY);
    }

    protected void actionPerformed(GuiButton button){
        if (button == this.questButton) {
            FMLCommonHandler.instance().showGuiScreen(new QuestGui(player, villager));
        }

        else if (button == this.merchantButton) {
            //FMLCommonHandler.instance().showGuiScreen(new GuiMerchant(player.inventory, villager, w));
        }
    }

    @SideOnly(Side.CLIENT)
    public class dialogButton extends GuiButton {
        private int type;

        private dialogButton(int buttonId, int x, int y, int type) {
            super(buttonId, x, y, "");
            this.type = type;
        }

        private void drawButton(Minecraft mc, int mouseX, int mouseY)
        {
            if (this.visible)
            {
                boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(GuIDialog.MENU);
                int i = 187;
                int j = 1;
                int wd = 60;
                int ht = 16;

                if (type == 1) {
                    j += 16;
                    wd = 38;
                }

                if(flag) {
                    j += 33;
                }

                this.drawTexturedModalRect(this.x, this.y, i, j, wd, ht);
            }
        }

    }
}
