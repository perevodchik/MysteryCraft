package perevodchik.item.unique;

import net.minecraft.creativetab.CreativeTabs;
import perevodchik.item.ItemLance;

public class AidLance extends ItemLance {

    public AidLance(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("aid_biden");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

}
