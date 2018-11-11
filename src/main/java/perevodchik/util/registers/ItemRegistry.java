package perevodchik.util.registers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import perevodchik.enums.ObjList;

public class ItemRegistry {

    public static void register() {
        setRegister();
        setRegister(ObjList.MyfrilOre, "myfril_ore");
        setRegister(ObjList.MyfrilBlock, "myfril_block");
        setRegister(ObjList.StarOre, "star_ore");
        setRegister(ObjList.StarBlock, "star_block");
        setRegister(ObjList.OryhalkOre, "oryhalk_ore");
        setRegister(ObjList.OryhalkBlock, "oryhalk_block");
        setRegister(ObjList.ElectrumOre, "electrum_ore");
        setRegister(ObjList.ElectrumBlock, "electrum_block");
        setRegister(ObjList.MagicWood, "magic_wood");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        setRender(ObjList.MyfrilOre);
        setRender(ObjList.MyfrilBlock);
        setRender(ObjList.OryhalkOre);
        setRender(ObjList.OryhalkBlock);
        setRender(ObjList.ElectrumOre);
        setRender(ObjList.ElectrumBlock);
        setRender(ObjList.StarOre);
        setRender(ObjList.MagicWood);

        setRender(ObjList.ElectrumStick);
        setRender(ObjList.SunIngot);
        setRender(ObjList.ElectrumIngot);
        setRender(ObjList.MyfrilIngot);
        setRender(ObjList.OryhalkIngot);
        setRender(ObjList.StarIngot);

        setRender(ObjList.SunSword);
        setRender(ObjList.ReaperScythe);
        setRender(ObjList.StoneFish);
        setRender(ObjList.Mjolnir);
        setRender(ObjList.FreiaBow);

        setRender(ObjList.MagickStick);

        setRender(ObjList.IkarusWing);

        setRender(ObjList.GlassWatch);
    }

    private static void setRegister(Block block, String RegistryName) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(RegistryName));
    }

    private static void setRegister() {
        ForgeRegistries.ITEMS.register(ObjList.SunSword);
        ForgeRegistries.ITEMS.register(ObjList.ReaperScythe);
        ForgeRegistries.ITEMS.register(ObjList.StoneFish);
        ForgeRegistries.ITEMS.register(ObjList.Mjolnir);
        ForgeRegistries.ITEMS.register(ObjList.FreiaBow);

        ForgeRegistries.ITEMS.register(ObjList.IkarusWing);

        ForgeRegistries.ITEMS.register(ObjList.MagickStick);

        ForgeRegistries.ITEMS.register(ObjList.ElectrumStick);
        ForgeRegistries.ITEMS.register(ObjList.SunIngot);
        ForgeRegistries.ITEMS.register(ObjList.ElectrumIngot);
        ForgeRegistries.ITEMS.register(ObjList.MyfrilIngot);
        ForgeRegistries.ITEMS.register(ObjList.OryhalkIngot);
        ForgeRegistries.ITEMS.register(ObjList.StarIngot);


        ForgeRegistries.ITEMS.register(ObjList.GlassWatch);
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    private static void setProfession() {
        // ForgeRegistries.VILLAGER_PROFESSIONS.register();+
    }
}
