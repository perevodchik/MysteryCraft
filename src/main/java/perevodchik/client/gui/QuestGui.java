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
import perevodchik.capability.quest.QuestProvider;
import perevodchik.entity.villager.EntityGuildMaster;
import perevodchik.event.ModEventFactory;
import perevodchik.quest.QuestList;

public class QuestGui extends GuiScreen {
    private static final ResourceLocation QUEST = new ResourceLocation(Main.MODID + ":textures/gui/quest_book.png");
    private GuiButton selectQuest;
    private GuiButton previousQuest;
    private GuiButton nextQuest;
    private EntityPlayer player;
    private EntityGuildMaster villager;
    private int currentQuest = 0;
    private QuestList questList;
    private IQuestContainer container;

    public QuestGui(EntityPlayer player, EntityGuildMaster villager) {
        this.player = player;
        this.villager = villager;
        this.questList = villager.getQuestList();
        this.container = player.getCapability(QuestProvider.quest, null);
    }

    public void initGui() {
        this.buttonList.clear();
        previousQuest = this.addButton(new GuiButton(0, 150, 200, 30, 20,"Назад"));
        nextQuest = this.addButton(new GuiButton(1, 260, 200, 30, 20, "Вперед"));
        selectQuest = this.addButton(new GuiButton(2, 190, 200, 40, 20, "Взяти"));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(QUEST);
        int x = (this.width - 180) / 2;
        int y = 2;
        int guiWidth = 188;
        int guiHeight = 233;
        drawTexturedModalRect(x, y, 0, 0, guiWidth, guiHeight);
        nextQuest.drawButton(this.mc, mouseX, mouseY, 1.0F);
        previousQuest.drawButton(this.mc, mouseX, mouseY, 1.0F);
        selectQuest.drawButton(this.mc, mouseX, mouseY, 1.0F);

        if(!questList.isEmpty()) {
            this.mc.getRenderItem().renderItemAndEffectIntoGUI(questList.get(currentQuest).getReward(), 160, 25);
            drawString(this.fontRenderer, String.valueOf(questList.get(currentQuest).getReward().stackSize) + "шт.", 200, 30, 231322);

            if(questList.get(currentQuest).getType()) {
                ItemStack item = new ItemStack(Items.FISH);
                Class c = questList.get(currentQuest).getTarget();
                if(c == EntityZombie.class) {
                    item = new ItemStack(Items.SKULL, 1, 2);
                } else if(c == EntitySkeleton.class) {
                    item = new ItemStack(Items.SKULL, 1);
                } else if(c == EntityCreeper.class) {
                    item = new ItemStack(Items.SKULL, 1, 4);
                } else if(c == EntityWitherSkeleton.class) {
                    item = new ItemStack(Items.SKULL, 1, 1);
                } else if(c == EntityDragon.class) {
                    item = new ItemStack(Items.SKULL, 1, 5);
                }
                this.mc.getRenderItem().renderItemAndEffectIntoGUI(item, 160, 50);
                drawString(this.fontRenderer, String.valueOf(questList.get(currentQuest).getTargetAmount()) + "шт.", 200, 55, 231322);
            } else {
            //if(questList.get(currentQuest).getWanted() != null) {
                this.mc.getRenderItem().renderItemAndEffectIntoGUI(questList.get(currentQuest).getWanted(), 160, 50);
                drawString(this.fontRenderer, String.valueOf(questList.get(currentQuest).getWanted().stackSize) + "шт.", 200, 55, 231322);
            }
        }
        drawString(this.fontRenderer, String.valueOf(currentQuest + 1), 100, 100, 231322);
    }

    public void updateScreen() {
        super.updateScreen();

        if (questList != null)
        {
            this.nextQuest.enabled = this.currentQuest < questList.size() - 1;
            this.previousQuest.enabled = this.currentQuest > 0;
            this.selectQuest.enabled = container.canSelectMore();
        }
    }

    protected void actionPerformed(GuiButton button){
        if(button.id == 0) {
            this.currentQuest--;
        }
        if(button.id == 1) {
            this.currentQuest++;
        }
        if(button.id == 2) {
            if(!ModEventFactory.onPlayerSelectedQuest(questList.get(currentQuest), villager, player) && container.canSelectMore()) {
                //player.getCapability(QuestProvider.quest, null).addQuest(questList.get(currentQuest));
                container.addQuest(questList.get(currentQuest));
                questList.remove(currentQuest);
                if(questList.isEmpty()) {
                    villager.initQuestList();
                    currentQuest = 0;
                }
                System.out.println("quest count " + container.size());
            }
        }
    }
}
