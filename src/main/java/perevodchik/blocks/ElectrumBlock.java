package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ElectrumBlock extends Block {

    public ElectrumBlock(Material blockMaterialIn, String name, CreativeTabs tab) {
        super(blockMaterialIn);
        this.setRegistryName("electrum_block");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
