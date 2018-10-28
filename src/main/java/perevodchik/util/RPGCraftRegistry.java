package perevodchik.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import perevodchik.blocks.*;
import perevodchik.item.other.ElectrumStick;
import perevodchik.item.unique.*;

import static perevodchik.util.CreativeTabs.EgyptTab;
import static perevodchik.util.CreativeTabs.SkandinavTab;

public class RPGCraftRegistry {

    private static Block Neel_Sand = new NeelSand(Material.SAND,"Neel sand", EgyptTab);
    private static Block MyfrilOre = new MyfrilOre(Material.ROCK,"Myfril ore", SkandinavTab);
    private static Block MyfrilBlock = new MyfrilBlock(Material.ROCK,"Myfril block", SkandinavTab);
    private static Block StarOre = new StarOre(Material.ROCK,"Star ore", SkandinavTab);
    private static Block StarBlock = new StarBlock(Material.ROCK,"Star block", SkandinavTab);
    private static Block OryhalkOre = new OryhalkOre(Material.ROCK,"Oryhalk ore", SkandinavTab);
    private static Block OryhalkBlock = new OryhalkBlock(Material.ROCK,"Oryhalk block", SkandinavTab);
    private static Block ElectrumOre = new ElectrumOre(Material.ROCK,"Electrum ore", SkandinavTab);
    private static Block ElectrumBlock = new ElectrumBlock(Material.ROCK,"Electrum block", SkandinavTab);
    private static Block MagicWood = new MagicWood(Material.ROCK,"Magic wood", SkandinavTab);

    private static SunSword SunSword = new SunSword(Materials.SUN_IRON, SkandinavTab,"Sun Sword");
    //private static FreiasBow FreiaBow = new FreiasBow(SkandinavTab, "Freyias bow");
    //private static Rapier Rapier = new Rapier(Item.ToolMaterial.IRON, SkandinavTab, "Rapier");
    private static ReaperScythe ReaperScythe = new ReaperScythe(Materials.SOUL_STEEL, SkandinavTab, "Reaper Scythe");
    private static StoneFish StoneFish = new StoneFish(Item.ToolMaterial.STONE, SkandinavTab, "Stone Fish");
    private static Mjolnir Mjolnir = new Mjolnir(Item.ToolMaterial.STONE, SkandinavTab, "Mjolnir");

    private static IkarusWing IkarusWing = new IkarusWing(SkandinavTab, "Ikarus wing");

    private static ElectrumStick ElectrumStick = new ElectrumStick(SkandinavTab, "Electrum stick");

    public static void register() {
        setRegister();
        setRegister(Neel_Sand, "neel_sand");
        setRegister(MyfrilOre, "myfril_ore");
        setRegister(MyfrilBlock, "myfril_block");
        setRegister(StarOre, "star_ore");
        setRegister(StarBlock, "star_block");
        setRegister(OryhalkOre, "oryhalk_ore");
        setRegister(OryhalkBlock, "oryhalk_block");
        setRegister(ElectrumOre, "electrum_ore");
        setRegister(ElectrumBlock, "electrum_block");
        setRegister(MagicWood, "magic_wood");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        setRender(Neel_Sand);
        setRender(MyfrilOre);
        setRender(MyfrilBlock);
        setRender(OryhalkOre);
        setRender(OryhalkBlock);
        setRender(ElectrumOre);
        setRender(ElectrumBlock);
        setRender(StarOre);
        setRender(MagicWood);

        setRender(ElectrumStick);

        setRender(SunSword);
        setRender(ReaperScythe);
        setRender(StoneFish);
        setRender(Mjolnir);

        setRender(IkarusWing);
    }

    private static void setRegister(Block block, String RegistryName) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(RegistryName));
    }

    private static void setRegister() {
        ForgeRegistries.ITEMS.register(SunSword);
        ForgeRegistries.ITEMS.register(ReaperScythe);
        ForgeRegistries.ITEMS.register(StoneFish);
        ForgeRegistries.ITEMS.register(Mjolnir);

        ForgeRegistries.ITEMS.register(IkarusWing);

        ForgeRegistries.ITEMS.register(ElectrumStick);
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}