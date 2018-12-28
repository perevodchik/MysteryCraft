package perevodchik.event.eventHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.Main;
import perevodchik.capability.cold.ColdProvider;
import perevodchik.capability.quest.QuestProvider;
import perevodchik.capability.skill.StatProvider;
import perevodchik.capability.water.WaterProvider;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class CapabilityEvent {
    private static final ResourceLocation skill = new ResourceLocation(Main.MODID + "skill");
    private static final ResourceLocation quest = new ResourceLocation(Main.MODID + "quest");
    private static final ResourceLocation water = new ResourceLocation(Main.MODID + "water");
    private static final ResourceLocation cold = new ResourceLocation(Main.MODID + "cold");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(skill, new StatProvider());
            event.addCapability(quest, new QuestProvider());
            event.addCapability(water, new WaterProvider());
            event.addCapability(cold, new ColdProvider());
        }
    }
}
