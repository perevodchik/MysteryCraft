package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class StarIgnot extends Item {

    public StarIgnot (CreativeTabs tab, String name) {
        this.setRegistryName("star_ignot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
