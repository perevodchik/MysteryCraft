package perevodchik.item.unique;

import net.minecraft.creativetab.CreativeTabs;
import perevodchik.item.ItemLance;

public class PoseidonTrident extends ItemLance {

    public PoseidonTrident(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("poseidon_trident");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
