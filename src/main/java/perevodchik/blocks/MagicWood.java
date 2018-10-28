package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MagicWood extends Block {

    public MagicWood(Material blockMaterialIn, String name, CreativeTabs tab) {
        super(blockMaterialIn);
        this.setRegistryName("magic_wood");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

}
