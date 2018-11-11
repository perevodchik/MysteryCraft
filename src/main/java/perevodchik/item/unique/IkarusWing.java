package perevodchik.item.unique;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import perevodchik.enums.RarityItemsList;

import javax.annotation.Nonnull;

public class IkarusWing extends Item {

    public IkarusWing(String name, CreativeTabs tab) {
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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemstack);
        ItemStack itemstack1 = playerIn.getItemStackFromSlot(entityequipmentslot);

        if (itemstack1.isEmpty())
        {
            playerIn.setItemStackToSlot(entityequipmentslot, itemstack.copy());
            itemstack.setCount(0);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }

    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return RarityItemsList.RARITY_LEGENDARY;
    }

}
