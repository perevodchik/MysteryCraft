package perevodchik.event.eventHandler;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import perevodchik.Main;
import perevodchik.capability.quest.QuestUtil;
import perevodchik.capability.skill.IStat;
import perevodchik.capability.skill.StatProvider;
import perevodchik.capability.water.IWater;
import perevodchik.capability.water.WaterProvider;
import perevodchik.client.gui.GuIDialog;
import perevodchik.entity.EntityVillagerDeffer;
import perevodchik.entity.villager.EntityGuildMaster;
import perevodchik.event.modEvent.QuestEvent;
import perevodchik.item.ItemScepter;
import perevodchik.quest.QuestManager;
import perevodchik.quest.QuestPlayerSelectedList;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EntityEvent {


    @SubscribeEvent
    public static void entityInteractEvent(@NotNull PlayerInteractEvent.EntityInteract event) {
        if(event.getTarget() instanceof EntityGuildMaster) {
            EntityGuildMaster gm = (EntityGuildMaster) event.getTarget();
            if(gm.getQuestList() == null || gm.getQuestList().isEmpty()) { gm.initQuestList(); }
            event.setCanceled(true);
            FMLCommonHandler.instance().showGuiScreen(new GuIDialog(event.getEntityPlayer(), gm, event.getWorld()));
        }

        if(event.getTarget() instanceof EntityVillager) {
            EntityVillager villager = (EntityVillager) event.getTarget();
            if (event.getWorld().getVillageCollection() != null) {
                Village village = event.getWorld().getVillageCollection().getNearestVillage(event.getPos(), 1);
                if (village != null) {
                    int r = village.getVillageRadius();
                    BlockPos center = village.getCenter();
                    AxisAlignedBB aabb = new AxisAlignedBB(center.getX() - r, center.getY() - r, center.getZ() - r, center.getX() + r, center.getY() + r, center.getZ() + r);

                    if (event.getWorld().findNearestEntityWithinAABB(EntityVillagerDeffer.class, aabb, villager) != null) {
                        EntityPlayerSP p = (EntityPlayerSP) event.getEntityPlayer();
                        p.sendChatMessage("Ми не зможемо торгувати, поки страшний житель десь поряд... Позбудься його спершу, а потім повертайся");
                        event.setCanceled(true);
                    }
                } else System.out.println("village not found");
            }
        }
    }

    @SubscribeEvent
    public static void entityJoinWorldEvent(@NotNull EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) event.getEntity();
            if(!QuestManager.getSQL().containsKey(p.getName())){
                QuestManager.getSQL().put(p.getName(), new QuestPlayerSelectedList());
            }
        }
    }

    @SubscribeEvent
    public static void entityDeathEvent(@NotNull LivingDeathEvent event) {
        if(event.getEntity() instanceof EntityVillager) {
            MinecraftForge.EVENT_BUS.post(new QuestEvent.villagerDie((EntityGuildMaster) event.getEntity()));
        }

        if(event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            {
                IStat stat = player.getCapability(StatProvider.skill, null);
                stat.decSkills();
            }
        }

        if(event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

            {
                Class target = event.getEntity().getClass();
                QuestUtil.increaseTotalAmountForPlayer(target, player);
            }

            {
                IStat stats = player.getCapability(StatProvider.skill, null);
                if(player.getHeldItemMainhand().item instanceof ItemSword) {
                    stats.incSwordSkill(0.1F);
                    System.out.println("sword " + stats.getSwordSkill());
                } else if(player.getHeldItemMainhand().item instanceof ItemBow) {
                    stats.incBowSkill(0.1F);
                    System.out.println("bow " + stats.getBowSkill());
                } else if(player.getHeldItemMainhand().item instanceof ItemScepter) {
                    stats.incScepterSkill(0.1F);
                    System.out.println("scepter " + stats.getScepterSkill());
                }
            }
        }

    }

    @SubscribeEvent
    public static void entityUseItemEventFinish(LivingEntityUseItemEvent.Finish event) {
        if(event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            if(event.getResultStack().item instanceof ItemFood) {
                IWater water = player.getCapability(WaterProvider.water, null);
                water.decWaterLevel(0.05F);
                //System.out.println(player.getName() + " water level is " + water.getWaterLevel());
            }
        }
    }
}
