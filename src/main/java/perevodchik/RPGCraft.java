package perevodchik;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import perevodchik.proxy.CommonProxy;
import perevodchik.util.Smell;

@Mod(modid = RPGCraft.MODID,
    name = RPGCraft.NAME,
    version = RPGCraft.VERSION)
public class RPGCraft {
    public static final String MODID = "rpg-c";
    static final String NAME = "RPG Craft";
    static final String VERSION = "0.1";
    
    @SidedProxy(
            clientSide = "perevodchik.proxy.ClientProxy",
            serverSide = "perevodchik.proxy.CommonProxy"
    )
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        System.out.println("\\u001B[32m" + "mod preInit" + "\u001B[0m");
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        System.out.println("\\u001B[32m" + "mod init" + "\u001B[0m");
        Smell.init();
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        System.out.println("\\u001B[32m" + "mod postInit" + "\u001B[0m");
        proxy.postInit(e);
    }

}