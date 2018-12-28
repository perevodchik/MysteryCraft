package perevodchik.capability.quest;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import perevodchik.quest.QuestData;

import java.util.Random;
import java.util.UUID;

public class Quest implements IQuest{
    private int level;
    private UUID id;
    private int expReward;
    private ItemStack reward;
    private ItemStack wanted;
    private Class target;
    private int totalTargetAmount;
    private int currentTargetAmount = 0;
    private Boolean type;
    private Random r = new Random();

    public Quest() { this.init(); }

    public Quest(NBTTagCompound compound) { this.readFromTag(compound); }

    private void init() {
        this.type = r.nextBoolean();
        this.id = UUID.randomUUID();
        System.out.println(this.id);

        this.level = r.nextInt(5) + 1;
        this.expReward = r.nextInt(30) * this.level;
        this.reward = new ItemStack(QuestData.itemList[r.nextInt(QuestData.itemList.length - 1)], this.level * r.nextInt(this.level) + 1);

        if(type) {
            this.target = QuestData.classList[r.nextInt(QuestData.classList.length-1)];
            this.totalTargetAmount = (r.nextInt(3) + 1) * this.level;
        } else {
            this.wanted = new ItemStack(QuestData.itemList[r.nextInt(QuestData.itemList.length - 1)], this.level * r.nextInt(this.level) + 1);
        }
    }

    /*________________________________________________*/

    public void readFromTag(NBTTagCompound compound) {

        this.id = compound.getUniqueId("id");
        this.type = compound.getBoolean("type");
        this.level = compound.getInteger("lvl");
        this.expReward = compound.getInteger("exp");
        this.reward = new ItemStack(compound.getCompoundTag("reward"));

        if(type) {
            this.totalTargetAmount = compound.getInteger("totalTargetAm");
            this.currentTargetAmount = compound.getInteger("currentTargetAm");
            String t = compound.getString("target");

            switch (t) {
                case "zombie":
                    this.target = EntityZombie.class;
                    break;
                case "skeleton":
                    this.target = EntitySkeleton.class;
                    break;
                case "creeper":
                    this.target = EntityCreeper.class;
                    break;
                case "wither_skeleton":
                    this.target = EntityWitherSkeleton.class;
                    break;
                case "dragon":
                    this.target = EntityDragon.class;
                    break;
            }

        } else {
            this.wanted = new ItemStack(compound.getCompoundTag("wanted"));
        }
    }

    public NBTTagCompound writeToTag() {
        NBTTagCompound compound = new NBTTagCompound();

        compound.setUniqueId("id", id);
        compound.setBoolean("type", type);
        compound.setInteger("lvl", level);
        compound.setInteger("exp", expReward);
        compound.setTag("reward", reward.writeToNBT(new NBTTagCompound()));

        if(type) {
            compound.setInteger("totalTargetAm", totalTargetAmount);
            compound.setInteger("currentTargetAm", currentTargetAmount);
            String t = "";

            if(target.equals(EntityZombie.class)) {
                t = "zombie";
            } else if(target.equals(EntitySkeleton.class)) {
                t = "skeleton";
            } else if(target.equals(EntityCreeper.class)) {
                t = "creeper";
            } else if(target.equals(EntityWitherSkeleton.class)) {
                t = "wither_skeleton";
            }else if(target.equals(EntityDragon.class)) {
                t = "dragon";
            }
            compound.setString("target", t);

        } else {
            compound.setTag("wanted", wanted.writeToNBT(new NBTTagCompound()));
        }

        return compound;
    }

    /*________________________________________________*/

    public void incCurrentAmount(int am) {
        this.currentTargetAmount += am;
    }

    public Boolean getType() {
        return type;
    }

    @Override
    public int getLevelQuest() {
        return level;
    }

    @Override
    public void setLevelQuest(int lvl) {
        this.level = lvl;
    }

    @Override
    public ItemStack getReward() {
        return reward;
    }

    @Override
    public void setReward(ItemStack item) {
        this.reward = item;
    }

    @Override
    public ItemStack getWanted() {
        return wanted;
    }

    @Override
    public void setWanted(ItemStack item) {
        this.wanted = item;
    }

    @Override
    public Class getTarget() {
        return target;
    }

    @Override
    public void setTarget(Class oClass) {
        this.target = oClass;
    }

    @Override
    public int getTargetAmount() {
        return totalTargetAmount;
    }

    @Override
    public void setTargetAmount(int c) {
        this.totalTargetAmount = c;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID uuid) {
        this.id = uuid;
    }

    @Override
    public int getExpReward() {
        return expReward;
    }

    @Override
    public void setExpReward(int exp) {
        this.expReward = exp;
    }

    @Override
    public int getCurrentTargetAmount() {
        return currentTargetAmount;
    }

    @Override
    public void setCurrentTargetAmount(int c) {
        this.currentTargetAmount = c;
    }
}
