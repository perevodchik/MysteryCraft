package perevodchik.event;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import perevodchik.Main;
import perevodchik.enums.TradeList;

@GameRegistry.ObjectHolder(Main.MODID)
public class ProfessionsEvent {
    // instantiate VillagerProfessions
    public static VillagerRegistry.VillagerProfession mysterious_stranger;
    public static VillagerRegistry.VillagerProfession warrior ;

    // declare VillagerCareers
    public static VillagerRegistry.VillagerCareer cloud_enchanter;
    //public static VillagerRegistry.VillagerCareer fisherman;
    //public static VillagerRegistry.VillagerCareer forestman;
    public static VillagerRegistry.VillagerCareer guardian;

    @Mod.EventBusSubscriber(modid = Main.MODID)
    public static class RegistrationHandler
    {
        /**
         * Register this mod's {@link VillagerRegistry.VillagerProfession}s.
         * @param event event
         */
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<VillagerRegistry.VillagerProfession> event)
        {
            final IForgeRegistry<VillagerRegistry.VillagerProfession> registry = event.getRegistry();

            // DEBUG
            System.out.println("*********************** Registering villager professions ***********************");

            mysterious_stranger = new VillagerRegistry.VillagerProfession(Main.MODID + ":mysterious_stranger",
                    Main.MODID + ":textures/entity/mysterious_stranger.png",
                    Main.MODID + ":textures/entity/mysterious_stranger.png");

            registry.register(mysterious_stranger);
            warrior = new VillagerRegistry.VillagerProfession(
                            Main.MODID+":warrior",
                            Main.MODID+":textures/entity/mod_villager.png",
                            Main.MODID+":textures/entity/mod_villager.png");
            registry.register(warrior);
        }
    }

    /**
     * Associate careers and trades.
     */
    public static void associateCareersAndTrades()
    {
        // DEBUG
        System.out.println("*********************** Associating careers and trades to villager professions ***********************");
        cloud_enchanter = new VillagerRegistry.VillagerCareer(mysterious_stranger, "cloud_enchanter");
                //.addTrade(1, new TradeEmeraldsForEnchantedBoots());
        guardian = new VillagerRegistry.VillagerCareer(warrior, "guardian")
                .addTrade(1, new TradeList(new ItemStack(Items.CHAINMAIL_BOOTS), new ItemStack(Items.EMERALD), 20, 60));
    }

}
