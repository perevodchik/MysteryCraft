package perevodchik.world.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import perevodchik.util.generate.columnGenerate;

import java.util.Random;

public class StructureMonsterTower extends WorldGenerator {

    protected Block[] GetValidSpawnBlocks(){
        return new Block[] {Blocks.GRASS,
                Blocks.STONE,
                Blocks.DIRT,
                Blocks.COBBLESTONE,
                Blocks.PLANKS,
                Blocks.GRAVEL};
    }

    public boolean LocationIsValidSpawn(World world, int x, int y, int z)
    {
        int distanceToAir = 0;
        Block checkBlock = world.getBlockState(new BlockPos(x, y, z)).getBlock();

        while (checkBlock != Blocks.AIR)
        {
            distanceToAir++;
            checkBlock = world.getBlockState(new BlockPos(x, y + distanceToAir, z)).getBlock();
        }

        if (distanceToAir > 1)
        {
            return false;
        }

        y += distanceToAir - 1;

        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        Block blockAbove = world.getBlockState(new BlockPos(x, y+1, z)).getBlock();
        Block blockBelow = world.getBlockState(new BlockPos(x, y-1, z)).getBlock();

        for (Block i : GetValidSpawnBlocks())
        {
            if (blockAbove != Blocks.AIR) {
                return false;
            }
            if (block == i) {
                return true;
            }
            else if (block == Blocks.SNOW_LAYER && blockBelow == i) {
                return true;
            }
            else if (block.getDefaultState().getMaterial() == Material.PLANTS && blockBelow == i)
        {
            return true;
        }
        }
        return false;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        columnGenerate.generateUp(worldIn, position, position.getY() + 20, Blocks.DIAMOND_BLOCK.getDefaultState());
        return false;
    }
}
