package perevodchik.world.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class StructureDungeHouse extends StructureDunge {

    public StructureDungeHouse(BlockPos center, String type){
        this.center = center;
        this.type = type;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        return false;
    }

    @Override
    public void spawnEntity(World world, BlockPos pos) {

    }
}
