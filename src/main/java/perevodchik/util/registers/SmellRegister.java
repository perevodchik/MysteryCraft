package perevodchik.util.registers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import perevodchik.enums.ObjList;

public class SmellRegister {

    /*
    Не забути:

    Переплавка руд = {40%}
    Переплавка старих предметів = {~~70% не точно}
    Переплавка нових предметів = {20%}
     */
    public static void init() {
        // ore
        GameRegistry.addSmelting(ObjList.ElectrumOre, new ItemStack(ObjList.ElectrumIngot, 2), 4.0F);
        GameRegistry.addSmelting(ObjList.StarOre, new ItemStack(ObjList.StarIngot, 2), 2.0F);
        GameRegistry.addSmelting(ObjList.MyfrilOre, new ItemStack(ObjList.MyfrilIngot, 2), 4.0F);
        GameRegistry.addSmelting(ObjList.OryhalkOre, new ItemStack(ObjList.OryhalkIngot, 2), 4.0F);
        GameRegistry.addSmelting(ObjList.SunSword, new ItemStack(ObjList.SunIngot, 5), 22.0F);

        // new items
        //GameRegistry.addSmelting(ItemRegistry.ReaperScythe, new ItemStack(ItemRegistry.SoulSteel, 2), 2.0F);
        GameRegistry.addSmelting(ObjList.Mjolnir, new ItemStack(ObjList.StarIngot, 2), 22.0F);
        GameRegistry.addSmelting(ObjList.StoneFish, new ItemStack(Items.IRON_INGOT, 2), 4.0F);
        GameRegistry.addSmelting(ObjList.SunSword, new ItemStack(ObjList.SunIngot, 5), 22.0F);

        // old items=
        /*GameRegistry.addSmelting(Items.IRON_SWORD, new ItemStack(Items.IRON_AXE, 1), 2.0F);
        GameRegistry.addSmelting(Items.IRON_SHOVEL, new ItemStack(Items.IRON_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.IRON_AXE, new ItemStack(Items.IRON_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.IRON_HORSE_ARMOR, new ItemStack(Items.IRON_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.IRON_PICKAXE, new ItemStack(Items.IRON_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.IRON_DOOR, new ItemStack(Items.IRON_INGOT, 2), 3.0F);
        GameRegistry.addSmelting(Items.IRON_BOOTS, new ItemStack(Items.IRON_INGOT, 1), 2);
        GameRegistry.addSmelting(Items.IRON_CHESTPLATE, new ItemStack(Items.IRON_INGOT, 2), 3.0F);
        GameRegistry.addSmelting(Items.IRON_HELMET, new ItemStack(Items.IRON_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.IRON_LEGGINGS, new ItemStack(Items.IRON_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.IRON_HOE, new ItemStack(Items.IRON_INGOT, 1), 2.0F);

        GameRegistry.addSmelting(Items.GOLDEN_AXE, new ItemStack(Items.GOLD_NUGGET, 2), 3.0F);
        GameRegistry.addSmelting(Items.GOLDEN_HOE, new ItemStack(Items.GOLD_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.GOLDEN_SWORD, new ItemStack(Items.GOLD_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.GOLDEN_PICKAXE, new ItemStack(Items.GOLD_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.GOLDEN_SHOVEL, new ItemStack(Items.GOLD_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.GOLDEN_HELMET, new ItemStack(Items.GOLD_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.GOLDEN_CHESTPLATE, new ItemStack(Items.GOLD_INGOT, 2), 3.0F);
        GameRegistry.addSmelting(Items.GOLDEN_LEGGINGS, new ItemStack(Items.GOLD_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.GOLDEN_BOOTS, new ItemStack(Items.GOLD_INGOT, 1), 2.0F);
        GameRegistry.addSmelting(Items.GOLDEN_HORSE_ARMOR, new ItemStack(Items.GOLD_INGOT, 2), 3.0F);

        GameRegistry.addSmelting(Items.CHAINMAIL_HELMET, new ItemStack(Items.IRON_INGOT, 2), 5.0F);
        GameRegistry.addSmelting(Items.CHAINMAIL_CHESTPLATE, new ItemStack(Items.IRON_INGOT, 3), 10.0F);
        GameRegistry.addSmelting(Items.CHAINMAIL_LEGGINGS, new ItemStack(Items.IRON_INGOT, 2), 5.0F);
        GameRegistry.addSmelting(Items.CHAINMAIL_BOOTS, new ItemStack(Items.IRON_INGOT, 2), 5.0F);*/
    }
}
