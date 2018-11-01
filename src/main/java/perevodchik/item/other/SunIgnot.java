package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SunIgnot extends Item {

    public SunIgnot(CreativeTabs tab, String name) {
        this.setRegistryName("sun_ignot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
