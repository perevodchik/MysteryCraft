package perevodchik.entity.boss;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityLivingZombie extends EntityMob {
    private double MAX_HEALTH = 150.0D;
    private double KNOCKBACK_RESISTANCE = 10.0D;
    private double ARMOR = 2.0D;
    private double ARMOR_TOUGHNESS = 4.0D;
    private double ATTACK_SPEED = 2.0D;
    private double MOVEMENT_SPEED = 1.5D;
    private double FOLLOW_RANGE = 64.0D;
    private double ATTACK_DAMAGE = 5.0D;
    private double x = 0.8D;

    public EntityLivingZombie(World worldIn) {
        super(worldIn);
        this.isImmuneToFire = true;
    }

    public EntityLivingZombie(World worldIn, double x, double y, double z) {
        super(worldIn);
        this.setPosition(x, y, z);
    }

    protected void initEntityAI()
    {
        //this.tasks.addTask(1, new EntityAINearestAttackableTarget<>());
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.2D, true));
        //this.tasks.addTask(1, new EntityAIDefendChunk(this, chunkX, chunkZ));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 2, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.1D, 32.0F));

    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(KNOCKBACK_RESISTANCE);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(ARMOR);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(ARMOR_TOUGHNESS);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(MOVEMENT_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(FOLLOW_RANGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ATTACK_DAMAGE);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
       /* if(this.getHealth() < MAX_HEALTH * 0.7 && this.getHealth() > MAX_HEALTH * 0.5) {
            x = 1.1D;
        } else if(this.getHealth() < MAX_HEALTH * 0.4) {
            x = 1.5D;
        } else x = 0.8D;*/

        super.onLivingUpdate();
        System.out.println(this.getHealth());
    }

    private void changeStage(double x) {
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(MOVEMENT_SPEED*x);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ATTACK_DAMAGE*x);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(ATTACK_SPEED*x);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(KNOCKBACK_RESISTANCE*x);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (super.attackEntityFrom(source, amount))
        {
            if(source.getTrueSource() instanceof EntityPlayer && rand.nextInt(100)%7 == 0) {
                BlockPos pos = this.getPosition();
                System.out.println("summon the reinforcemence!! ****************************************");
                EntityZombie z = new EntityZombie(this.world);
                z.setPosition(pos.getX(), pos.getY(), pos.getZ());
                this.world.spawnEntity(z);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns false if this Entity is a boss, true otherwise.
     */
    public boolean isNonBoss()
    {
        return false;
    }
}
