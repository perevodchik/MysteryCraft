package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MyfrilIgnot extends Item {

    public MyfrilIgnot (CreativeTabs tab, String name) {
        this.setRegistryName("myfril_ignot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
