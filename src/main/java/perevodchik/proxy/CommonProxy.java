package perevodchik.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import perevodchik.event.EntityEvent;
import perevodchik.event.EntityRegistry;
import perevodchik.event.EventGenerate;
import perevodchik.event.EventListener;
import perevodchik.util.registers.CraftingRegister;
import perevodchik.util.registers.ItemRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e){
        ItemRegistry.register();
        MinecraftForge.EVENT_BUS.register(new EntityRegistry());
        MinecraftForge.EVENT_BUS.register(new EventListener());
        MinecraftForge.EVENT_BUS.register(new EntityEvent());
        MinecraftForge.TERRAIN_GEN_BUS.register(new EventGenerate());

        //GameRegistry.registerWorldGenerator(new SkyIslandGenerate(), 0);
    }
    public void init(FMLInitializationEvent e){
        CraftingRegister.register();
    }
    public void postInit(FMLPostInitializationEvent e){

    }
}
