package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ElectrumOre extends Block {

    public ElectrumOre(Material blockMaterialIn, String name, CreativeTabs tab) {
        super(blockMaterialIn);
        this.setRegistryName("electrum_ore");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }
}
