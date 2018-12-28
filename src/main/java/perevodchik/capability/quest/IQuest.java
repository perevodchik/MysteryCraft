package perevodchik.capability.quest;

import net.minecraft.item.ItemStack;

import java.util.UUID;

public interface IQuest {

    int getLevelQuest();
    void setLevelQuest(int lvl);

    ItemStack getReward();
    void setReward(ItemStack item);

    ItemStack getWanted();
    void setWanted(ItemStack item);

    Class getTarget();
    void setTarget(Class oClass);

    int getTargetAmount();
    void setTargetAmount(int c);

    UUID getId();
    void setId(UUID uuid);

    int getExpReward();
    void setExpReward(int exp);

    int getCurrentTargetAmount();
    void setCurrentTargetAmount(int c);
}
