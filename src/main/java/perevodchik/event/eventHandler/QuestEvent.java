package perevodchik.event.eventHandler;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.Main;
import perevodchik.capability.quest.QuestProvider;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class QuestEvent {

    @SubscribeEvent
    public static void playerSelectQuestEvent(perevodchik.event.modEvent.QuestEvent.playerSelectQuestEvent event) {
        if(event.getPlayer().getCapability(QuestProvider.quest, null).canSelectMore()) event.setCanceled(false);
    }

    @SubscribeEvent
    public static void playerCancelQuestEvent(perevodchik.event.modEvent.QuestEvent.playerCanceledQuestEvent event) {
        //QuestManager.selectedQuestsList.get(event.getPlayer().getUniqueID()).remove(event.getQuest());
    }

    @SubscribeEvent
    public static void playerDoneQuestEvent(perevodchik.event.modEvent.QuestEvent.playerDoneQuestEvent event) {
    }

    @SubscribeEvent
    public static void villagerDieEvent(perevodchik.event.modEvent.QuestEvent.villagerDie event) {
    }

    @SubscribeEvent
    public static void questConstructingEvent(perevodchik.event.modEvent.QuestEvent.questConstructing event) {

    }
}
