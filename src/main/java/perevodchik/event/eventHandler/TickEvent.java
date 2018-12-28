package perevodchik.event.eventHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.Main;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class TickEvent {

    @SubscribeEvent
    public static void worldTickEvent(net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent event) {

    }
}
