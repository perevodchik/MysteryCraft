package perevodchik.event.modEvent;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import perevodchik.capability.quest.Quest;
import perevodchik.entity.villager.EntityGuildMaster;

/**
 * Event fired when player has take quest from villager
 */
public class QuestEvent extends Event {
    private final EntityGuildMaster entity;

    public QuestEvent(EntityGuildMaster entity) {
        this.entity = entity;
    }

    public EntityGuildMaster getEntity() {
        return this.entity;
    }

    /*==========================================================================*/

    /**
     * Called when quest constructing, before replenish villager quest list
     */
    public static class questConstructing extends QuestEvent {
        private final Quest quest;

        public questConstructing(Quest quest, EntityGuildMaster entity) {
            super(entity);
            this.quest = quest;
        }

        public Quest getQuest() {
            return this.quest;
        }

    }

    /**
     * Called when villager die
     */
    public static class villagerDie extends QuestEvent {

        public villagerDie(EntityGuildMaster entity) {
            super(entity);
        }

    }

    /**
     * Called when player take quest from villager
     */
    @Cancelable
    public static class playerSelectQuestEvent extends QuestEvent {
        private final EntityPlayer player;
        private final Quest quest;

        public playerSelectQuestEvent(Quest quest, EntityGuildMaster entity, EntityPlayer player) {
            super(entity);
            this.quest = quest;
            this.player = player;
        }

        public EntityPlayer getPlayer() {
            return this.player;
        }

        public Quest getQuest() {
            return this.quest;
        }

    }

    /**
     * Called when player canceled quest
     */
    @Cancelable
    public static class playerCanceledQuestEvent extends QuestEvent {
        private final EntityPlayer player;
        private final Quest quest;

        public playerCanceledQuestEvent(Quest quest, EntityGuildMaster entity, EntityPlayer player) {
            super(entity);
            this.quest = quest;
            this.player = player;
        }

        public EntityPlayer getPlayer() {
            return this.player;
        }

        public Quest getQuest() {
            return this.quest;
        }
    }

    /**
     * Called when player performs quest
     */
    @Cancelable
    public static class playerDoneQuestEvent extends QuestEvent {
        private final EntityPlayer player;
        private final Quest quest;

        public playerDoneQuestEvent(Quest quest, EntityGuildMaster entity, EntityPlayer player) {
            super(entity);
            this.quest = quest;
            this.player = player;
        }

        public EntityPlayer getPlayer() {
            return this.player;
        }

        public Quest getQuest() {
            return this.quest;
        }
    }

}
