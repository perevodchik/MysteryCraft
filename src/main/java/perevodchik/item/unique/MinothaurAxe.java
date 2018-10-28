package perevodchik.item.unique;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class MinothaurAxe extends ItemAxe {

    protected MinothaurAxe(ToolMaterial material, float damage, float speed, CreativeTabs tab, String name) {
        super(material, damage, speed);
        this.setRegistryName("minotaur_axe");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

}
