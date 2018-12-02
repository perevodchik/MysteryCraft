package perevodchik.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class SkyIslandGenerate implements IWorldGenerator {

    @Override
    public void generate(Random rnd, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider) {
        //корди центр чанку
        int cchunkX = 0;
        int cchunkZ = 0;

        boolean cg = false;
        for(int k = 0; k < 49; k++) {
            // шукаємо центр острову
            int i = k%7-3;
            int j = k/7-3;
            if(canGenerateHere(world, chunkX+i, chunkZ+j)){
                cchunkX = chunkX + i;
                cchunkZ = chunkZ + j;

                cg = true;
                break;
            }

        }
        if(!cg)return;//якщо центр не знайдено - вихід

        //центр чанку в якості центру острову
        int x = cchunkX*16+8;
        int z = cchunkZ*16+8;
        int y = 40;

        rnd = new Random((long)x * 341873128712L + (long)z * 132897987541L + world.getWorldInfo().getSeed() + (long)27644437);

        int r = 16+rnd.nextInt(17);//радіус острова від 16 до 32

        NoiseGeneratorPerlin perlin = new NoiseGeneratorPerlin(rnd, 2);

        //цикл в межах потрібного чанку
        int iMin = ( chunkX-cchunkX ) * 16-8;
        int iMax = ( chunkX-cchunkX+1 ) * 16-8;
        int jMin = ( chunkZ-cchunkZ ) * 16-8;
        int jMax = ( chunkZ-cchunkZ+1 ) * 16-8;

        for(int i = iMax;  i >= iMin; i--) {
            for(int j = jMax; j >= jMin; j--) {
                double l = Math.sqrt(i*i+j*j);

                int hT = (int)( perlin.getValue((x+i)/40., (z+j)/40.)*3 - (i*i+j*j)/(10.*r) );//верхній шум
                int hB = (int) perlin.getValue((x+i), (z+j))*2;//нижній шум

                hB += (int)l-r;

                for(int k = hT; k >= hB; k--) {

                    BlockPos pos = new BlockPos(x+i, y+k, z+j);

                    IBlockState block = Blocks.DIAMOND_BLOCK.getDefaultState();
                    world.setBlockToAir(pos);
                }
            }
        }


    }

    private static boolean canGenerateHere(World world, int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0) {
            i = chunkX - 9;
        }

        if (chunkZ < 0) {
            j = chunkZ - 9;
        }

        i /= 15;
        j /= 15;
        i *= 15;
        j *= 15;

        Random random = new Random((long)i * 341873128712L + (long)j * 132897987541L + world.getWorldInfo().getSeed() + (long)27644437);
        i += random.nextInt(5);
        j += random.nextInt(5);

        if( i == chunkX && j == chunkZ ){
            Chunk c = world.getChunkFromChunkCoords(chunkX, chunkZ);
            System.out.println(chunkX + " ||| " + chunkZ);
            System.out.println(world.getChunkFromChunkCoords(chunkX, chunkZ).getPos().getXEnd());
            System.out.println(world.getChunkFromChunkCoords(chunkX, chunkZ).getPos().getZEnd());
            Biome biome = c.getBiome(new BlockPos(chunkX, chunkZ, c.getHeightValue(8, 8)), world.getBiomeProvider());
            //if(biome == Biome.getBiome(2) || biome == Biome.getBiome(17) || biome == Biome.getBiome(130)) {
                return true;
            //}
        }

        return false;
    }
}
