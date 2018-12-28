package perevodchik.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import perevodchik.Main;
import perevodchik.capability.quest.IQuestContainer;
import perevodchik.capability.quest.Quest;
import perevodchik.capability.quest.QuestProvider;
import perevodchik.capability.quest.QuestUtil;
import perevodchik.quest.QuestManager;
import perevodchik.util.InventoryUtil;

import java.util.ArrayList;

public class GuiQuestBook extends GuiScreen {
    private static final ResourceLocation MENU = new ResourceLocation(Main.MODID + ":textures/gui/quest_book.png");
    private EntityPlayer player;

    private GuiButton prevPage;
    private GuiButton nextPage;
    private GuiButton cancelQuest;
    private GuiButton makeQuest;

    private QuestManager questManager;
    private ArrayList<Quest> questList;
    private Quest q;

    private IQuestContainer container;

    private int currentQuest = 0;

    public GuiQuestBook(EntityPlayer player) {
        this.player = player;
        this.questList = QuestManager.getSQL().get(player.getName());
        this.questManager = new QuestManager();
        this.container = player.getCapability(QuestProvider.quest, null);
    }

    public void initGui() {
        this.buttonList.clear();

        this.prevPage = this.addButton(new GuiButton(0, 150, 30,25, 15, "Назад"));
        this.nextPage = this.addButton(new GuiButton(1, 250, 30, 25, 15, "Вперед"));
        this.cancelQuest = this.addButton(new GuiButton(2, 145, 200, 40, 15, "Відмінити"));
        this.makeQuest = this.addButton(new GuiButton(3, 195, 200, 40, 15,"Здати"));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (this.width - 180) / 2;
        int y = 2;
        this.mc.getTextureManager().bindTexture(MENU);
        int guiWidth = 188;
        int guiHeight = 233;
        drawTexturedModalRect(x, y, 0, 0, guiWidth, guiHeight);

        if(!container.isEmpty()) {
            q = container.get(currentQuest);
        }

        prevPage.drawButton(this.mc, mouseX, mouseY, 1.0F);
        nextPage.drawButton(this.mc, mouseX, mouseY, 1.0F);
        cancelQuest.drawButton(this.mc, mouseX, mouseY, 1.0F);
        makeQuest.drawButton(this.mc, mouseX, mouseY, 1.0F);

        //drawString(this.fontRenderer, "ЦІЛЬ", 200, 50, 231322);
        drawCenteredString(this.fontRenderer, "ЦІЛЬ", 200, 50, 231322);
        //drawString(this.fontRenderer, "НАГОРОДА", 200, 85, 231322);
        drawCenteredString(this.fontRenderer, "НАГОРОДА", 200, 85, 231322);

        if(!container.isEmpty()) {
            this.mc.getRenderItem().renderItemAndEffectIntoGUI(q.getReward(), 160, 100);
            this.itemRender.renderItemOverlays(this.fontRenderer, q.getReward(), 160, 100);
            drawString(this.fontRenderer, String.valueOf(q.getReward().stackSize) + "шт.", 200, 105, 231322);

            if(q.getType()) {
                ItemStack item = new ItemStack(Items.FISH);
                Class c = q.getTarget();
                
                if(c.equals(EntityZombie.class)) {
                    item = new ItemStack(Items.SKULL, 1, 2);
                } else if(c.equals(EntitySkeleton.class)) {
                    item = new ItemStack(Items.SKULL, 1);
                } else if(c.equals(EntityCreeper.class)) {
                    item = new ItemStack(Items.SKULL, 1, 4);
                } else if(c.equals(EntityWitherSkeleton.class)) {
                    item = new ItemStack(Items.SKULL, 1, 1);
                } else if(c.equals(EntityDragon.class)) {
                    item = new ItemStack(Items.SKULL, 1, 5);
                }
                
                this.mc.getRenderItem().renderItemAndEffectIntoGUI(item, 160, 65);
                drawString(this.fontRenderer, String.valueOf(q.getCurrentTargetAmount() + " з " +
                        q.getTargetAmount()) + "шт.", 200, 65, 231322);
            } else {
                this.mc.getRenderItem().renderItemAndEffectIntoGUI(q.getWanted(), 160, 65);
                drawString(this.fontRenderer, String.valueOf(InventoryUtil.getCountItemInInv(q.getWanted().item, player.inventory) + " з " +
                        q.getWanted().stackSize) + "шт.", 200, 65, 231322);
            }
        }
    }

    public void updateScreen()
    {
        super.updateScreen();
        this.prevPage.enabled = this.currentQuest > 0;
        this.nextPage.enabled = this.currentQuest < container.size() - 1;
        this.cancelQuest.enabled = this.container.size() > 0;
        this.makeQuest.enabled = !container.isEmpty() ? QuestUtil.checkCondition(q, player) : false;
    }

    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            this.currentQuest--;
        }
        if (button.id == 1) {
            this.currentQuest++;
        }
        if (button.id == 2) {
            if(container.size() > 1 && currentQuest == container.size()-1) currentQuest--;
            container.removeQuest(q);
        }
        if (button.id == 3) {
           QuestUtil.makeQuest(q, player);
           container.removeQuest(q);
        }
    }

}
