package perevodchik.entity.villager;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import perevodchik.capability.quest.Quest;
import perevodchik.quest.QuestList;

import java.util.Random;

public class EntityGuildMaster extends EntityVillager {
    private QuestList questList;

    public EntityGuildMaster(World worldIn) {
        super(worldIn);
    }

    public QuestList getQuestList() {
        return this.questList;
    }

    public void setQuestList(QuestList questList) {
        this.questList = questList;
    }

    public void initQuestList() {
        Random r = new Random();
        if(this.questList == null) { this.questList = new QuestList(); }
        for(int i = 0; i < 5; i++) {
            Quest q = new Quest();
            this.questList.add(q);
        }
    }

    protected boolean canDespawn()
    {
        return false;
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        //compound.setUniqueId("uId", this.getUniqueID());


        if (this.questList != null)
        {
            compound.setTag("QuestList", this.questList.getQuestsAsTags());
        }
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        //this.setUniqueId(Objects.requireNonNull(compound.getUniqueId("uId")));

        if (compound.hasKey("QuestList", 10))
        {
            NBTTagCompound nbttagcompound = compound.getCompoundTag("QuestList");
            this.questList = new QuestList(nbttagcompound);
        }
        this.setCanPickUpLoot(false);
    }
}
