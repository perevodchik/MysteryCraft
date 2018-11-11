package perevodchik.enums;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import perevodchik.blocks.*;
import perevodchik.item.other.*;
import perevodchik.item.tool.MagickStick;
import perevodchik.item.unique.*;

import static perevodchik.enums.CreativeTabsList.*;

public class ObjList {
    public static Block MyfrilOre = new MyfrilOre(Material.ROCK,"Myfril ore", MTabBlocks);
    public static Block StarOre = new StarOre(Material.ROCK,"Star ore", MTabBlocks);
    public static Block OryhalkOre = new OryhalkOre(Material.ROCK,"Oryhalk ore", MTabBlocks);
    public static Block ElectrumOre = new ElectrumOre(Material.ROCK,"Electrum ore", MTabBlocks);

    public static Block MyfrilBlock = new MyfrilBlock(Material.ROCK,"Myfril block", MTabBlocks);
    public static Block StarBlock = new StarBlock(Material.ROCK,"Star block", MTabBlocks);
    public static Block OryhalkBlock = new OryhalkBlock(Material.ROCK,"Oryhalk block", MTabBlocks);
    public static Block ElectrumBlock = new ElectrumBlock(Material.ROCK,"Electrum block", MTabBlocks);
    public static Block MagicWood = new MagicWood(Material.ROCK,"Magic wood", MTabBlocks);

    public static perevodchik.item.other.SunIngot SunIngot = new SunIngot("Sun ignot", MTabItems);
    public static perevodchik.item.other.ElectrumIngot ElectrumIngot = new ElectrumIngot("Electrum ignot", MTabItems);
    public static perevodchik.item.other.MyfrilIngot MyfrilIngot = new MyfrilIngot("Myfril ignot", MTabItems);
    public static perevodchik.item.other.OryhalkIngot OryhalkIngot = new OryhalkIngot("Oryhalk ignot", MTabItems);
    public static perevodchik.item.other.StarIngot StarIngot = new StarIngot("Star ignot", MTabItems);
    public static ElectrumStick ElectrumStick = new ElectrumStick("Electrum stick", MTabItems);

    public static perevodchik.item.unique.SunSword SunSword = new SunSword(MaterialList.SUN_IRON,"Sun Sword", MTabEquip);
    public static FreiasBow FreiaBow = new FreiasBow("Freyias bow", MTabEquip);
    //private static Rapier Rapier = new Rapier(Item.ToolMaterial.IRON, "Rapier", MTabEquip);
    public static perevodchik.item.unique.ReaperScythe ReaperScythe = new ReaperScythe(MaterialList.SOUL_STEEL, "Reaper Scythe", MTabEquip);
    public static perevodchik.item.unique.StoneFish StoneFish = new StoneFish(Item.ToolMaterial.IRON,"Stone Fish", MTabEquip);
    public static perevodchik.item.unique.Mjolnir Mjolnir = new Mjolnir(MaterialList.STAR, "Mjolnir", MTabEquip);

    public static perevodchik.item.tool.MagickStick MagickStick = new MagickStick("Magick stick", MTabTools);

    public static IkarusWing IkarusWing = new IkarusWing("Ikarus wing", MTabEquip);

    public static GlassWatch GlassWatch = new GlassWatch("Glass Watch", MTabEquip);
}
