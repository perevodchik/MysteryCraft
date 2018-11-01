package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class OryhalkIgnot extends Item {

    public OryhalkIgnot (CreativeTabs tab, String name) {
        this.setRegistryName("oryhalk_ignot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
