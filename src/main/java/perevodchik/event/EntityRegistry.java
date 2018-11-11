package perevodchik.event;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import perevodchik.Main;
import perevodchik.client.renderer.entity.RenderEntitySmallHerobrine;
import perevodchik.entity.EntitySmallHerobrine;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EntityRegistry {

    @SideOnly(Side.CLIENT)//Только клиент
    public static void initModels() {
        /*Регистрируем рендер, 1 параметр = класс моба, 2 параметр = НАШ РЕНДЕР ФЭКТОРИ */
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallHerobrine.class, RenderEntitySmallHerobrine.FACTORY);
    }

    private static int ID = 0;//Для айди

    public static EntityEntry SMALL_HEROBRINE = EntityEntryBuilder
            .create()//Создаём новый EntityEntry
            .entity(EntitySmallHerobrine.class)//Какой моб в EntityEntry
            .name("Small Herobrine")//Имя
            .id("small_herobrine", ID++)//Айди и имя регистрации
            .egg(0xff4040, 0xd891ef)//Цвет яйца, первое значени фон, второе "точки"
            .tracker(160, 2, false)//Трекер моба
            .build();//Устанавливаем параметры

    @SubscribeEvent
    public static void registryEntity(RegistryEvent.Register<EntityEntry> event) {
        /*Новая регистрация от форджа*/
        /*event.getRegistry().registerAll(
                SMALL_HEROBRINE
        );*/
        event.getRegistry().register(SMALL_HEROBRINE);
    }
}