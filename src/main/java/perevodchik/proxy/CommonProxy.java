package perevodchik.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import perevodchik.event.EntityRegistry;
import perevodchik.event.EventListener;
import perevodchik.util.registers.ItemRegistry;
import perevodchik.util.registers.CraftingRegister;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e){
        ItemRegistry.register();
        MinecraftForge.EVENT_BUS.register(new EntityRegistry());
    }
    public void init(FMLInitializationEvent e){
        MinecraftForge.EVENT_BUS.register(new EventListener());
        CraftingRegister.register();
    }
    public void postInit(FMLPostInitializationEvent e){

    }
}
