package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class StarBlock extends Block {

    public StarBlock(Material blockMaterialIn, String name, CreativeTabs tab) {
        super(blockMaterialIn);
        this.setRegistryName("star_block");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
