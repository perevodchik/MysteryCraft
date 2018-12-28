package perevodchik.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import perevodchik.capability.quest.Quest;
import perevodchik.entity.villager.EntityGuildMaster;
import perevodchik.event.modEvent.QuestEvent;

public class ModEventFactory {

    public static boolean onPlayerSelectedQuest(Quest q, EntityGuildMaster GuildMaster, EntityPlayer player) {
        return MinecraftForge.EVENT_BUS.post(new QuestEvent.playerSelectQuestEvent(q, GuildMaster, player));
    }

    public static boolean onQuestConstructing(Quest q, EntityGuildMaster GuildMaster) {
        return MinecraftForge.EVENT_BUS.post(new QuestEvent.questConstructing(q, GuildMaster));
    }

    public static boolean onPlayerDoneQuest(Quest q, EntityGuildMaster GuildMaster, EntityPlayer player) {
        return MinecraftForge.EVENT_BUS.post(new QuestEvent.playerDoneQuestEvent(q, GuildMaster, player));
    }

    public static boolean onPlayerCanceledQuest(Quest q, EntityGuildMaster GuildMaster, EntityPlayer player) {
        return MinecraftForge.EVENT_BUS.post(new QuestEvent.playerCanceledQuestEvent(q, GuildMaster, player));
    }
}
