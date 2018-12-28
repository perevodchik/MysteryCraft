package perevodchik.util.generate;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Generate {

    public static void fill(World world, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, IBlockState block) {
        for(int x = minX; x <= maxX; x++) {
            for(int y = minY; y <= maxY; y++) {
                for(int z = minZ; z<= maxZ; z++) {
                    world.setBlockState(new BlockPos(x, y, z), block);
                }
            }
        }
    }

    public static void fill(World world, BlockPos from, BlockPos to, IBlockState block) {
        fill(world, from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ(), block);
    }
}
