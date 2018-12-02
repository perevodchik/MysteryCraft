package perevodchik.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class StructureGenerate implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int x = random.nextInt(15);
        int y = world.getHeight(chunkX, chunkZ);
        int z = random.nextInt(15);
        BlockPos p = new BlockPos(x, y, z);
        Chunk c = world.getChunkFromChunkCoords(chunkX, chunkZ);
        world.getChunkFromChunkCoords(chunkX, chunkZ);

        if(c.getBiome(p, world.getBiomeProvider()) == Biome.getBiome(1)) {
            //for (int a = 0; a < 1; a++) {
                int i = x + random.nextInt(256);
                int j = z + random.nextInt(256);
                int k = world.getHeight(i, j);
                /*new StructureWell().generate(world, random, new BlockPos(i, k, j));
            }*/
            //new StructureMonsterTower().generate(world, random, new BlockPos(i, k, j));
        }
    }
}
