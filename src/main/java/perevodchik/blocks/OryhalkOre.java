package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class OryhalkOre extends Block {

    public OryhalkOre(Material blockMaterialIn, String name, CreativeTabs tab) {
        super(blockMaterialIn);
        this.setRegistryName("oryhalk_ore");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
