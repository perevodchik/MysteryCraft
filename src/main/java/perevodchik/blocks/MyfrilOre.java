package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MyfrilOre extends Block {

    public MyfrilOre(Material blockMaterialIn, String name, CreativeTabs tab) {
        super(blockMaterialIn);
        this.setRegistryName("myfril_ore");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
