package perevodchik.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import perevodchik.event.EventListener;
import perevodchik.util.RPGCraftRegistry;
import perevodchik.util.CraftingRegister;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e){
        RPGCraftRegistry.register();
    }
    public void init(FMLInitializationEvent e){
        MinecraftForge.EVENT_BUS.register(new EventListener());
        CraftingRegister.register();
    }
    public void postInit(FMLPostInitializationEvent e){

    }
}
