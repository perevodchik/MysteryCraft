package perevodchik.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

public class EntityGhost extends EntityMob {

    protected static final IAttribute reinforcementChance = (new RangedAttribute((IAttribute)null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");

    public EntityGhost(World worldIn) {
        super(worldIn);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 20F));
        this.tasks.addTask(6, new EntityAILookIdle(this));

        this.setSize(0.6f, 1.5f);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(reinforcementChance).setBaseValue(this.rand.nextDouble() * ForgeModContainer.zombieSummonBaseChance);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    public void updateRidden() {
        super.updateRidden();
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
    }

    @Override
    protected Item getDropItem() {
        return Items.BONE;
    }

    @Override
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
        super.dropFewItems(p_70628_1_, p_70628_2_);
        this.dropItem(Items.BONE, rand.nextInt(3) * p_70628_2_);
    }

    @Override
    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEnchantmentBasedOnDifficulty(difficulty);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        return livingdata;
    }

    @Override
    public float getEyeHeight() {
        return 1.3F;
    }

}