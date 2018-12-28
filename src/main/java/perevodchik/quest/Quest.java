package perevodchik.quest;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class Quest implements IQuest{
    // reward for complete q
    private ItemStack reward;
    // what to want for q
    private ItemStack wanted;
    // if true -- need kill entity, else need ItemStack
    public Boolean isGrindQuest = false;
    // entity for killing to complete this q
    private Class target;
    // amount entity
    private int targetAmount;
    private int totalAmount = 0;
    // level the q, vplivae na nagorodu
    private int levelQuest;
    // min rep, for player can take this q
    private int minRep;
    // rewatd exp for complite q
    private int exp;
    private Random r = new Random();
    private double id;

    public Quest(NBTTagCompound compound) {
        this.readFromTags(compound);
    }

    public Quest() {
        this.levelQuest = r.nextInt(5);
        //this.init();
        /*this.reward = new ItemStack(QuestData.itemList[r.nextInt(QuestData.itemList.length - 1)],
                this.levelQuest);
        this.wanted = new ItemStack(QuestData.itemList[r.nextInt(QuestData.itemList.length - 1)],
                this.levelQuest);*/
        this.init();
    }

    public void init() {
        this.levelQuest = r.nextInt(5) + 1;
        this.exp = r.nextInt(30) * this.levelQuest;
        this.reward = new ItemStack(QuestData.itemList[r.nextInt(QuestData.itemList.length - 1)], this.levelQuest * r.nextInt(this.levelQuest) + 1);
        this.id = new Random().nextDouble();
        this.isGrindQuest = r.nextBoolean();

        if(this.isGrindQuest) {
            this.target = QuestData.classList[r.nextInt(QuestData.classList.length-1)];
            this.targetAmount = (r.nextInt(3) + 1) * this.levelQuest;
        } else {
            this.wanted = new ItemStack(QuestData.itemList[r.nextInt(QuestData.itemList.length - 1)], this.levelQuest * r.nextInt(this.levelQuest) + 1);
        }
    }

    public void incTotalAm(int amount) {
        this.totalAmount += amount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTargetAmount() {
        return targetAmount;
    }


    public ItemStack getWanted() {
        return this.wanted;
    }

    public Class getTarget() {
        return this.target;
    }

    @Override
    public ItemStack getReward() {
        return this.reward;
    }

    @Override
    public int getExpReward() {
        return exp;
    }

    @Override
    public int getLevelQuest() {
        return levelQuest;
    }

    /*===============================================================================================*/

    public void readFromTags(NBTTagCompound tagCompound) {
        this.levelQuest = tagCompound.getInteger("lvl");
        this.reward = new ItemStack(tagCompound.getCompoundTag("reward"));
        this.id = tagCompound.getDouble("id");
        this.isGrindQuest = tagCompound.getBoolean("isGrind");
        this.exp = tagCompound.getInteger("expReward");

        if(isGrindQuest) {
            this.targetAmount = tagCompound.getInteger("targetAmount");
            this.totalAmount = tagCompound.getInteger("totalAmount");
            String t = tagCompound.getString("target");
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
            if(tagCompound.hasKey("wanted")) {
                this.wanted = new ItemStack(tagCompound.getCompoundTag("wanted"));
            }
        }
    }

    public NBTTagCompound writeToTags() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setTag("reward", this.reward.writeToNBT(new NBTTagCompound()));
        nbttagcompound.setInteger("lvl", this.levelQuest);
        nbttagcompound.setDouble("id", id);
        nbttagcompound.setBoolean("isGrind", this.isGrindQuest);
        nbttagcompound.setInteger("expReward", this.exp);

        String t = "";
        if(this.isGrindQuest) {
            nbttagcompound.setInteger("targetAmount", this.targetAmount);
            nbttagcompound.setInteger("totalAmount", this.totalAmount);

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

            nbttagcompound.setString("target", t);
        } else {
            nbttagcompound.setTag("wanted", this.wanted.writeToNBT(new NBTTagCompound()));
        }

        return nbttagcompound;
    }
}
