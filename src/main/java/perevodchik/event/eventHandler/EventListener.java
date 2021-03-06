package perevodchik.event.eventHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import perevodchik.Main;
import perevodchik.capability.cold.ColdProvider;
import perevodchik.capability.cold.ICold;
import perevodchik.client.gui.PlayerStatsGui;
import perevodchik.enums.ObjList;


@Mod.EventBusSubscriber(modid = Main.MODID)
public class EventListener {

    @SubscribeEvent
    public void entityHurts(LivingHurtEvent event) {
        EntityLivingBase living = event.getEntityLiving();

        //FreiaBow teleport
        if (event.getSource().damageType.equals("arrow") && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

            /*if (player.getHeldItemMainhand().getItem() == ObjList.FreiaBow|| player.getHeldItemOffhand().getItem() == ObjList.FreiaBow) {

                double sourceX = player.posX;
                double sourceY = player.posY;
                double sourceZ = player.posZ;
                float sourceYaw = player.rotationYaw;
                float sourcePitch = player.rotationPitch;

                player.rotationYaw = living.rotationYaw;
                player.rotationPitch = living.rotationPitch;
                player.setPositionAndUpdate(living.posX, living.posY, living.posZ);

                living.setPositionAndRotation(sourceX, sourceY, sourceZ, sourceYaw, sourcePitch);
            }*/
        }
    }

    @SubscribeEvent
    public void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        EntityPlayer player = event.getEntityPlayer();
        /* thunder from right click with Mjolnir */
        if (player.getHeldItemMainhand().getItem() == ObjList.Mjolnir || player.getHeldItemOffhand().getItem() == ObjList.Mjolnir) {
            BlockPos pPos = player.getPosition();
            EntityLightningBolt lite = new EntityLightningBolt(event.getWorld(), pPos.getX(), pPos.getY(), pPos.getZ(), false);
            event.getWorld().spawnEntity(lite);
        }

        if (player.getHeldItemMainhand().getItem() == ObjList.MagickStick || player.getHeldItemOffhand().getItem() == ObjList.MagickStick) {
            ICold cold = event.getEntityPlayer().getCapability(ColdProvider.cold, null);
            System.out.println("cold level " + cold.getCold());
        }
    }

    @SubscribeEvent
    public void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();

        if(!event.getWorld().isRemote) {
            if(player.getHeldItemOffhand().getItem() == ObjList.MagickStick || player.getHeldItemMainhand().getItem() == ObjList.MagickStick) {
                ICold cold = event.getEntityPlayer().getCapability(ColdProvider.cold, null);
                cold.incCold();
                System.out.println("cold level " + cold.getCold());
            }
        }

        if(!event.getWorld().isRemote) {
            if(player.getHeldItemOffhand().getItem() == Items.EMERALD || player.getHeldItemMainhand().getItem() == Items.EMERALD) {
                FMLCommonHandler.instance().showGuiScreen(new PlayerStatsGui(event.getEntityPlayer()));
            }
        }
    }

    @SubscribeEvent
    public void entityStruckByLightning(EntityStruckByLightningEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if(player.getHeldItemMainhand().getItem() == ObjList.Mjolnir || player.getHeldItemOffhand().getItem() == ObjList.Mjolnir) {
                event.setCanceled(true);
            }
        }
    }

}