package perevodchik.entity.AI;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import perevodchik.entity.villager.EntityModVillager;

public class EntityAIForestman extends EntityAIMoveToBlock {
    private EntityModVillager v;
    private Boolean hasAxe;


    public EntityAIForestman(EntityModVillager villagerIn, double speedIn)
    {
        super(villagerIn, speedIn, 16);
        this.v = villagerIn;
        this.hasAxe = v.isHaveAxe();
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.runDelay <= 0)
        {
            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.v.world, this.v))
            {
                return false;
            }

            this.hasAxe = this.v.isHaveAxe();
        }

        return super.shouldExecute();
    }

    public void updateTask()
    {
        super.updateTask();
        this.v.getLookHelper().setLookPosition((double)this.destinationBlock.getX() + 0.5D, (double)(this.destinationBlock.getY() + 1), (double)this.destinationBlock.getZ() + 0.5D, 10.0F, (float)this.v.getVerticalFaceSpeed());

        if (this.getIsAboveDestination())
        {
            World world = this.v.world;
            BlockPos blockpos = this.destinationBlock.up();
            IBlockState iblockstate = world.getBlockState(blockpos);
            Block block = iblockstate.getBlock();

            world.destroyBlock(blockpos, true);

            BlockPos.MutableBlockPos m = (BlockPos.MutableBlockPos) blockpos;

            while(world.getBlockState(m).getMaterial() == Material.WOOD) {
                world.destroyBlock(m, true);
                m.add(0, 1, 0);
            }

            m = (BlockPos.MutableBlockPos) blockpos;

            while(world.getBlockState(m).getMaterial() == Material.WOOD) {
                world.destroyBlock(m, true);
                m.add(0, -1, 0);
            }

            this.runDelay = 10;
        }
    }

    @Override
    protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
        IBlockState block = worldIn.getBlockState(pos);

        if(block.getMaterial() == Material.WOOD) {
            BlockPos.MutableBlockPos p = (BlockPos.MutableBlockPos) pos;
            for(int i = p.getY(); i < p.getY() + 5; i++) {
                if(worldIn.getBlockState(p.add(0, i, 0)).getMaterial() == Material.LEAVES) {
                    return true;
                }
            }
        }

        return false;

    }
}
