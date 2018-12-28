package perevodchik.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FindValidLocation {

    private static boolean LocationIsValidSpawn(World world, int x, int y, int z, Block[] validBlocks) {
        int distanceToAir = 0;
        Block checkBlock = world.getBlockState(new BlockPos(x, y, z)).getBlock();

        while (checkBlock != Blocks.AIR)
        {
            distanceToAir++;
            checkBlock = world.getBlockState(new BlockPos(x, y + distanceToAir, z)).getBlock();
        }

        if (distanceToAir > 1)
        {
            return true;
        }

        y += distanceToAir - 1;

        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        Block blockAbove = world.getBlockState(new BlockPos(x, y + 1, z)).getBlock();
        Block blockBelow = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

        for (Block i : validBlocks)
        {
            if (blockAbove != Blocks.AIR)
            {
                return true;
            }
            if (block == i)
            {
                return false;
            }
            else if (block == Blocks.SNOW_LAYER && blockBelow == i) {
                return false;
            }
            else if (block.getDefaultState().getMaterial() == Material.PLANTS && blockBelow == i)
            {
                return false;
            }
        }
        return true;
    }

}
