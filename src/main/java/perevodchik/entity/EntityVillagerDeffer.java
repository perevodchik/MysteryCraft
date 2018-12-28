package perevodchik.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import perevodchik.entity.AI.EntityAIDefendVillage;

import javax.annotation.Nullable;

public class EntityVillagerDeffer extends EntityMob {
    @Nullable
    Village village;

    public EntityVillagerDeffer(World worldIn, BlockPos pos) {
        super(worldIn);
        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
        //this.village = v;
    }

    public EntityVillagerDeffer(World worldIn) {
        super(worldIn);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 2, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.1D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.5D, true));
        this.tasks.addTask(4, new EntityAIDefendVillage(this));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

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

    public void setVillage(Village v) {
        this.village = v;
    }

    @Nullable
    protected Item getDropItem()
    {
        return null;
    }

    protected boolean canDespawn()
    {
        return false;
    }

    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    /**
     * Drop the equipment for this entity.
     */
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
    {
        for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
        {
            ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);
            double d0;

            switch (entityequipmentslot.getSlotType())
            {
                case HAND:
                    d0 = (double)this.inventoryHandsDropChances[entityequipmentslot.getIndex()];
                    break;
                case ARMOR:
                    d0 = (double)this.inventoryArmorDropChances[entityequipmentslot.getIndex()];
                    break;
                default:
                    d0 = 0.0D;
            }

            boolean flag = d0 > 1.0D;

            if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack) && (wasRecentlyHit || flag) && (double)(this.rand.nextFloat() - (float)lootingModifier * 0.01F) < d0)
            {
                if (!flag && itemstack.isItemStackDamageable())
                {
                    itemstack.setItemDamage(itemstack.getMaxDamage() - this.rand.nextInt(1 + this.rand.nextInt(Math.max(itemstack.getMaxDamage() - 3, 1))));
                }

                this.entityDropItem(itemstack, 0.0F);
            }
        }
    }

    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        if (this.rand.nextFloat() < 0.15F * difficulty.getClampedAdditionalDifficulty())
        {
            int i = this.rand.nextInt(2);
            float f = this.world.getDifficulty() == EnumDifficulty.HARD ? 0.1F : 0.25F;

            if (this.rand.nextFloat() < 0.095F)
            {
                ++i;
            }

            if (this.rand.nextFloat() < 0.095F)
            {
                ++i;
            }

            if (this.rand.nextFloat() < 0.095F)
            {
                ++i;
            }

            boolean flag = true;

            for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
            {
                if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
                {
                    ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);

                    if (!flag && this.rand.nextFloat() < f)
                    {
                        break;
                    }

                    flag = false;

                    if (itemstack.isEmpty())
                    {
                        Item item = getArmorByChance(entityequipmentslot, i);

                        if (item != null)
                        {
                            this.setItemStackToSlot(entityequipmentslot, new ItemStack(item));
                        }
                    }
                }
            }
        }
    }

    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        float f = difficulty.getClampedAdditionalDifficulty();

        if (!this.getHeldItemMainhand().isEmpty() && this.rand.nextFloat() < 0.25F * f)
        {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, EnchantmentHelper.addRandomEnchantment(this.rand, this.getHeldItemMainhand(), (int)(5.0F + f * (float)this.rand.nextInt(18)), false));
        }

        for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
        {
            if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
            {
                ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);

                if (!itemstack.isEmpty() && this.rand.nextFloat() < 0.5F * f)
                {
                    this.setItemStackToSlot(entityequipmentslot, EnchantmentHelper.addRandomEnchantment(this.rand, itemstack, (int)(5.0F + f * (float)this.rand.nextInt(18)), false));
                }
            }
        }
    }

    /**
     * Returns true if this entity can attack entities of the specified class.
     */
    public boolean canAttackClass(Class <? extends EntityLivingBase> cls)
    {
        return cls != EntityVillager.class;
    }

    @Nullable
    protected SoundEvent getAmbientSound()
    {
        return null;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return null;
    }

    @Nullable
    protected SoundEvent getDeathSound()
    {
        return null;
    }
}

