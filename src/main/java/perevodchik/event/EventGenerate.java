package perevodchik.event;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.Main;
import perevodchik.village.VillageMapGenMod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EventGenerate {

    @SubscribeEvent
    public void onInitMapGen(InitMapGenEvent event) {
        if (event.getType() != null && event.getType() == InitMapGenEvent.EventType.VILLAGE){
            VillageMapGenMod newGen = new VillageMapGenMod();
            event.setNewGen(newGen);
        }
    }
}
