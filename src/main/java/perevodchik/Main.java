package perevodchik;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import perevodchik.event.ProfessionsEvent;
import perevodchik.proxy.CommonProxy;
import perevodchik.util.registers.SmellRegister;
import perevodchik.world.OreGen;

@Mod(modid = Main.MODID,
    name = Main.NAME,
    version = Main.VERSION)
public class Main {
    public static final String MODID = "rpg-c";
    static final String NAME = "RPG Craft";
    static final String VERSION = "0.1";

    @Mod.Instance("rpg-c")
    public static Main instance;
    
    @SidedProxy(
            clientSide = "perevodchik.proxy.ClientProxy",
            serverSide = "perevodchik.proxy.CommonProxy"
    )
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        GameRegistry.registerWorldGenerator(new OreGen(),3);
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        SmellRegister.init();
        //EntityRegistry.initModels();
        ProfessionsEvent.associateCareersAndTrades();
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit(e);
    }

}