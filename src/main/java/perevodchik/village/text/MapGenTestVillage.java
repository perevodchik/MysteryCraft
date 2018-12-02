package perevodchik.village.text;

import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapGenTestVillage extends MapGenStructure {
    public static List<Biome> VILLAGE_SPAWN_BIOMES = Arrays.<Biome>asList(Biomes.DESERT, Biomes.PLAINS, Biomes.OCEAN, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER);
    private int size;
    private int averageSpacing;

    public MapGenTestVillage() {
        averageSpacing = 12;
    }

    @Nonnull
    @Override
    public String getStructureName() {
        return "test_village";
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        return findNearestStructurePosBySpacing(worldIn, this, pos, averageSpacing, 8, 10387312, false, 100, findUnexplored);
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int unadjustedX = chunkX;
        int unadjustedZ = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= averageSpacing - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= averageSpacing - 1;
        }

        // randomize relative positions of village candidate sites
        int candidateX = chunkX / averageSpacing;
        int candidateZ = chunkZ / averageSpacing;
        Random random = world.setRandomSeed(candidateX, candidateZ, 10387312);
        candidateX = candidateX * averageSpacing;
        candidateZ = candidateZ * averageSpacing;
        candidateX = candidateX + random.nextInt(averageSpacing - 8);
        candidateZ = candidateZ + random.nextInt(averageSpacing - 8);

        if (unadjustedX == candidateX && unadjustedZ == candidateZ)
        {
            // DEBUG
            System.out.println("Is biome viable for village = "+world.getBiomeProvider().areBiomesViable(unadjustedX * 16 + 8, unadjustedZ * 16 + 8, 0, VILLAGE_SPAWN_BIOMES));
            return world.getBiomeProvider().areBiomesViable(unadjustedX * 16 + 8, unadjustedZ * 16 + 8, 0, VILLAGE_SPAWN_BIOMES);
        }

        return false;
    }

    @Nonnull
    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new MapGenTestVillage.Start(world, rand, chunkX, chunkZ, size);
    }

    public static class Start extends StructureStart
    {
        /** well ... thats what it does */
        private boolean hasMoreThanTwoComponents;

        public Start()
        {
        }

        Start(World worldIn, Random rand, int x, int z, int size)
        {
            super(x, z);

            List<StructureVillagePieces.PieceWeight> list = StructureVillagePieces.getStructureVillageWeightedPieceList(rand, size);
            StructureVillagePieces.Start start = new StructureVillagePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(start);
            start.buildComponent(start, this.components, rand);
            List<StructureComponent> pendingRoads  = start.pendingRoads;
            List<StructureComponent> pendingHouses  = start.pendingHouses;

            while (!pendingRoads .isEmpty() || !pendingHouses .isEmpty())
            {
                if (pendingRoads .isEmpty())
                {
                    int i = rand.nextInt(pendingHouses .size());
                    StructureComponent component = pendingHouses .remove(i);
                    component.buildComponent(start, this.components, rand);
                }
                else
                {
                    int j = rand.nextInt(pendingRoads .size());
                    StructureComponent component2 = pendingRoads .remove(j);
                    component2.buildComponent(start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int nonRoadComponentCount  = 0;

            for (StructureComponent component1 : this.components)
            {
                if (!(component1 instanceof StructureVillagePieces.Road))
                {
                    ++nonRoadComponentCount;
                }
            }

            this.hasMoreThanTwoComponents = nonRoadComponentCount > 2;
        }

        /**
         * currently only defined for Villages, returns true if Village has more than 2 non-road components
         */
        public boolean isSizeableStructure()
        {
            return this.hasMoreThanTwoComponents;
        }

        public void writeToNBT(NBTTagCompound tagCompound)
        {
            super.writeToNBT(tagCompound);
            tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        public void readFromNBT(NBTTagCompound tagCompound)
        {
            super.readFromNBT(tagCompound);
            this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
        }
    }
}
