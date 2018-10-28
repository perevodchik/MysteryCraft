package perevodchik.item.unique;

import net.minecraft.creativetab.CreativeTabs;
import perevodchik.item.ItemLance;

public class Gungnir extends ItemLance {

    public Gungnir(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("gungnir");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

}
