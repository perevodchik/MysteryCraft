package perevodchik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class NeelSand extends Block {

    public NeelSand(Material materialIn, String name, CreativeTabs tab) {
        super(materialIn);
        this.setRegistryName("neel_sand");
        this.setUnlocalizedName(name);
        this.setLightLevel(150.0F);
        this.setCreativeTab(tab);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //return false;
        playerIn.jump();
        playerIn.dropItem(true);
        return false;
    }
}
