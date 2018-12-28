package perevodchik.world.structure;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;
import java.util.Random;

public class MapGenWorldStructure extends MapGenStructure {
    @Override
    public String getStructureName() {
        return "world_structure";
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        this.world = worldIn;
        //return findNearestStructurePosBySpacing(worldIn, this, pos, this.maxDistanceBetweenScatteredFeatures, 8, 14357617, false, 100, findUnexplored);
        return null;
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return false;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new MapGenWorldStructure.Start(this.world, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        public Start()
        {
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ)
        {
            this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn)
        {
            super(chunkX, chunkZ);

            if(biomeIn == Biomes.SAVANNA || biomeIn == Biomes.SAVANNA_PLATEAU || biomeIn == Biomes.MUTATED_SAVANNA) {
                WorldStructurePieces.Caravan structure = new WorldStructurePieces.Caravan(random, chunkX * 16, chunkZ * 16);
                this.components.add(structure);
            }

            /*if (biomeIn != Biomes.JUNGLE && biomeIn != Biomes.JUNGLE_HILLS)
            {
                if (biomeIn == Biomes.SWAMPLAND)
                {
                    ComponentScatteredFeaturePieces.SwampHut componentscatteredfeaturepieces$swamphut = new ComponentScatteredFeaturePieces.SwampHut(random, chunkX * 16, chunkZ * 16);
                    this.components.add(componentscatteredfeaturepieces$swamphut);
                }
                else if (biomeIn != Biomes.DESERT && biomeIn != Biomes.DESERT_HILLS)
                {
                    if (biomeIn == Biomes.ICE_PLAINS || biomeIn == Biomes.COLD_TAIGA)
                    {
                        ComponentScatteredFeaturePieces.Igloo componentscatteredfeaturepieces$igloo = new ComponentScatteredFeaturePieces.Igloo(random, chunkX * 16, chunkZ * 16);
                        this.components.add(componentscatteredfeaturepieces$igloo);
                    }
                }
                else
                {
                    ComponentScatteredFeaturePieces.DesertPyramid componentscatteredfeaturepieces$desertpyramid = new ComponentScatteredFeaturePieces.DesertPyramid(random, chunkX * 16, chunkZ * 16);
                    this.components.add(componentscatteredfeaturepieces$desertpyramid);
                }
            }
            else
            {
                ComponentScatteredFeaturePieces.JunglePyramid componentscatteredfeaturepieces$junglepyramid = new ComponentScatteredFeaturePieces.JunglePyramid(random, chunkX * 16, chunkZ * 16);
                this.components.add(componentscatteredfeaturepieces$junglepyramid);
            }*/

            this.updateBoundingBox();
        }
    }
}
