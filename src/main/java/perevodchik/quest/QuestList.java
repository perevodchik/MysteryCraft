package perevodchik.quest;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import perevodchik.capability.quest.Quest;

import java.util.ArrayList;

public class QuestList extends ArrayList<Quest> {

    public QuestList() {}

    public QuestList(NBTTagCompound compound) { this.readQuestsFromTags(compound); }

    public void readQuestsFromTags(NBTTagCompound compound)
    {
        NBTTagList nbttaglist = compound.getTagList("Quests", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            this.add(new Quest(nbttagcompound));
        }
    }

    public NBTTagCompound getQuestsAsTags()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        NBTTagList nbttaglist = new NBTTagList();

        for (Quest quest : this) {
            nbttaglist.appendTag(quest.writeToTag());
        }

        nbttagcompound.setTag("Quests", nbttaglist);
        return nbttagcompound;
    }
}
