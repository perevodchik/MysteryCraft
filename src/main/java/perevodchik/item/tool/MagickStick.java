package perevodchik.item.tool;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class MagickStick extends Item {

    public MagickStick(String name, CreativeTabs tab) {
        this.setMaxStackSize(1);
        this.setMaxDamage(500);
        this.setRegistryName("magick_stick");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState target = worldIn.getBlockState(pos);
        return EnumActionResult.SUCCESS;
    }

    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player)
    {
        return false;
    }

}
