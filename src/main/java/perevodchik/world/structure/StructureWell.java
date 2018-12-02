package perevodchik.world.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class StructureWell extends WorldGenerator {

    private Block[] GetValidSpawnBlocks() {
        return new Block[] {
                Blocks.STONE,
                Blocks.GRAVEL,
                Blocks.GRASS,
                Blocks.DIRT,
                Blocks.SAND
        };
    }

    private boolean LocationIsValidSpawn(World world, int x, int y, int z){
        int distanceToAir = 0;
        Block checkBlock = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        while(checkBlock != Blocks.AIR){
            distanceToAir++;
            checkBlock = world.getBlockState(new BlockPos(x,y+distanceToAir,z)).getBlock();
        }
        if(distanceToAir > 1){
            return true;
        }
        y += distanceToAir - 1;
        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        Block blockAbove = world.getBlockState(new BlockPos(x, y+1, z)).getBlock();
        Block blockBelow = world.getBlockState(new BlockPos(x, y-1, z)).getBlock();
        for(Block i : GetValidSpawnBlocks()){
            if(blockAbove != Blocks.AIR){
                return true;
            }
            if(block == i){
                return false;
            }
            else if(block.getMaterial(block.getDefaultState()) == Material.PLANTS && blockBelow==i){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int x = position.getX();
        int y = position.getY();
        int z = position.getZ();

        if(LocationIsValidSpawn(worldIn, x, y, z) || LocationIsValidSpawn(worldIn, x + 4, y, z) || LocationIsValidSpawn(worldIn, x + 4, y, z + 4) || LocationIsValidSpawn(worldIn, x, y, z + 4)){
            return false;
        }

        worldIn.setBlockState(position, Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x, y, z), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y, z), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y, z), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y, z), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+4, y, z), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x, y, z+1), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y, z+1), Blocks.MOSSY_COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y, z+1), Blocks.MOSSY_COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y, z+1), Blocks.MOSSY_COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+4, y, z+1), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x, y, z+2), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y, z+2), Blocks.MOSSY_COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y, z+2), Blocks.MOSSY_COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+4, y, z+2), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x, y, z+3), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y, z+3), Blocks.MOSSY_COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y, z+3), Blocks.MOSSY_COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y, z+3), Blocks.MOSSY_COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+4, y, z+3), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x, y, z+4), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y, z+4), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y, z+4), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y, z+4), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+4, y, z+4), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y+1, z+1), Blocks.OAK_FENCE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y+1, z+1), Blocks.OAK_FENCE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y+1, z+3), Blocks.OAK_FENCE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y+1, z+3), Blocks.OAK_FENCE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y+2, z+1), Blocks.OAK_FENCE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y+2, z+1), Blocks.OAK_FENCE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y+2, z+3), Blocks.OAK_FENCE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y+2, z+3), Blocks.OAK_FENCE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y+3, z+1), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y+3, z+1), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y+3, z+1), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y+3, z+2), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y+3, z+2), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y+3, z+2), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y+3, z+3), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y+3, z+3), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y+3, z+3), Blocks.STONE_SLAB.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y-1, z+1), Blocks.COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y-1, z+2), Blocks.COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y-1, z+3), Blocks.COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y-1, z+2), Blocks.COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y-2, z+1), Blocks.COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+1, y-2, z+2), Blocks.COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y-2, z+3), Blocks.COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+3, y-2, z+2), Blocks.COBBLESTONE.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y-1, z+2), Blocks.AIR.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y-2, z+2), Blocks.AIR.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y-1, z+2), Blocks.WATER.getDefaultState());
        worldIn.setBlockState(new BlockPos.MutableBlockPos().add(x+2, y-2, z+2), Blocks.WATER.getDefaultState());
        return true;
    }
}
