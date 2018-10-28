package perevodchik.item.unique;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import perevodchik.util.RarityItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class SunSword extends ItemSword {
    private boolean isStrong = false;

    public SunSword(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("sun_sword");
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("§2Описание предмета");
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        if(isStrong) {
            target.setFire(3);
        }
        stack.damageItem(1, attacker);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        worldIn.setBlockState(pos, Blocks.STONE.getDefaultState(), 2);
        stack.damageItem(2, entityLiving);
        return true;
    }

    public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker)
    {
        return true;
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
        this.isStrong = world.getWorldTime() > 1000 && world.getWorldTime() < 12200;

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(3), 2, 2, true, true));
        }
    }

    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return RarityItems.RARITY_LEGENDARY;
    }
}
