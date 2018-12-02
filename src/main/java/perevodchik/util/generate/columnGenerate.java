package perevodchik.util.generate;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class columnGenerate {

    public static void generateUp(World world, BlockPos pos, int maxY, ArrayList<Block> blockMap) {
       BlockPos.MutableBlockPos pos1 = (BlockPos.MutableBlockPos) pos;
       Random r = new Random();
       for (int c = pos.getY(); c <= maxY; c++) {
           IBlockState b = blockMap.get(r.nextInt(blockMap.size()-1)).getDefaultState();
           world.setBlockState(pos1, b);
        }
    }

    public static void generateUp(World world, BlockPos pos, int maxY, IBlockState block) {
        //BlockPos.MutableBlockPos pos1 = (BlockPos.MutableBlockPos) pos;
        int c = maxY-pos.getY();
        for(int i = 0; i <= c; i++) {
            world.setBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()), block);
            //pos1.add(0, 1, 0);
        }
    }
}
