package perevodchik.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import perevodchik.event.EntityRegistry;
import perevodchik.util.registers.ItemRegistry;

public class ClientProxy extends CommonProxy{

    @Override
    public void preInit(FMLPreInitializationEvent e){
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e){
        ItemRegistry.registerRender();
        super.init(e);
        EntityRegistry.initModels();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e){
        super.postInit(e);
    }
}
