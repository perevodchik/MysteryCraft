package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SunIngot extends Item {

    public SunIngot(String name, CreativeTabs tab) {
        this.setRegistryName("sun_ingot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
