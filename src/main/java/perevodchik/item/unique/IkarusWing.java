package perevodchik.item.unique;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;
import perevodchik.item.ItemWings;
import perevodchik.util.RarityItems;

import javax.annotation.Nonnull;

public class IkarusWing extends ItemWings {

    public IkarusWing(CreativeTabs tab, String name) {
        this.setRegistryName("ikarus_wing");
        this.setUnlocalizedName(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(1234);
        this.setCreativeTab(tab);
    }

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(entityIn instanceof EntityPlayer) {
            ((EntityPlayer) entityIn).capabilities.allowFlying = true;
        }
    }



    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return RarityItems.RARITY_LEGENDARY;
    }

}
