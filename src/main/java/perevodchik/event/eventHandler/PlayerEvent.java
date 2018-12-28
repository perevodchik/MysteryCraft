package perevodchik.event.eventHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.Main;
import perevodchik.capability.quest.IQuestContainer;
import perevodchik.capability.quest.QuestProvider;
import perevodchik.capability.skill.IStat;
import perevodchik.capability.skill.StatProvider;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class PlayerEvent {

    @SubscribeEvent
    public static void entityUseItemFinishEvent(LivingEntityUseItemEvent.Finish event) {
        if(event.getEntity() instanceof EntityPlayer) {
            if (event.getResultStack().item instanceof ItemFood) {
                EntityPlayer player = (EntityPlayer) event.getEntity();
                //player.getCapability(WaterProvider.instance, null).decreaseWaterBalance(0.1);
            }
        }
    }

    @SubscribeEvent
    public static void playerCloneEvent(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
        EntityPlayer player = event.getOriginal();
        {
            IStat currentSkill = event.getEntityPlayer().getCapability(StatProvider.skill, null);
            IStat oldSkill = player.getCapability(StatProvider.skill, null);

            currentSkill.setBowSkill(oldSkill.getBowSkill());
            currentSkill.setScepterSkill(oldSkill.getScepterSkill());
            currentSkill.setSwordSkill(oldSkill.getSwordSkill());
            currentSkill.setShieldSkill(oldSkill.getShieldSkill());
            currentSkill.setFarmerSkill(oldSkill.getFarmerSkill());
            currentSkill.setMinerSkill(oldSkill.getMinerSkill());
        }

        {
            IQuestContainer currentQuest = event.getEntityPlayer().getCapability(QuestProvider.quest, null);
            IQuestContainer oldQuest = player.getCapability(QuestProvider.quest, null);
            currentQuest.setList(oldQuest.getList());
        }
    }
}
