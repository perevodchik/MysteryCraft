package perevodchik.event;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.Main;
import perevodchik.entity.EntityVillagerDeffer;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EntityEvent {

    @SubscribeEvent
    public static void entityInteractEvent(PlayerInteractEvent.EntityInteract event) {

        if(event.getTarget() instanceof EntityVillager) {
            EntityVillager v = (EntityVillager) event.getTarget();
            BlockPos b = v.getPos();
            AxisAlignedBB abb = new AxisAlignedBB(b.getX()-50, b.getY()-50, b.getZ()-50, b.getX()+50, b.getY()+50, b.getZ()+50);

            if(event.getWorld().findNearestEntityWithinAABB(EntityVillagerDeffer.class, abb, v) != null) {
                EntityPlayerSP p = (EntityPlayerSP) event.getEntityPlayer();
                p.sendChatMessage("Ми не зможемо торгувати, поки страшний житель десь поряд... Позбудься його спершу, а потім повертайся");
                event.setCanceled(true);
            }

        }

    }

}
