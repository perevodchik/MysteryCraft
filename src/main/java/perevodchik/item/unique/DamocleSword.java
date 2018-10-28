package perevodchik.item.unique;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class DamocleSword extends ItemSword {

    public DamocleSword(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("damocle_sword");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

}
