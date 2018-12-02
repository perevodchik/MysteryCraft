package perevodchik.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import perevodchik.enums.ObjList;

import java.util.Random;

public class OreGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateOverworld(random, chunkX, chunkZ, world);
        generateNether(random, chunkX, chunkZ, world);
        generateEnd(random, chunkX, chunkZ, world);
    }

    private void generateOverworld(Random rand, int chunkX, int chunkZ, World world) {
        generateOre(ObjList.ElectrumOre.getDefaultState(), world, rand, chunkX, chunkZ, 40, 50, rand.nextInt(5)+2, 11);
        generateOre(ObjList.StarOre.getDefaultState(), world, rand, chunkX, chunkZ, 40, 50, rand.nextInt(5)+2, 11);
        generateOre(ObjList.OryhalkOre.getDefaultState(), world, rand, chunkX, chunkZ, 40, 50, rand.nextInt(5)+2, 11);

        // tree

        IBlockState blockStateMagicWood = ObjList.MagicWood.getDefaultState();

    }

    private void generateNether(Random rand, int chunkX, int chunkZ, World world) {
        generateOre(ObjList.ElectrumOre.getDefaultState(), world, rand, chunkX, chunkZ, 40, 50, rand.nextInt(5)+2, 11);
    }

    private void generateEnd(Random rand, int chunkX, int chunkZ, World world) {
        generateOre(ObjList.MyfrilOre.getDefaultState(), world, rand, chunkX, chunkZ, 40, 50, rand.nextInt(5)+2, 11);
    }

    private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances)
    {
        int deltaY = maxY - minY;

        for (int i = 0; i < chances; i++) {
            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, size);
            generator.generate(world, random, pos);
        }
    }


}
