package perevodchik.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.RPGCraft;
import perevodchik.util.RPGCraftRegistry;


@Mod.EventBusSubscriber(modid = RPGCraft.MODID)
public class EventListener {

    @SubscribeEvent
    public void entityHurts(LivingHurtEvent event) {
        EntityLivingBase living = event.getEntityLiving();

        if(event.getSource().getTrueSource() instanceof  EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

        }

        //FreiaBow teleport
        if (event.getSource().damageType.equals("arrow") && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

            if (player.getHeldItemMainhand().getItem() == RPGCraftRegistry.FreiaBow || player.getHeldItemOffhand().getItem() == RPGCraftRegistry.FreiaBow) {

                double sourceX = player.posX;
                double sourceY = player.posY;
                double sourceZ = player.posZ;
                float sourceYaw = player.rotationYaw;
                float sourcePitch = player.rotationPitch;

                // this is the only method that will move the player properly
                player.rotationYaw = living.rotationYaw;
                player.rotationPitch = living.rotationPitch;
                player.setPositionAndUpdate(living.posX, living.posY, living.posZ);

                // monsters are easy to move
                living.setPositionAndRotation(sourceX, sourceY, sourceZ, sourceYaw, sourcePitch);
            }
        }
    }
}

