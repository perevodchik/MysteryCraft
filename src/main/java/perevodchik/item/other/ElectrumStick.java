package perevodchik.item.other;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ElectrumStick extends Item {

    public ElectrumStick(CreativeTabs tab, String name) {
        this.setRegistryName("electrum_stick");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

}
