package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MyfrilBlock extends Block {

    public MyfrilBlock(Material blockMaterialIn, String name, CreativeTabs tab) {
        super(blockMaterialIn);
        this.setRegistryName("myfril_block");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
