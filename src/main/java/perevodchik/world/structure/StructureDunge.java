package perevodchik.world.structure;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class StructureDunge extends WorldGenerator {
    public BlockPos center;
    public String type;
    public Boolean isBoss;
    public EntityLiving entity;


    public abstract boolean generate(World worldIn, Random rand, BlockPos position);

    public abstract void spawnEntity(World world, BlockPos pos);


}
