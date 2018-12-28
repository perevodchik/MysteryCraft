package perevodchik.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import perevodchik.event.eventHandler.EntityEvent;
import perevodchik.event.eventHandler.EntityRegistryEvent;
import perevodchik.event.eventHandler.GenerateEvent;
import perevodchik.event.eventHandler.EventListener;
import perevodchik.util.registers.CapabilityRegister;
import perevodchik.util.registers.CraftingRegister;
import perevodchik.util.registers.DimensionRegister;
import perevodchik.util.registers.ItemRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e){
        ItemRegistry.register();
        MinecraftForge.EVENT_BUS.register(new EntityRegistryEvent());
        MinecraftForge.EVENT_BUS.register(new EventListener());
        MinecraftForge.EVENT_BUS.register(new EntityEvent());
        MinecraftForge.TERRAIN_GEN_BUS.register(new GenerateEvent());

        DimensionRegister.initModDimension();

        CapabilityRegister.register();
        //GameRegistry.registerWorldGenerator(new SkyIslandGenerate(), 0);
    }

    public void init(FMLInitializationEvent e){
        CraftingRegister.register();
    }

    public void postInit(FMLPostInitializationEvent e){

    }
}
