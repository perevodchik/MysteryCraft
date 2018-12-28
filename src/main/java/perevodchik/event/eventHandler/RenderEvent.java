package perevodchik.event.eventHandler;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.Main;
import perevodchik.client.gui.ColdGui;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RenderEvent {

    @SubscribeEvent
    public static void renderGameOverlayPreEvent(RenderGameOverlayEvent event) {
        if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            ColdGui cd = new ColdGui();
            cd.draw();
        }
        //System.out.println(event.getResolution().getScaledHeight());
    }

}
