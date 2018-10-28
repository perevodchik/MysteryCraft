package perevodchik.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import perevodchik.util.RPGCraftRegistry;
import perevodchik.util.CraftingRegister;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e){
        RPGCraftRegistry.register();
    }
    public void init(FMLInitializationEvent e){
        CraftingRegister.register();
    }
    public void postInit(FMLPostInitializationEvent e){

    }
}
