package perevodchik.capability.quest;

import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import perevodchik.util.InventoryUtil;

public class QuestUtil {

    public static void makeQuest(Quest q, @NotNull EntityPlayer iPlayer) {
        if(!q.getType()) {
            InventoryUtil.removeItemsFromInv(q.getWanted().item, q.getWanted().stackSize, iPlayer.inventory);
        }
        iPlayer.inventory.addItemStackToInventory(q.getReward());
    }

    @NotNull
    public static Boolean checkCondition(Quest q, @NotNull EntityPlayer player) {
        if(q != null) {
            if (q.getType()) {
                return q.getTargetAmount() == q.getCurrentTargetAmount();
            } else {
                return InventoryUtil.getCountItemInInv(q.getWanted().item, player.inventory) >= q.getWanted().stackSize;
            }
        }
        return false;
    }

    public static void increaseTotalAmountForPlayer(Class oClass, EntityPlayer player) {
        IQuestContainer container = player.getCapability(QuestProvider.quest, null);

        for(Quest q : container.getList()) {
            if(q.getType()) {
                if(q.getTarget() == oClass) {
                    q.setCurrentTargetAmount(q.getCurrentTargetAmount() + 1);
                }
            }
        }
    }
}
