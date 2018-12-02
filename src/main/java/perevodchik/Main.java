package perevodchik;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import perevodchik.event.EntityRegistry;
import perevodchik.event.ProfessionsEvent;
import perevodchik.proxy.CommonProxy;
import perevodchik.util.registers.SmellRegister;
import perevodchik.village.VillageMapGenMod;
import perevodchik.village.VillageMod;
import perevodchik.village.text.TestVillageCreationHandler;
import perevodchik.world.OreGen;
import perevodchik.world.StructureGenerate;

@Mod(modid = Main.MODID,
    name = Main.NAME,
    version = Main.VERSION)
public class Main {
    public static final String MODID = "rpg-c";
    static final String NAME = "RPG Craft";
    static final String VERSION = "0.1";

    public static final VillagerRegistry.IVillageCreationHandler TEST_VILLAGE_HANDLER = new TestVillageCreationHandler();

    @Mod.Instance("rpg-c")
    public static Main instance;
    
    @SidedProxy(
            clientSide = "perevodchik.proxy.ClientProxy",
            serverSide = "perevodchik.proxy.CommonProxy"
    )
    private static CommonProxy proxy;

    public static void main( final String[] args ){}

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        GameRegistry.registerWorldGenerator(new OreGen(),3);
        GameRegistry.registerWorldGenerator(new StructureGenerate(), 3);
        EntityRegistry.initModels();
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        SmellRegister.init();
        ProfessionsEvent.associateCareersAndTrades();

        /*MapGenStructureIO.registerStructure(MapGenTestVillage.Start.class, MODID+":test_village");
        MapGenStructureIO.registerStructureComponent(TestVillageHouse.class, MODID+":test_village_house");
        VillagerRegistry.instance().registerVillageCreationHandler(TEST_VILLAGE_HANDLER);*/

        VillageMod.registerVillagePieces();
        MapGenStructureIO.registerStructure(VillageMapGenMod.Start.class, "mod_village");

        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit(e);
    }

}