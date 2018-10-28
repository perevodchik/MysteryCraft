package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class StarOre extends Block {

    public StarOre(Material blockMaterialIn, String name, CreativeTabs tab) {
        super(blockMaterialIn);
        this.setRegistryName("star_ore");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
