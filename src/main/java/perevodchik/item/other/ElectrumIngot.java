package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ElectrumIngot extends Item {

    public ElectrumIngot (String name, CreativeTabs tab) {
        this.setRegistryName("electrum_ingot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
