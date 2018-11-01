package perevodchik.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smell {


    /*
    Не забути:

    Переплавка руд = {40%}
    Переплавка старих предметів = {0%}
    Переплавка нових предметів = {0%}
     */
    public static void init() {
        GameRegistry.addSmelting(RPGCraftRegistry.ElectrumOre, new ItemStack(RPGCraftRegistry.ElectrumIgnot, 2), 2.0F);
        GameRegistry.addSmelting(RPGCraftRegistry.StarOre, new ItemStack(RPGCraftRegistry.StarIgnot, 2), 2.0F);
        GameRegistry.addSmelting(RPGCraftRegistry.MyfrilOre, new ItemStack(RPGCraftRegistry.MyfrilIgnot, 2), 2.0F);
        GameRegistry.addSmelting(RPGCraftRegistry.OryhalkOre, new ItemStack(RPGCraftRegistry.OryhalkIgnot, 2), 2.0F);
        GameRegistry.addSmelting(RPGCraftRegistry.SunSword, new ItemStack(RPGCraftRegistry.SunIgnot, 5), 22.0F);
    }
}
