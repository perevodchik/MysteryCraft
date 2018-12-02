package perevodchik.entity.villager;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import perevodchik.entity.AI.EntityAIForestman;

import javax.annotation.Nullable;

public class EntityModVillager extends EntityVillager {
    @Nullable
    Village village;

    public VillagerRegistry.VillagerProfession profession;
    public String villagerClass;
    private boolean hasFarmItem;

    public EntityModVillager(World worldIn) {
        super(worldIn);
    }

    public EntityModVillager(World worldIn, VillagerRegistry.VillagerProfession profession) {
        super(worldIn);
        this.setProfession(profession);
    }

    public EntityModVillager(World worldIn, BlockPos pos, VillagerRegistry.VillagerProfession profession) {
        super(worldIn);
        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
        this.setProfession(profession);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.5D, true));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIForestman(this, 0.9D));

    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        //this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
        //this.getAttributeMap().registerAttribute(SharedMonsterAttributes.)
    }

    public Village getVillage() {
        return this.village;
    }

    public Boolean isHaveAxe() {
        return false;
    }
}
