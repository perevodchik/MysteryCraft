package perevodchik.quest;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import perevodchik.Main;
import perevodchik.capability.quest.Quest;
import perevodchik.entity.villager.EntityGuildMaster;
import perevodchik.event.ModEventFactory;

import java.util.HashMap;
import java.util.Map;

public class QuestManager extends WorldSavedData {
    private static final String DATA_NAME = Main.MODID + "_quest";
    private static Map<String, QuestPlayerSelectedList> selectedQuestsList = new HashMap<>();
    public static int tickCounter;

    public QuestManager() {
        super(DATA_NAME);
    }

    public static QuestManager get(@NotNull World world) {
        MapStorage mapStorage = world.getMapStorage();
        assert mapStorage != null;
        QuestManager INSTANCE = (QuestManager) mapStorage.getOrLoadData(QuestManager.class, DATA_NAME);

        if(INSTANCE == null) {
            INSTANCE = new QuestManager();
            mapStorage.setData(DATA_NAME, INSTANCE);
        }
        return INSTANCE;
    }

    public void makeQuest(Quest q, @NotNull EntityPlayer iPlayer) {
        for(int c = 0; c<=iPlayer.inventory.getSizeInventory(); c++) {
            if(iPlayer.inventory.getStackInSlot(c).item == q.getWanted().item && iPlayer.inventory.getStackInSlot(c).stackSize >= q.getWanted().stackSize) {
             iPlayer.addItemStackToInventory(q.getReward());
             iPlayer.inventory.getStackInSlot(c).shrink(q.getWanted().stackSize);
             selectedQuestsList.get(iPlayer.getName()).remove(q);
             iPlayer.world.spawnEntity(new EntityXPOrb(iPlayer.world, iPlayer.posX, iPlayer.posY, iPlayer.posZ, q.getExpReward()));
            }
        }
        this.markDirty();
    }

    public void cancelQuest(Quest q, @NotNull EntityPlayer player) {
        selectedQuestsList.get(player.getName()).remove(q);
        this.markDirty();
    }

    public void addQuest(Quest q, @NotNull EntityGuildMaster guildMaster, @NotNull EntityPlayer player) {
        if(ModEventFactory.onPlayerSelectedQuest(q, guildMaster, player)) {
            selectedQuestsList.get(player.getName()).add(q);
            this.markDirty();
        }
    }

    @Contract(pure = true)
    public static Map<String, QuestPlayerSelectedList> getSQL() {
        return selectedQuestsList;
    }

    public static int currentItemsInInventory(Item item, InventoryPlayer iPlayer) {
        int i = 0;
        for(int c = 0; c < iPlayer.getSizeInventory(); c++) {
            if(iPlayer.getStackInSlot(c).item == item) {
                i += iPlayer.getStackInSlot(c).stackSize;
            }
        }
        return i;
    }

    public static void increaseTotalAmountForPlayer(EntityPlayer player, Class oClass) {
        System.out.println("checked kill");
        for(Quest q : selectedQuestsList.get(player.getName())) {
            if(q.getType()) {
                if(q.getTarget() == oClass) {
                    q.incCurrentAmount(1);
                    System.out.println("kill true");
                }
            }
        }
    }

    @Override
    public boolean isDirty()
    {
        return true;
    }

    @NotNull
    public static Boolean checkCondition(Quest q, @NotNull EntityPlayer player) {
        //for(int c = 0; c < player.inventory.getSizeInventory(); c++) {
            //if(player.inventory.getStackInSlot(c).item == q.getWanted().item && player.inventory.getStackInSlot(c).stackSize >= q.getWanted().stackSize) {
                return true;
            /*}
        }
        return false;*/
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt) {
        /*----------------------------------------*/

        System.out.println("read_1_start");

        NBTTagList nbttag = nbt.getTagList("playerSelectedQuestList", 10);
        tickCounter = nbt.getInteger("tick");

        for(int i = 0; i < nbttag.tagCount(); i++) {
            NBTTagList list = (NBTTagList) nbttag.get(i);

            NBTTagCompound name = list.getCompoundTagAt(0);
            QuestPlayerSelectedList qList = new QuestPlayerSelectedList(list.getCompoundTagAt(1));

            selectedQuestsList.put(name.getString("playerName"), qList);
        }

        System.out.println("read_1_end");
    }

    @NotNull
    @Override
    public NBTTagCompound writeToNBT(@NotNull NBTTagCompound compound) {
        /*----------------------------------------*/
        System.out.println("write_1_start");

        NBTTagList tagList = new NBTTagList();

        for(Map.Entry<String, QuestPlayerSelectedList> e : selectedQuestsList.entrySet()) {
            QuestPlayerSelectedList qList = e.getValue();
            String pName = e.getKey();
            NBTTagList list = new NBTTagList();
            NBTTagCompound name = new NBTTagCompound();

            name.setString("playerName", pName);
            list.appendTag(name);
            list.appendTag(qList.getQuestsAsTags());
            tagList.appendTag(list);
        }

        compound.setInteger("tick", tickCounter);
        compound.setTag("playerSelectedQuestList", tagList);

        System.out.println("write_1_end");
        return compound;
    }
}