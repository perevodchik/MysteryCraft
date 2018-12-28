package perevodchik.event.eventHandler;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.Main;
import perevodchik.village.VillageMapGenMod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class GenerateEvent {

    @SubscribeEvent
    public void onInitMapGen(InitMapGenEvent event) {
        if (event.getType() != null && event.getType() == InitMapGenEvent.EventType.VILLAGE){
            VillageMapGenMod newGen = new VillageMapGenMod();
            event.setNewGen(newGen);
        }
    }

    @SubscribeEvent
    public void populateChunkEventPre(PopulateChunkEvent.Pre event) {
        if(event.isHasVillageGenerated()) {
            BlockPos pos = new BlockPos(event.getChunkX(), event.getWorld().getHeight(event.getChunkX(), event.getChunkZ()), event.getChunkZ());
        }
    }
}
