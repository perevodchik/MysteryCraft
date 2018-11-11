package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MyfrilIngot extends Item {

    public MyfrilIngot (String name, CreativeTabs tab) {
        this.setRegistryName("myfril_ingot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
