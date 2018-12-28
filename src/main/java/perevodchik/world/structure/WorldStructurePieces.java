package perevodchik.world.structure;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldStructurePieces {

    public static void registerScatteredFeaturePieces() {
        MapGenStructureIO.registerStructureComponent(WorldStructurePieces.Caravan.class, "TeC");
        MapGenStructureIO.registerStructureComponent(WorldStructurePieces.Shrine.class, "TeS");
        //MapGenStructureIO.registerStructureComponent(WorldStructurePieces.SwampHut.class, "TeSH");
        //MapGenStructureIO.registerStructureComponent(WorldStructurePieces.Igloo.class, "Iglu");
    }

    abstract static class Feature extends StructureComponent {
        /** The size of the bounding box for this feature in the X axis */
        private int width;
        /** The size of the bounding box for this feature in the Y axis */
        private int height;
        /** The size of the bounding box for this feature in the Z axis */
        private int depth;
        private int horizontalPos = -1;

        public Feature() { }

        protected Feature(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
            super(0);
            this.width = sizeX;
            this.height = sizeY;
            this.depth = sizeZ;
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

            if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
            {
                this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeZ - 1, y + sizeY - 1, z + sizeX - 1);
            }
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            tagCompound.setInteger("Width", this.width);
            tagCompound.setInteger("Height", this.height);
            tagCompound.setInteger("Depth", this.depth);
            tagCompound.setInteger("HPos", this.horizontalPos);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
            this.width = tagCompound.getInteger("Width");
            this.height = tagCompound.getInteger("Height");
            this.depth = tagCompound.getInteger("Depth");
            this.horizontalPos = tagCompound.getInteger("HPos");
        }

        /**
         * Calculates and offsets this structure boundingbox to average ground level
         */
        protected boolean offsetToAverageGroundLevel(World worldIn, StructureBoundingBox structurebb, int yOffset)
        {
            if (this.horizontalPos >= 0)
            {
                return true;
            }
            else
            {
                int i = 0;
                int j = 0;
                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
                {
                    for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
                    {
                        blockpos$mutableblockpos.setPos(l, 64, k);

                        if (structurebb.isVecInside(blockpos$mutableblockpos))
                        {
                            i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel());
                            ++j;
                        }
                    }
                }

                if (j == 0)
                {
                    return false;
                }
                else
                {
                    this.horizontalPos = i / j;
                    this.boundingBox.offset(0, this.horizontalPos - this.boundingBox.minY + yOffset, 0);
                    return true;
                }
            }
        }
    }

    public static class Shrine extends WorldStructurePieces.Feature {

        public Shrine() {
        }

        public Shrine(Random rand, int x, int z) {
            super(rand, x, 64, z, 7, 7, 9);
        }

        @Override
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
            if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, 0)) {
                return false;
            }
            else {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 0, 0, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 4, 0, 1, Blocks.MOSSY_COBBLESTONE.getDefaultState(), Blocks.MOSSY_COBBLESTONE.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 4, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 3, 3, 0, 3, Blocks.MOSSY_COBBLESTONE.getDefaultState(), Blocks.MOSSY_COBBLESTONE.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 4, 4, 0, 4, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 1, 3, 3, 3, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);


                this.setBlockState(worldIn, Blocks.STONE_SLAB.getDefaultState(), 0, 0, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getDefaultState(), 4, 0, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 1, 0, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 3, 0, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getDefaultState(), 4, 0, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getDefaultState(), 4, 0, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 1, 1, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 3, 1, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 1, 1, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 3, 1, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 1, 2, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 3, 2, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 1, 2, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 3, 2, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.STONE_SLAB.getDefaultState(), 1, 3, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, -1, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, -2, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.WATER.getDefaultState(), 2, -1, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.WATER.getDefaultState(), 2, -2, 2, structureBoundingBoxIn);

                return true;
            }
        }
    }

    public static class Caravan extends WorldStructurePieces.Feature {

        public Caravan() {
        }

        public Caravan(Random rand, int x, int z) {
            super(rand, x, 64, z, 7, 7, 9);
        }

        @Override
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
            if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, 0)) {
                return false;
            }
            else {
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 1, Blocks.WOOL.getDefaultState(), Blocks.WOOL.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 1, 9, 0, 1, Blocks.WOOL.getDefaultState(), Blocks.WOOL.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 0, 1, 12, 0, 1, Blocks.WOOL.getDefaultState(), Blocks.WOOL.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 4, 7, 0, 4, Blocks.WOOL.getDefaultState(), Blocks.WOOL.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 12, 3, 0, 12, Blocks.WOOL.getDefaultState(), Blocks.WOOL.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 0, 12, 11, 0, 12, Blocks.WOOL.getDefaultState(), Blocks.WOOL.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 1, 12, 1, 1, Blocks.WOOL.getDefaultState(), Blocks.WOOL.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 5, 2, 0, 5, Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 6, 2, 0, 6, Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 7, 2, 0, 7, Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 0, 2, 8, 0, 2, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 4, 5, 0, 4, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 6, 4, 0, 7, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 5, 12, 0, 5, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 10, 13, 0, 10, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 12, 8, 0, 12, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 12, 13, 0, 12, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), false);

                this.setBlockState(worldIn, Blocks.WOOL.getDefaultState(), 0, 0, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.COBBLESTONE.getDefaultState(), 1, 0, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.DIRT.getDefaultState(), 2, 0, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.DIRT.getDefaultState(), 12, 0, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.DIRT.getDefaultState(), 0, 0, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.DIRT.getDefaultState(), 6, 0, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.DIRT.getDefaultState(), 8, 0, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.DIRT.getDefaultState(), 12, 0, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.DIRT.getDefaultState(), 1, 0, 12, structureBoundingBoxIn);

                this.setBlockState(worldIn, Blocks.WOOL.getDefaultState(), 6, 0, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.WOOL.getDefaultState(), 1, 0, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.WOOL.getDefaultState(), 5, 0, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.WOOL.getDefaultState(), 7, 0, 2, structureBoundingBoxIn);

                return true;
            }
        }
    }
}
