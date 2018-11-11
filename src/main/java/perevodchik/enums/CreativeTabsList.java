package perevodchik.enums;

import net.minecraft.item.ItemStack;

public class CreativeTabsList {

    public static final net.minecraft.creativetab.CreativeTabs MTabBlocks = new net.minecraft.creativetab.CreativeTabs("Mystery blocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ObjList.ElectrumOre);
        }
    };

    public static final net.minecraft.creativetab.CreativeTabs MTabTools = new net.minecraft.creativetab.CreativeTabs("Mystery tools") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ObjList.MagicWood);
        }
    };

    public static final net.minecraft.creativetab.CreativeTabs MTabItems = new net.minecraft.creativetab.CreativeTabs("Mystery items") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ObjList.SunIngot);
        }
    };

    public static final net.minecraft.creativetab.CreativeTabs MTabEquip = new net.minecraft.creativetab.CreativeTabs("Mystery equip") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ObjList.Mjolnir);
        }
    };
}
