package perevodchik.event.eventHandler;

import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import perevodchik.Main;
import perevodchik.client.model.ModelDeer;
import perevodchik.client.renderer.entity.RenderVillagerDeffer;
import perevodchik.client.renderer.entity.boss.RenderLivingZombie;
import perevodchik.client.renderer.entity.passive.RenderDeer;
import perevodchik.entity.EntityDeer;
import perevodchik.entity.EntityVillagerDeffer;
import perevodchik.entity.boss.EntityLivingZombie;
import perevodchik.entity.villager.EntityGuildMaster;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EntityRegistryEvent {
    private static int entityID = 0;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        /*Регистрируем рендер, 1 параметр = класс моба, 2 параметр = НАШ РЕНДЕР ФЭКТОРИ */
        RenderingRegistry.registerEntityRenderingHandler(EntityDeer.class, m -> new RenderDeer(m, new ModelDeer(), 0.7F));
        //RenderingRegistry.registerEntityRenderingHandler(EntityModVillager.class, RenderVillager::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGuildMaster.class, RenderVillager::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityVillagerDeffer.class, RenderVillagerDeffer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLivingZombie.class, RenderLivingZombie::new);
    }

    @SubscribeEvent
    public static void registryEntity(RegistryEvent.Register<EntityEntry> event) {
        registerEntity(EntityDeer.class, "deer", 120, 5, false, 0xff4040, 0xd891ef);
       // registerEntity(EntityModVillager.class, "mod_villager", 120, 5, false, 0xff1010, 0xd123ef);
        registerEntity(EntityGuildMaster.class, "guild_master", 120, 5, false, 0xff9999, 0xd11111);
        registerEntity(EntityVillagerDeffer.class, "villager_deffer", 120, 5, false, 0xff1111, 0xd9999);
        registerEntity(EntityLivingZombie.class, "living_zombie", 120, 5, false, 0xff2020, 0xd9999);
    }

    private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggColor1, int eggColor2) {
        net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID + ":textures/entity/" + entityName + ".png"), entityClass, entityName, entityID++, Main.instance, trackingRange, updateFrequency, sendsVelocityUpdates, eggColor1, eggColor2);
    }
}