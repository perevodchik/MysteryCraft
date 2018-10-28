package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class OryhalkBlock extends Block {

    public OryhalkBlock(Material blockMaterialIn, String name, CreativeTabs tab) {
        super(blockMaterialIn);
        this.setRegistryName("oryhalk_block");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
