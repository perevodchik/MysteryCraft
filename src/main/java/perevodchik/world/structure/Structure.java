package perevodchik.world.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class Structure extends WorldGenerator {
    public BlockPos center;
    public String type;


    public abstract boolean generate(World worldIn, Random rand, BlockPos position);
}
