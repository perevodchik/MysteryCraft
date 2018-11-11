package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class StarIngot extends Item {

    public StarIngot (String name, CreativeTabs tab) {
        this.setRegistryName("star_ingot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
