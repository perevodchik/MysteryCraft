package perevodchik.item.unique;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import perevodchik.item.ItemScythe;
import perevodchik.util.RarityItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ReaperScythe extends ItemScythe {
    private boolean isStrong = false;

    public ReaperScythe(ToolMaterial material, CreativeTabs tab, String name) {
        super(material);
        this.setRegistryName("reaper_scythe");
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
            target.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 2, 2, true, true));
        }

        stack.damageItem(1, attacker);
        if (!target.isEntityAlive()) {
            attacker.heal(3);
        }
        return true;
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
        this.isStrong = world.getWorldTime() > 1000 && world.getWorldTime() < 12200;
    }

    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return RarityItems.RARITY_LEGENDARY;
    }
}
