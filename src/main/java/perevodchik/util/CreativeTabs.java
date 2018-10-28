package perevodchik.util;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CreativeTabs {
    public static final net.minecraft.creativetab.CreativeTabs EgyptTab = new net.minecraft.creativetab.CreativeTabs("Egypt") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.LAVA);
        }
    };

    public static final net.minecraft.creativetab.CreativeTabs SkandinavTab = new net.minecraft.creativetab.CreativeTabs("Skandinav") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.LAVA);
        }
    };
}
