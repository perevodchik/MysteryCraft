package perevodchik.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import perevodchik.Main;
import perevodchik.client.gui.GuiHandler;
import perevodchik.event.eventHandler.EntityRegistryEvent;
import perevodchik.util.registers.ItemRegistry;

public class ClientProxy extends CommonProxy{

    @Override
    public void preInit(FMLPreInitializationEvent e){
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e){
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
        ItemRegistry.registerRender();
        super.init(e);
        EntityRegistryEvent.initModels();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e){
        super.postInit(e);
    }
}
