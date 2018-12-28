package perevodchik.quest;

import net.minecraft.item.ItemStack;

public interface IQuest {

    void init();

    ItemStack getReward();

    int getExpReward();

    int getLevelQuest();
}
