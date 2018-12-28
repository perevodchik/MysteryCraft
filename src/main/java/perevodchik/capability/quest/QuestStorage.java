package perevodchik.capability.quest;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class QuestStorage implements Capability.IStorage<IQuestContainer> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IQuestContainer> capability, IQuestContainer instance, EnumFacing side) {
        NBTTagList list = new NBTTagList();

        for(Quest q : instance.getList()) {
            list.appendTag(q.writeToTag());
        }

        return list;
    }

    @Override
    public void readNBT(Capability<IQuestContainer> capability, IQuestContainer instance, EnumFacing side, NBTBase nbt) {
        NBTTagList list = (NBTTagList) nbt;

        for(int c = 0; c < list.tagCount(); c++) {
            instance.addQuest(new Quest((NBTTagCompound) list.get(c)));
        }

    }
}
