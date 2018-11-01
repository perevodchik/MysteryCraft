package perevodchik.item.weapon;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSword;

public class Rapier extends ItemSword {

    public Rapier(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("rapier");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return blockIn.getBlock() == Blocks.GLASS || blockIn.getBlock() == Blocks.GLASS_PANE;
    }

}
