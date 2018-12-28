package perevodchik.village;

import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class VillageMod {

    public static void registerVillagePieces()
    {
        //MapGenStructureIO.registerStructureComponent(VillageMod.ArcherHut.class, "ViBH");//**
        //MapGenStructureIO.registerStructureComponent(VillageMod.Field1.class, "ViDF");//**
        MapGenStructureIO.registerStructureComponent(VillageMod.Field2.class, "ViF");//**
        //MapGenStructureIO.registerStructureComponent(VillageMod.Torch.class, "ViL");//**
        //MapGenStructureIO.registerStructureComponent(VillageMod.Banner.class, "ViB");
        //MapGenStructureIO.registerStructureComponent(VillageMod.GuardHouse.class, "ViGH");//**
        MapGenStructureIO.registerStructureComponent(VillageMod.Shrine.class, "ViSH");//
        //MapGenStructureIO.registerStructureComponent(VillageMod.SmallHut.class, "ViSmH");//**
        MapGenStructureIO.registerStructureComponent(VillageMod.BigField.class, "ViBF");//**
        MapGenStructureIO.registerStructureComponent(VillageMod.FarmHouse.class, "ViFH");//**
        MapGenStructureIO.registerStructureComponent(VillageMod.Start.class, "ViStart");//**
        MapGenStructureIO.registerStructureComponent(VillageMod.Path.class, "ViPath");//**
        MapGenStructureIO.registerStructureComponent(VillageMod.House.class, "ViHome");//**
        MapGenStructureIO.registerStructureComponent(VillageMod.House2.class, "ViHome2");//**
        MapGenStructureIO.registerStructureComponent(VillageMod.Well.class, "ViWell");//**
    }

    static List<VillageMod.PieceWeight> getStructureVillageWeightedPieceList(Random random, int size)
    {
        List<VillageMod.PieceWeight> list = Lists.<VillageMod.PieceWeight>newArrayList();
        list.add(new VillageMod.PieceWeight(VillageMod.Shrine.class, 3, MathHelper.getInt(random, 1 + size, 1 + size * 2)));
        list.add(new VillageMod.PieceWeight(VillageMod.House.class, 10, MathHelper.getInt(random, 0, 1 + size)));
        list.add(new VillageMod.PieceWeight(VillageMod.House2.class, 15, MathHelper.getInt(random, 2, 1 + size)));
        list.add(new VillageMod.PieceWeight(VillageMod.Field2.class, 5, MathHelper.getInt(random, 2 + size, 2 + size * 2)));
        list.add(new VillageMod.PieceWeight(VillageMod.FarmHouse.class, 3, MathHelper.getInt(random, 2 + size, 5 + size * 3)));
        list.add(new VillageMod.PieceWeight(VillageMod.BigField.class, 15, MathHelper.getInt(random, 1 + size, 2 + size)));
        /*list.add(new VillageMod.PieceWeight(VillageMod.Field1.class, 3, MathHelper.getInt(random, 1 + size, 4 + size)));
        list.add(new VillageMod.PieceWeight(VillageMod.Field2.class, 3, MathHelper.getInt(random, 2 + size, 4 + size * 2)));
        list.add(new VillageMod.PieceWeight(VillageMod.BathHouse.class, 15, MathHelper.getInt(random, 0, 1 + size)));
        list.add(new VillageMod.PieceWeight(VillageMod.GuardHouse.class, 8, MathHelper.getInt(random, 0 + size, 3 + size * 2)));*/

        list.removeIf(pieceWeight -> (pieceWeight).villagePiecesLimit == 0);

        return list;
    }

    private static int updatePieceWeight(List<VillageMod.PieceWeight> pieceWeight)
    {
        boolean flag = false;
        int i = 0;

        for (VillageMod.PieceWeight pieceweight : pieceWeight)
        {
            if (pieceweight.villagePiecesLimit > 0 && pieceweight.villagePiecesSpawned < pieceweight.villagePiecesLimit)
            {
                flag = true;
            }

            i += pieceweight.villagePieceWeight;
        }

        return flag ? i : -1;
    }

    private static VillageMod.Village findAndCreateComponentFactory(VillageMod.Start start, VillageMod.PieceWeight weight, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType)
    {
        Class <? extends VillageMod.Village > oclass = weight.villagePieceClass;
        VillageMod.Village StructurePieces$village = null;

        if (oclass == VillageMod.Shrine.class)
        {
            StructurePieces$village = VillageMod.Shrine.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == VillageMod.House.class)
        {
            StructurePieces$village = VillageMod.House.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == VillageMod.House2.class)
        {
            StructurePieces$village = VillageMod.House2.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == VillageMod.Field2.class)
        {
            StructurePieces$village = VillageMod.Field2.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == VillageMod.BigField.class)
        {
            StructurePieces$village = VillageMod.BigField.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }
        else if (oclass == VillageMod.FarmHouse.class)
        {
            StructurePieces$village = VillageMod.FarmHouse.createPiece(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);
        }

        return StructurePieces$village;
    }

    private static VillageMod.Village generateComponent(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType)
    {
        int i = updatePieceWeight(start.structureVillageWeightedPieceList);

        if (i <= 0)
        {
            return null;
        }
        else
        {
            int j = 0;

            while (j < 5)
            {
                ++j;
                int k = rand.nextInt(i);

                for (VillageMod.PieceWeight NordStructurePieces$pieceweight : start.structureVillageWeightedPieceList)
                {
                    k -= NordStructurePieces$pieceweight.villagePieceWeight;

                    if (k < 0)
                    {
                        if (!NordStructurePieces$pieceweight.canSpawnMoreVillagePiecesOfType(componentType) || NordStructurePieces$pieceweight == start.lastPlaced && start.structureVillageWeightedPieceList.size() > 1)
                        {
                            break;
                        }

                        VillageMod.Village NordStructurePieces$village = findAndCreateComponentFactory(start, NordStructurePieces$pieceweight, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType);

                        if (NordStructurePieces$village != null)
                        {
                            ++NordStructurePieces$pieceweight.villagePiecesSpawned;
                            start.lastPlaced = NordStructurePieces$pieceweight;

                            if (!NordStructurePieces$pieceweight.canSpawnMoreVillagePieces())
                            {
                                start.structureVillageWeightedPieceList.remove(NordStructurePieces$pieceweight);
                            }

                            return NordStructurePieces$village;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = VillageMod.Torch.findPieceBox(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing);

            if (structureboundingbox != null)
            {
                // if(rand.nextInt(100) < 50) return new LatinStructurePieces.Banner(start, componentType, rand, structureboundingbox, EnumFacing.UP);///return new LatinStructurePieces.Torch(start, componentType, rand, structureboundingbox, facing);
                //else return new LatinStructurePieces.Banner(start, componentType, rand, structureboundingbox, EnumFacing.UP);
                return new VillageMod.Torch(start, componentType, rand, structureboundingbox, facing);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent generateAndAddComponent(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType)
    {
        if (componentType > 50)
        {
            return null;
        }
        else if (Math.abs(structureMinX - start.getBoundingBox().minX) <= 112 && Math.abs(structureMinZ - start.getBoundingBox().minZ) <= 112)
        {
            StructureComponent structurecomponent = generateComponent(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing, componentType + 1);

            if (structurecomponent != null)
            {
                structureComponents.add(structurecomponent);
                start.pendingHouses.add(structurecomponent);
                return structurecomponent;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    private static StructureComponent generateAndAddRoadPiece(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType)
    {
        if (componentType > 3 + start.terrainType)
        {
            return null;
        }
        else if (Math.abs(structureMinX - start.getBoundingBox().minX) <= 112 && Math.abs(structureMinZ - start.getBoundingBox().minZ) <= 112)
        {
            StructureBoundingBox structureboundingbox = VillageMod.Path.findPieceBox(start, structureComponents, rand, structureMinX, structureMinY, structureMinZ, facing);

            if (structureboundingbox != null && structureboundingbox.minY > 10)
            {
                StructureComponent structurecomponent = new VillageMod.Path(start, componentType, rand, structureboundingbox, facing);
                structureComponents.add(structurecomponent);
                start.pendingRoads.add(structurecomponent);
                return structurecomponent;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    /* ********************************************************************************** */

    static class PieceWeight {
        final int villagePieceWeight;
        Class<? extends VillageMod.Village> villagePieceClass;
        int villagePiecesSpawned;
        int villagePiecesLimit;

        PieceWeight(Class<? extends VillageMod.Village> villagePieceClass, int villagePieceWeight, int villagePiecesLimit) {
            this.villagePieceClass = villagePieceClass;
            this.villagePieceWeight = villagePieceWeight;
            this.villagePiecesLimit = villagePiecesLimit;
        }

        boolean canSpawnMoreVillagePiecesOfType(int componentType) {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }

        boolean canSpawnMoreVillagePieces() {
            return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
        }
    }

    public abstract static class Village extends StructureComponent
    {
        int averageGroundLvl = -1;
        /** The number of villagers that have been spawned in this component. */
        private int villagersSpawned;
        int structureType;
        boolean isZombieInfested;
        boolean isBanditInfested;
        EnumFacing front;
        VillageMod.Start startPiece;

        Village()
        {
        }

        Village(VillageMod.Start start, int type)
        {
            super(type);

            if (start != null)
            {
                this.structureType = start.structureType;
                this.isZombieInfested = start.isZombieInfested;
                this.isBanditInfested = start.isBanditInfested;
                startPiece = start;
            }
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            tagCompound.setInteger("HPos", this.averageGroundLvl);
            tagCompound.setInteger("VCount", this.villagersSpawned);
            tagCompound.setByte("Type", (byte)this.structureType);
            tagCompound.setBoolean("Zombie", this.isZombieInfested);
            tagCompound.setBoolean("Bandit", this.isBanditInfested);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
            this.averageGroundLvl = tagCompound.getInteger("HPos");
            this.villagersSpawned = tagCompound.getInteger("VCount");
            this.structureType = tagCompound.getByte("Type");

            if (tagCompound.getBoolean("Desert"))
            {
                this.structureType = 1;
            }

            this.isZombieInfested = tagCompound.getBoolean("Zombie");
            this.isBanditInfested = tagCompound.getBoolean("Bandit");
        }

        /**
         * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
         */
        @Nullable
        StructureComponent getNextComponentNN(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int p_74891_4_, int p_74891_5_)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                    case NORTH:
                    default:
                        return VillageMod.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                    case SOUTH:
                        return VillageMod.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
                    case WEST:
                        return VillageMod.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    case EAST:
                        return VillageMod.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }
            else
            {
                return null;
            }
        }

        /**
         * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
         */
        @Nullable
        StructureComponent getNextComponentPP(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int p_74894_4_, int p_74894_5_)
        {
            EnumFacing enumfacing = this.getCoordBaseMode();

            if (enumfacing != null)
            {
                switch (enumfacing)
                {
                    case NORTH:
                    default:
                        return VillageMod.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                    case SOUTH:
                        return VillageMod.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
                    case WEST:
                        return VillageMod.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    case EAST:
                        return VillageMod.generateAndAddComponent(start, structureComponents, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
            else
            {
                return null;
            }
        }

        /**
         * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of
         * all the levels in the BB's horizontal rectangle).
         */
        int getAverageGroundLevel(World worldIn, StructureBoundingBox structurebb)
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
                        i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel() - 1);
                        ++j;
                    }
                }
            }

            if (j == 0)
            {
                return -1;
            }
            else
            {
                return i / j;
            }
        }

        static boolean canVillageGoDeeper(StructureBoundingBox structurebb)
        {
            return structurebb != null && structurebb.minY > 10;
        }

        /**
         * Spawns a number of villagers in this component. Parameters: world, component bounding box, x offset, y
         * offset, z offset, number of villagers
         */
        void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count)
        {
            if (this.villagersSpawned < count)
            {
                for (int i = this.villagersSpawned; i < count; ++i)
                {
                    int j = this.getXWithOffset(x + i, z);
                    int k = this.getYWithOffset(y);
                    int l = this.getZWithOffset(x + i, z);

                    if (!structurebb.isVecInside(new BlockPos(j, k, l)))
                    {
                        break;
                    }

                    ++this.villagersSpawned;

                    if (this.isZombieInfested)
                    {
                        EntityZombieVillager entityzombievillager = new EntityZombieVillager(worldIn);
                        entityzombievillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                        entityzombievillager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityzombievillager)), (IEntityLivingData)null);
                        entityzombievillager.enablePersistence();
                        worldIn.spawnEntity(entityzombievillager);
                    }
                    else
                    {
                        EntityVillager entityvillager = new EntityVillager(worldIn);
                        entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                        entityvillager.setProfession(this.chooseForgeProfession(i, entityvillager.getProfessionForge()));
                        entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                        worldIn.spawnEntity(entityvillager);
                    }
                }
            }
        }

        @Deprecated // Use Forge version below.
        protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
        {
            return currentVillagerProfession;
        }

        private net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession chooseForgeProfession(int count, net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession prof)
        {
            return net.minecraftforge.fml.common.registry.VillagerRegistry.getById(chooseProfession(count, net.minecraftforge.fml.common.registry.VillagerRegistry.getId(prof)));
        }

        IBlockState getBiomeSpecificBlockState(IBlockState blockstateIn)
        {
            net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID event = new net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID(startPiece == null ? null : startPiece.biome, blockstateIn);
            net.minecraftforge.common.MinecraftForge.TERRAIN_GEN_BUS.post(event);
            if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.getReplacement();
            if(Blocks.LOG == blockstateIn.getBlock()  || blockstateIn.getBlock()  == Blocks.LOG2){
                return Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.CHISELED);
            }
            if(Blocks.COBBLESTONE == blockstateIn.getBlock() ){
                return Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.SMOOTH);
            }
            if(Blocks.PLANKS == blockstateIn.getBlock() ){
                return Blocks.RED_SANDSTONE.getDefaultState();
            }
            if(Blocks.OAK_FENCE == blockstateIn.getBlock() ){
                return Blocks.COBBLESTONE_WALL.getDefaultState();
            }
            if(Blocks.OAK_STAIRS == blockstateIn.getBlock() ){
                return Blocks.RED_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
            }
            if(Blocks.STONE_STAIRS == blockstateIn.getBlock()){
                return Blocks.RED_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING));
            }
            if(Blocks.GRASS_PATH == blockstateIn.getBlock() ){
                return Blocks.HARDENED_CLAY.getDefaultState();
            }
            if(Blocks.GRAVEL == blockstateIn.getBlock() ){
                return Blocks.CLAY.getDefaultState();
            }
            if(Blocks.DOUBLE_STONE_SLAB == blockstateIn.getBlock() ){
                return Blocks.QUARTZ_BLOCK.getDefaultState().withProperty(BlockQuartz.VARIANT, BlockQuartz.EnumType.LINES_X);
            }
            if(Blocks.STONEBRICK == blockstateIn.getBlock() ){
                return Blocks.RED_SANDSTONE.getDefaultState();
            }
            return blockstateIn;
        }

        private BlockDoor biomeDoor()
        {
            return Blocks.OAK_DOOR;
        }

        void createVillageDoor(World p_189927_1_, StructureBoundingBox p_189927_2_, Random p_189927_3_, int p_189927_4_, int p_189927_5_, int p_189927_6_, EnumFacing p_189927_7_)
        {
            if (!this.isZombieInfested)
            {
                this.generateDoor(p_189927_1_, p_189927_2_, p_189927_3_, p_189927_4_, p_189927_5_, p_189927_6_, EnumFacing.NORTH, this.biomeDoor());
            }
        }

        public void placeTorch(World p_189926_1_, EnumFacing p_189926_2_, int p_189926_3_, int p_189926_4_, int p_189926_5_, StructureBoundingBox p_189926_6_)
        {
            if (!this.isZombieInfested || !this.isBanditInfested)
            {
                this.setBlockState(p_189926_1_, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, p_189926_2_), p_189926_3_, p_189926_4_, p_189926_5_, p_189926_6_);
            }
        }

        /**
         * Replaces air and liquid from given position downwards. Stops when hitting anything else than air or
         * liquid
         */
        protected void replaceAirAndLiquidDownwards(World worldIn, IBlockState blockstateIn, int x, int y, int z, StructureBoundingBox boundingboxIn)
        {
            IBlockState iblockstate = this.getBiomeSpecificBlockState(blockstateIn);
            super.replaceAirAndLiquidDownwards(worldIn, iblockstate, x, y, z, boundingboxIn);
        }

        public void setStructureType(int type)
        {
            this.structureType = type;
        }

    }

    public static class Start extends VillageMod.Well
    {
        BiomeProvider biomeProvider;
        /** World terrain type, 0 for normal, 1 for flap map */
        int terrainType;
        PieceWeight lastPlaced;
        /**
         * Contains List of all spawnable StructureDunge Piece Weights. If no more Pieces of a type can be spawned, they
         * are removed from this list
         */
        List<VillageMod.PieceWeight> structureVillageWeightedPieceList;
        List<StructureComponent> pendingHouses = Lists.<StructureComponent>newArrayList();
        List<StructureComponent> pendingRoads = Lists.<StructureComponent>newArrayList();
        Biome biome;

        public Start()
        {
        }

        public Start(BiomeProvider biomeProviderIn, int var, Random rand, int x, int y, List<VillageMod.PieceWeight> structureVillageWeightedPieceList, int terrainType)
        {
            super((VillageMod.Start)null, 0, rand, x, y);
            this.biomeProvider = biomeProviderIn;
            this.structureVillageWeightedPieceList = structureVillageWeightedPieceList;
            this.terrainType = terrainType;
            Biome biome = biomeProviderIn.getBiome(new BlockPos(x, 0, y), Biomes.DEFAULT);
            this.biome = biome;
            this.startPiece = this;

            System.out.println("v ********************************************************************************** 1");
            System.out.println("********************************************************************************** x " + x + " y " + y);

            if (biome instanceof BiomeDesert)
            {
                this.structureType = 1;
            }
            else if (biome instanceof BiomeSavanna)
            {
                this.structureType = 2;
            }
            else if (biome instanceof BiomeTaiga)
            {
                this.structureType = 3;
            }

            this.setStructureType(this.structureType);
            this.isZombieInfested = rand.nextInt(50) == 0;
        }
    }

    public static class Well extends VillageMod.Village
    {
        Well()
        {
            super();
        }

        Well(VillageMod.Start start, int type, Random rand, int x, int z)
        {
            super(start, type);
            this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

            if (Objects.requireNonNull(this.getCoordBaseMode()).getAxis() == EnumFacing.Axis.Z)
            {
                this.boundingBox = new StructureBoundingBox(x, 64, z, x + 6 - 1, 78, z + 6 - 1);
            }
            else
            {
                this.boundingBox = new StructureBoundingBox(x, 64, z, x + 6 - 1, 78, z + 6 - 1);
            }
        }

        /**
         * Initiates construction of the StructureDunge Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, this.getComponentType());
            VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, this.getComponentType());
            VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
        }

        /**
         * second Part of StructureDunge generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            System.out.println("v ********************************************************************************** 2");
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 3, 0);
            }

            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.DIAMOND_BLOCK.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.ACACIA_STAIRS.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.CYAN_GLAZED_TERRACOTTA.getDefaultState());
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 4, 12, 4, iblockstate, Blocks.FLOWING_WATER.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 12, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 12, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 12, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 12, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 13, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 14, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, 13, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, 14, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 13, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 1, 14, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, 13, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, 14, 4, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 15, 1, 4, 15, 4, iblockstate, iblockstate, false);

            this.setBlockState(worldIn, iblockstate2, 6, 16, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 6, 16, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 4, 16, 6, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate2, 4, 16, 6, structureBoundingBoxIn);

            double x = structureBoundingBoxIn.minX + ((structureBoundingBoxIn.maxX - structureBoundingBoxIn.minX) >> 1);
            double z = structureBoundingBoxIn.minZ + ((structureBoundingBoxIn.maxZ - structureBoundingBoxIn.minZ)/2);
            double y = worldIn.getHeight((int) x, (int) z);

            spawnEntity(worldIn, x, y, z);

            for (int i = 0; i <= 5; ++i)
            {
                for (int j = 0; j <= 5; ++j)
                {
                    if (j == 0 || j == 5 || i == 0 || i == 5)
                    {
                        this.setBlockState(worldIn, iblockstate, j, 11, i, structureBoundingBoxIn);
                        this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, i, structureBoundingBoxIn);
                    }
                }
            }

            return true;
        }

        private void spawnEntity(World world, double x, double y, double z) {
            /*EntityVillagerDeffer e = new EntityVillagerDeffer(world, new BlockPos(x, y, z));
            net.minecraft.village.Village v = world.getVillageCollection().getNearestVillage(new BlockPos(x, y, z), 20);
            e.setVillage(v);
            VillageList.blockedVillageList.put(v, true);
            System.out.println("its ok");
            System.out.println(v.getCenter());*/
        }
    }

    public static class Shrine extends VillageMod.Village
    {
        private boolean isRoofAccessible;

        public Shrine()
        {
        }

        public Shrine(VillageMod.Start start, int type, Random rand, StructureBoundingBox structureBoundingBox, EnumFacing facing)
        {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.front = facing;
            this.boundingBox = structureBoundingBox;
            this.isRoofAccessible = rand.nextBoolean();
        }

        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setBoolean("Shrine", this.isRoofAccessible);
        }

        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager tm)
        {
            super.readStructureFromNBT(tagCompound, tm);
            this.isRoofAccessible = tagCompound.getBoolean("Shrine");
        }

        static VillageMod.Shrine createPiece(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int type)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, 0, 10, 6, 7, facing);
            if (canVillageGoDeeper(structureboundingbox)) {
                StructureComponent.findIntersecting(structureComponents, structureboundingbox);
            }
            return null;
        }

        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            {
                if (this.averageGroundLvl < 0)
                {
                    this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                    if (this.averageGroundLvl < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 3, 0);
                }

                IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.DIAMOND_BLOCK.getDefaultState());
                IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.ACACIA_STAIRS.getDefaultState());
                IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.CYAN_GLAZED_TERRACOTTA.getDefaultState());
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 4, 12, 4, iblockstate, Blocks.FLOWING_WATER.getDefaultState(), false);
                this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 12, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 12, 2, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 12, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 3, 12, 3, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1, 1, 13, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1, 1, 14, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1, 4, 13, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1, 4, 14, 1, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1, 1, 13, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1, 1, 14, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1, 4, 13, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate1, 4, 14, 4, structureBoundingBoxIn);
                this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 15, 1, 4, 15, 4, iblockstate, iblockstate, false);


                this.setBlockState(worldIn, iblockstate2, 6, 16, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate2, 6, 16, 4, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate2, 4, 16, 6, structureBoundingBoxIn);
                this.setBlockState(worldIn, iblockstate2, 4, 16, 6, structureBoundingBoxIn);

                for (int i = 0; i <= 5; ++i)
                {
                    for (int j = 0; j <= 5; ++j)
                    {
                        if (j == 0 || j == 5 || i == 0 || i == 5)
                        {
                            this.setBlockState(worldIn, iblockstate, j, 11, i, structureBoundingBoxIn);
                            this.clearCurrentPositionBlocksUpwards(worldIn, j, 12, i, structureBoundingBoxIn);
                        }
                    }
                }

                return true;
            }
        }
    }

    public static class House extends VillageMod.Village
    {
        public House()
        {
            super();
        }

        House(VillageMod.Start start, int type, Random rand, StructureBoundingBox structureBB, EnumFacing facing)
        {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = structureBB;
        }

        static VillageMod.House createPiece(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int type)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, 0, 9, 9, 6, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(structureComponents, structureboundingbox) == null ? new VillageMod.House(start, type, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of StructureDunge generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            System.out.println("v ********************************************************************************** 3");
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 9 - 1, 0);
            }

            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.DIAMOND_BLOCK.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.DIAMOND_BLOCK.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.DIAMOND_BLOCK.getDefaultState());
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.DIAMOND_BLOCK.getDefaultState());
            IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
            IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState());
            IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 8, 0, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 8, 5, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 1, 8, 6, 4, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 7, 2, 8, 7, 3, iblockstate, iblockstate, false);

            for (int i = -1; i <= 2; ++i)
            {
                for (int j = 0; j <= 8; ++j)
                {
                    this.setBlockState(worldIn, iblockstate1, j, 6 + i, i, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate2, j, 6 + i, 5 - i, structureBoundingBoxIn);
                }
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 5, 8, 1, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 0, 8, 1, 4, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 0, 7, 1, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 4, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 5, 0, 4, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 5, 8, 4, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 0, 8, 4, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 1, 0, 4, 4, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 7, 4, 5, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 1, 8, 4, 4, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 4, 0, iblockstate4, iblockstate4, false);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 6, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 4, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 6, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 3, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 3, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 6, 2, 5, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 7, 4, 1, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 4, 7, 4, 4, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 4, 7, 3, 4, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
            this.setBlockState(worldIn, iblockstate4, 7, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate3, 7, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 6, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 5, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 6, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 6, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 4, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.CRAFTING_TABLE.getDefaultState(), 7, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 1, 2, 0, structureBoundingBoxIn);
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 1, 1, 0, EnumFacing.NORTH);

            if (this.getBlockStateFromPos(worldIn, 1, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
            {
                this.setBlockState(worldIn, iblockstate5, 1, 0, -1, structureBoundingBoxIn);

                if (this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
                {
                    this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 1, -1, -1, structureBoundingBoxIn);
                }
            }

            for (int l = 0; l < 6; ++l)
            {
                for (int k = 0; k < 9; ++k)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, k, 9, l, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, iblockstate, k, -1, l, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 1);
            return true;
        }

        protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
        {
            return 1;
        }
    }

    public static class House2 extends VillageMod.Village
    {
        public House2()
        {
            super();
        }

        House2(VillageMod.Start start, int type, Random rand, StructureBoundingBox structureBB, EnumFacing facing)
        {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = structureBB;
        }

        static VillageMod.House2 createPiece(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int type)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, 0, 9, 9, 6, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(structureComponents, structureboundingbox) == null ? new VillageMod.House2(start, type, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of StructureDunge generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            System.out.println(structureBoundingBoxIn.minX);
            System.out.println(structureBoundingBoxIn.minZ);
            System.out.println("v ********************************************************************************** 3");
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 9 - 1, 0);
            }

            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.BEDROCK.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.BEDROCK.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.BEDROCK.getDefaultState());
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.BEDROCK.getDefaultState());
            IBlockState iblockstate4 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
            IBlockState iblockstate5 = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState());
            IBlockState iblockstate6 = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 8, 0, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 0, 8, 5, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 6, 1, 8, 6, 4, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 7, 2, 8, 7, 3, iblockstate, iblockstate, false);

            for (int i = -1; i <= 2; ++i)
            {
                for (int j = 0; j <= 8; ++j)
                {
                    this.setBlockState(worldIn, iblockstate1, j, 6 + i, i, structureBoundingBoxIn);
                    this.setBlockState(worldIn, iblockstate2, j, 6 + i, 5 - i, structureBoundingBoxIn);
                }
            }

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 1, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 5, 8, 1, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 0, 8, 1, 4, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 0, 7, 1, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 0, 0, 4, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 5, 0, 4, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 5, 8, 4, 5, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 0, 8, 4, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 1, 0, 4, 4, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 7, 4, 5, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 2, 1, 8, 4, 4, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 4, 0, iblockstate4, iblockstate4, false);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 6, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 4, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 6, 3, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 3, 2, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 3, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 3, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 6, 2, 5, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 1, 7, 4, 1, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 4, 4, 7, 4, 4, iblockstate4, iblockstate4, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 3, 4, 7, 3, 4, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
            this.setBlockState(worldIn, iblockstate4, 7, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate3, 7, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 6, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 5, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 4, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate1, 3, 1, 4, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 6, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 6, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate6, 4, 1, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WOODEN_PRESSURE_PLATE.getDefaultState(), 4, 2, 3, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.CRAFTING_TABLE.getDefaultState(), 7, 1, 1, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 1, 2, 0, structureBoundingBoxIn);
            this.createVillageDoor(worldIn, structureBoundingBoxIn, randomIn, 1, 1, 0, EnumFacing.NORTH);

            if (this.getBlockStateFromPos(worldIn, 1, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
            {
                this.setBlockState(worldIn, iblockstate5, 1, 0, -1, structureBoundingBoxIn);

                if (this.getBlockStateFromPos(worldIn, 1, -1, -1, structureBoundingBoxIn).getBlock() == Blocks.GRASS_PATH)
                {
                    this.setBlockState(worldIn, Blocks.GRASS.getDefaultState(), 1, -1, -1, structureBoundingBoxIn);
                }
            }

            for (int l = 0; l < 6; ++l)
            {
                for (int k = 0; k < 9; ++k)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, k, 9, l, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, iblockstate, k, -1, l, structureBoundingBoxIn);
                }
            }

            this.spawnVillagers(worldIn, structureBoundingBoxIn, 2, 1, 2, 1);
            return true;
        }

        protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession)
        {
            return 2;
        }
    }

    public static class FarmHouse extends VillageMod.Village
    {
        /** First crop type for this field. */
        private Block cropTypeA;
        /** Second crop type for this field. */
        private Block cropTypeB;

        public FarmHouse()
        {
        }

        public FarmHouse(VillageMod.Start start, int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
        {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = boundingBox;
            this.cropTypeA = this.getRandomCropType(rand);
            this.cropTypeB = this.getRandomCropType(rand);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("CA", Block.REGISTRY.getIDForObject(this.cropTypeA));
            tagCompound.setInteger("CB", Block.REGISTRY.getIDForObject(this.cropTypeB));
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager tManager)
        {
            super.readStructureFromNBT(tagCompound, tManager);
            this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
            this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
        }

        private Block getRandomCropType(Random rand)
        {
            switch (rand.nextInt(10))
            {
                case 0:
                case 1:
                    return Blocks.CARROTS;
                case 2:
                case 3:
                    return Blocks.POTATOES;
                case 4:
                    return Blocks.BEETROOTS;
                default:
                    return Blocks.WHEAT;
            }
        }

        public static VillageMod.FarmHouse createPiece(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int type)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, 0, 13, 7, 14, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(structureComponents, structureboundingbox) == null ? new VillageMod.FarmHouse(start, type, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of StructureDunge generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
            }


            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 1, 0, 1, 5, Blocks.SPRUCE_FENCE.getDefaultState(), Blocks.SPRUCE_FENCE.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 9, 1, 0, Blocks.SPRUCE_FENCE.getDefaultState(), Blocks.SPRUCE_FENCE.getDefaultState(), false);

            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 1, 0, 1, 5, Blocks.SPRUCE_FENCE.getDefaultState(), Blocks.SPRUCE_FENCE.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 9, 1, 0, Blocks.SPRUCE_FENCE.getDefaultState(), Blocks.SPRUCE_FENCE.getDefaultState(), false);



            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 6, 4, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 5, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 8, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 0, 8, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 5, 0, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 8, 5, 0, 8, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 7, Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), false);

            for (int i = 1; i <= 7; ++i)
            {
                int j = ((BlockCrops)this.cropTypeA).getMaxAge();
                int k = j / 3;
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 1, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 2, 1, i, structureBoundingBoxIn);
                int l = ((BlockCrops)this.cropTypeB).getMaxAge();
                int i1 = l / 3;
                this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 4, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 5, 1, i, structureBoundingBoxIn);
            }

            for (int j1 = 0; j1 < 9; ++j1)
            {
                for (int k1 = 0; k1 < 7; ++k1)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, k1, 4, j1, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, Blocks.DIRT.getDefaultState(), k1, -1, j1, structureBoundingBoxIn);
                }
            }

            return true;
        }
    }

    public static class Field2 extends VillageMod.Village
    {
        /** First crop type for this field. */
        private Block cropTypeA;
        /** Second crop type for this field. */
        private Block cropTypeB;

        public Field2()
        {
        }

        public Field2(VillageMod.Start start, int p_i45569_2_, Random rand, StructureBoundingBox p_i45569_4_, EnumFacing facing)
        {
            super(start, p_i45569_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45569_4_;
            this.cropTypeA = this.getRandomCropType(rand);
            this.cropTypeB = this.getRandomCropType(rand);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("CA", Block.REGISTRY.getIDForObject(this.cropTypeA));
            tagCompound.setInteger("CB", Block.REGISTRY.getIDForObject(this.cropTypeB));
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
            this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
        }

        private Block getRandomCropType(Random rand)
        {
            switch (rand.nextInt(10))
            {
                case 0:
                case 1:
                    return Blocks.CARROTS;
                case 2:
                case 3:
                    return Blocks.POTATOES;
                case 4:
                    return Blocks.BEETROOTS;
                default:
                    return Blocks.WHEAT;
            }
        }

        public static VillageMod.Field2 createPiece(VillageMod.Start start, List<StructureComponent> p_175852_1_, Random rand, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing facing, int p_175852_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175852_1_, structureboundingbox) == null ? new VillageMod.Field2(start, p_175852_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of StructureDunge generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
            }

            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 6, 4, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 2, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 0, 1, 5, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 8, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 0, 8, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 5, 0, 0, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 8, 5, 0, 8, iblockstate, iblockstate, false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 1, 3, 0, 7, Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), false);

            for (int i = 1; i <= 7; ++i)
            {
                int j = ((BlockCrops)this.cropTypeA).getMaxAge();
                int k = j / 3;
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 1, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 2, 1, i, structureBoundingBoxIn);
                int l = ((BlockCrops)this.cropTypeB).getMaxAge();
                int i1 = l / 3;
                this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 4, 1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getInt(randomIn, i1, l)), 5, 1, i, structureBoundingBoxIn);
            }

            for (int j1 = 0; j1 < 9; ++j1)
            {
                for (int k1 = 0; k1 < 7; ++k1)
                {
                    this.clearCurrentPositionBlocksUpwards(worldIn, k1, 4, j1, structureBoundingBoxIn);
                    this.replaceAirAndLiquidDownwards(worldIn, Blocks.DIRT.getDefaultState(), k1, -1, j1, structureBoundingBoxIn);
                }
            }

            return true;
        }
    }

    public static class BigField extends VillageMod.Village
    {
        /** First crop type for this field. */
        private Block cropTypeA;

        public BigField()
        {
        }

        public BigField(VillageMod.Start start, int p_i45569_2_, Random rand, StructureBoundingBox p_i45569_4_, EnumFacing facing)
        {
            super(start, p_i45569_2_);
            this.setCoordBaseMode(facing);
            this.boundingBox = p_i45569_4_;
            this.cropTypeA = this.getRandomCropType(rand);
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("CA", Block.REGISTRY.getIDForObject(this.cropTypeA));
            //tagCompound.setInteger("CB", Block.REGISTRY.getIDForObject(this.cropTypeB));
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
        {
            super.readStructureFromNBT(tagCompound, p_143011_2_);
            this.cropTypeA = Block.getBlockById(tagCompound.getInteger("CA"));
            //this.cropTypeB = Block.getBlockById(tagCompound.getInteger("CB"));
        }

        private Block getRandomCropType(Random rand)
        {
            switch (rand.nextInt(10))
            {
                case 0:
                case 1:
                    return Blocks.CARROTS;
                case 2:
                case 3:
                    return Blocks.POTATOES;
                case 4:
                    return Blocks.BEETROOTS;
                default:
                    return Blocks.WHEAT;
            }
        }

        public static VillageMod.BigField createPiece(VillageMod.Start start, List<StructureComponent> p_175852_1_, Random rand, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing facing, int p_175852_7_)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, facing);
            return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_175852_1_, structureboundingbox) == null ? new VillageMod.BigField(start, p_175852_7_, rand, structureboundingbox, facing) : null;
        }

        /**
         * second Part of StructureDunge generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
                if (this.averageGroundLvl < 0)
                {
                    return true;
                }
                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
            }
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 0, 0, Blocks.OAK_FENCE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 12, Blocks.OAK_FENCE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 12, 0, 0, Blocks.OAK_FENCE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 12, 0, 0, 12, 0, 12, Blocks.OAK_FENCE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 12, 12, 0, 12, Blocks.OAK_FENCE.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 0, 5, 7, 0, 7, Blocks.STONE_BRICK_STAIRS.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 5, 1, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 5, 1, 7, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 7, 1, 5, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.OAK_FENCE.getDefaultState(), 7, 1, 7, structureBoundingBoxIn);
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 2, 5, 7, 2, 7, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
            for(int i = 2; i <= 10; i++) {
                this.setBlockState(worldIn, Blocks.FARMLAND.getDefaultState(), 2, -1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.FARMLAND.getDefaultState(), 3, -1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.FARMLAND.getDefaultState(), 4, -1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.FARMLAND.getDefaultState(), 8, -1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.FARMLAND.getDefaultState(), 9, -1, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, Blocks.FARMLAND.getDefaultState(), 10, -1, i, structureBoundingBoxIn);
            }
            for (int i = 2; i <= 10; ++i)
            {
                int j = ((BlockCrops)this.cropTypeA).getMaxAge();
                int k = j / 3;
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 2, 0, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 3, 0, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 4, 0, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 8, 0, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 9, 0, i, structureBoundingBoxIn);
                this.setBlockState(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getInt(randomIn, k, j)), 10, 0, i, structureBoundingBoxIn);
            }
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, 0, 6,  structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 6, -1, 6,  structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WATER.getDefaultState(), 6, 0, 6,  structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WATER.getDefaultState(), 6, -1, 6,  structureBoundingBoxIn);
            return true;
        }
    }

    public static class Torch extends VillageMod.Village
    {
        public Torch()
        {
            super();
        }

        Torch(VillageMod.Start start, int type, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
        {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = boundingBox;
        }

        static StructureBoundingBox findPieceBox(VillageMod.Start start, List<StructureComponent> structureComponents, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, 0, 3, 4, 2, facing);
            StructureComponent.findIntersecting(structureComponents, structureboundingbox);
            return null;
        }

        /**
         * second Part of StructureDunge generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            if (this.averageGroundLvl < 0)
            {
                this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

                if (this.averageGroundLvl < 0)
                {
                    return true;
                }

                this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
            }

            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 2, 3, 1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
            this.setBlockState(worldIn, iblockstate, 1, 0, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 1, 1, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, iblockstate, 1, 2, 0, structureBoundingBoxIn);
            this.setBlockState(worldIn, Blocks.WOOL.getStateFromMeta(EnumDyeColor.WHITE.getDyeDamage()), 1, 3, 0, structureBoundingBoxIn);
            this.placeTorch(worldIn, EnumFacing.EAST, 2, 3, 0, structureBoundingBoxIn);
            this.placeTorch(worldIn, EnumFacing.NORTH, 1, 3, 1, structureBoundingBoxIn);
            this.placeTorch(worldIn, EnumFacing.WEST, 0, 3, 0, structureBoundingBoxIn);
            this.placeTorch(worldIn, EnumFacing.SOUTH, 1, 3, -1, structureBoundingBoxIn);
            return true;
        }
    }

    abstract static class Road extends VillageMod.Village
    {
        Road()
        {
            super();
        }

        Road(VillageMod.Start start, int type)
        {
            super(start, type);
        }
    }

    public static class Path extends VillageMod.Road
    {
        private int length;

        public Path()
        {
        }

        Path(VillageMod.Start start, int type, Random rand, StructureBoundingBox structBB, EnumFacing facing)
        {
            super(start, type);
            this.setCoordBaseMode(facing);
            this.boundingBox = structBB;
            this.length = Math.max(structBB.getXSize(), structBB.getZSize());
        }

        /**
         * (abstract) Helper method to write subclass data to NBT
         */
        protected void writeStructureToNBT(NBTTagCompound tagCompound)
        {
            super.writeStructureToNBT(tagCompound);
            tagCompound.setInteger("Length", this.length);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager)
        {
            super.readStructureFromNBT(tagCompound, templateManager);
            this.length = tagCompound.getInteger("Length");
        }

        /**
         * Initiates construction of the StructureDunge Component picked, at the current Location of StructGen
         */
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
        {
            boolean flag = false;

            for (int i = rand.nextInt(5); i < this.length - 8; i += 2 + rand.nextInt(5))
            {
                StructureComponent structurecomponent = this.getNextComponentNN((VillageMod.Start)componentIn, listIn, rand, 0, i);

                if (structurecomponent != null)
                {
                    i += Math.max(structurecomponent.getBoundingBox().getXSize(), structurecomponent.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            for (int j = rand.nextInt(5); j < this.length - 8; j += 2 + rand.nextInt(5))
            {
                StructureComponent structurecomponent1 = this.getNextComponentPP((VillageMod.Start)componentIn, listIn, rand, 0, j);

                if (structurecomponent1 != null)
                {
                    j += Math.max(structurecomponent1.getBoundingBox().getXSize(), structurecomponent1.getBoundingBox().getZSize());
                    flag = true;
                }
            }

            EnumFacing enumfacing = this.getCoordBaseMode();

            if (flag && rand.nextInt(3) > 0 && enumfacing != null)
            {
                switch (enumfacing)
                {
                    case NORTH:
                    default:
                        VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, this.getComponentType());
                        break;
                    case SOUTH:
                        VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, this.getComponentType());
                        break;
                    case WEST:
                        VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                        break;
                    case EAST:
                        VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                }
            }

            if (flag && rand.nextInt(3) > 0 && enumfacing != null)
            {
                switch (enumfacing)
                {
                    case NORTH:
                    default:
                        VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, this.getComponentType());
                        break;
                    case SOUTH:
                        VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, this.getComponentType());
                        break;
                    case WEST:
                        VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                        break;
                    case EAST:
                        VillageMod.generateAndAddRoadPiece((VillageMod.Start)componentIn, listIn, rand, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                }
            }
        }

        static StructureBoundingBox findPieceBox(VillageMod.Start start, List<StructureComponent> structList, Random rand, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing)
        {
            for (int i = 7 * MathHelper.getInt(rand, 3, 5); i >= 7; i -= 7)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, 0, 3, 3, i, facing);

                if (StructureComponent.findIntersecting(structList, structureboundingbox) == null)
                {
                    return structureboundingbox;
                }
            }

            return null;
        }

        /**
         * second Part of StructureDunge generating, this for example places Spiderwebs, Mob Spawners, it closes
         * Mineshafts at the end, it adds Fences...
         */
        public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
        {
            IBlockState iblockstate = this.getBiomeSpecificBlockState(Blocks.GRASS_PATH.getDefaultState());
            IBlockState iblockstate1 = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
            IBlockState iblockstate2 = this.getBiomeSpecificBlockState(Blocks.GRAVEL.getDefaultState());
            IBlockState iblockstate3 = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());

            for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
            {
                for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
                {
                    BlockPos blockpos = new BlockPos(i, 64, j);

                    if (structureBoundingBoxIn.isVecInside(blockpos))
                    {
                        blockpos = worldIn.getTopSolidOrLiquidBlock(blockpos).down();

                        if (blockpos.getY() < worldIn.getSeaLevel())
                        {
                            blockpos = new BlockPos(blockpos.getX(), worldIn.getSeaLevel() - 1, blockpos.getZ());
                        }

                        while (blockpos.getY() >= worldIn.getSeaLevel() - 1)
                        {
                            IBlockState iblockstate4 = worldIn.getBlockState(blockpos);

                            if (iblockstate4.getBlock() == Blocks.GRASS && worldIn.isAirBlock(blockpos.up()))
                            {
                                worldIn.setBlockState(blockpos, iblockstate, 2);
                                break;
                            }

                            if (iblockstate4.getMaterial().isLiquid())
                            {
                                worldIn.setBlockState(blockpos, iblockstate1, 2);
                                break;
                            }

                            if (iblockstate4.getBlock() == Blocks.SAND || iblockstate4.getBlock() == Blocks.SANDSTONE || iblockstate4.getBlock() == Blocks.RED_SANDSTONE)
                            {
                                worldIn.setBlockState(blockpos, iblockstate2, 2);
                                worldIn.setBlockState(blockpos.down(), iblockstate3, 2);
                                break;
                            }

                            blockpos = blockpos.down();
                        }
                    }
                }
            }

            return true;
        }
    }
}