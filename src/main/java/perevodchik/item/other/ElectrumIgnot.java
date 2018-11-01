package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ElectrumIgnot extends Item {

    public ElectrumIgnot (CreativeTabs tab, String name) {
        this.setRegistryName("electrum_ignot");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
