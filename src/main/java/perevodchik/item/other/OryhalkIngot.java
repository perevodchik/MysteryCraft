package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class OryhalkIngot extends Item {

    public OryhalkIngot (String name, CreativeTabs tab) {
        this.setRegistryName("oryhalk_ingot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
